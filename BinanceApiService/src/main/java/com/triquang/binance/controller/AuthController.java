package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.TwoFactorOTP;
import com.triquang.binance.model.User;
import com.triquang.binance.repository.UserRepository;
import com.triquang.binance.response.AuthResponse;
import com.triquang.binance.security.JwtProvider;
import com.triquang.binance.service.CustomUserService;
import com.triquang.binance.service.EmailService;
import com.triquang.binance.service.TwoFactorOTPService;
import com.triquang.binance.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserService userDetails;

	@Autowired
	private TwoFactorOTPService factorOTPService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {

		User isExistEmail = repository.findByEmail(user.getEmail());
		if (isExistEmail != null) {
			throw new Exception("Email is already used with another account");
		}

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setFullName(user.getFullName());
		newUser.setMobile(user.getMobile());

		var savedUser = repository.save(newUser);
		repository.save(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);
		var response = new AuthResponse();
		response.setJwt(token);
		response.setMessage("Register successfully");
		response.setStatus(true);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

		Authentication authentication = authenticate(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);
		User authUser = repository.findByEmail(user.getEmail());
		if (user.getTwoFactorAuth().isEnabled()) {
			var res = new AuthResponse();
			res.setMessage("Two-Factor Authentication is enabled");
			res.setTwoFactoraAuth(true);
			String otp = OtpUtils.generateOTP();

			TwoFactorOTP oldFactorOTP = factorOTPService.findByUser(authUser.getId());
			if (oldFactorOTP != null) {
				factorOTPService.deleteTwoFactorOtp(oldFactorOTP);
			}
			var newTwoFactorOTP = factorOTPService.createFactorOTP(authUser, otp, token);
			emailService.sendVerificationOtpEmail(user.getEmail(), otp);

			res.setSession(newTwoFactorOTP.getId());
			return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		}
		var response = new AuthResponse();
		response.setMessage("Login Success");
		response.setJwt(token);
		response.setStatus(true);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/two-factor/otp/{otp}")
	public ResponseEntity<AuthResponse> verifySignUpOtp(@PathVariable String otp, @RequestParam String id)
			throws Exception {
		TwoFactorOTP factorOTP = factorOTPService.findById(id);
		if (factorOTPService.verifyTwoFactorOtp(factorOTP, otp)) {
			AuthResponse res = new AuthResponse();
			res.setMessage("Two-Factor Authenticaton verified");
			res.setTwoFactoraAuth(true);
			res.setJwt(factorOTP.getJwt());
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

		throw new Exception("Invalid OTP");

	}

	private Authentication authenticate(String username, String password) {
		UserDetails details = userDetails.loadUserByUsername(username);
		if (details == null || !passwordEncoder.matches(password, details.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
	}
}
