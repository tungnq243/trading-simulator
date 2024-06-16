package com.triquang.binance.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.domain.VerificationType;
import com.triquang.binance.model.ForgotPasswordToken;
import com.triquang.binance.model.User;
import com.triquang.binance.model.VerificationCode;
import com.triquang.binance.request.ForgotPasswordTokenRequest;
import com.triquang.binance.request.ResetPasswordRequest;
import com.triquang.binance.response.ApiResponse;
import com.triquang.binance.response.AuthResponse;
import com.triquang.binance.service.EmailService;
import com.triquang.binance.service.ForgotPasswordService;
import com.triquang.binance.service.UserService;
import com.triquang.binance.service.VerificationCodeService;
import com.triquang.binance.utils.OtpUtils;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VerificationCodeService codeService;

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@GetMapping("/users/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PatchMapping("/users/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<User> enableTwoFactorAuthentication(@PathVariable String otp,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		VerificationCode code = codeService.getVerificationCodeByUser(user.getId());

		String sendTo = code.getVerificationType().equals(VerificationType.EMAIL) ? code.getEmail() : code.getMobile();

		boolean isVerified = code.getOtp().equals(otp);
		if (isVerified) {
			User updatedUser = userService.enableTwoFactorAuthentication(code.getVerificationType(), sendTo, user);
			codeService.deleteVerificationCodeById(code);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		throw new Exception("Wrong OTP");
	}

	@PostMapping("/users/verification/{verificationType}/send-otp")
	public ResponseEntity<String> verificationOtp(@RequestHeader("Authorization") String jwt,
			@PathVariable VerificationType verificationType) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		VerificationCode code = codeService.getVerificationCodeByUser(user.getId());
		if (code == null) {
			code = codeService.sendVerificationCode(user, verificationType);
		}
		if (code.equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(), code.getOtp());
		}

		return new ResponseEntity<>("Verification OTP send successfully", HttpStatus.OK);
	}

	@PostMapping("/auth/users/reset-password/send-otp")
	public ResponseEntity<AuthResponse> forgotPasswordOtp(@RequestBody ForgotPasswordTokenRequest req)
			throws Exception {
		User user = userService.findUserByEmail(req.getSendTo());
		String otp = OtpUtils.generateOTP();
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();

		ForgotPasswordToken token = forgotPasswordService.findByUser(user.getId());
		if (token == null) {
			token = forgotPasswordService.createToken(user, id, otp, req.getVerificationType(), req.getSendTo());
		}
		if (req.getVerificationType().equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(), token.getOtp());
		}

		var res = new AuthResponse();
		res.setSession(token.getId());
		res.setMessage("Password reset OTP send successfully");

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PatchMapping("/auth/users/reset-password/verify-otp")
	public ResponseEntity<ApiResponse> resetPassword(@RequestParam String id, @RequestBody ResetPasswordRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		ForgotPasswordToken token = forgotPasswordService.findById(id);
		boolean isVerified = token.getOtp().equals(req.getOtp());
		if (isVerified) {
			userService.updatePassword(token.getUser(), req.getPassword());
			var res = new ApiResponse("Password updated successfully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		throw new Exception("Wrong OTP");

	}

}
