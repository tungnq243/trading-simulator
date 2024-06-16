package com.triquang.binance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.domain.VerificationType;
import com.triquang.binance.model.TwoFactorAuth;
import com.triquang.binance.model.User;
import com.triquang.binance.repository.UserRepository;
import com.triquang.binance.security.JwtProvider;
import com.triquang.binance.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isEmpty()) {
			throw new Exception("User not found");
		}
		return optional.get();
	}

	@Override
	public User updatePassword(User user, String password) {
		user.setPassword(password);
		return userRepository.save(user);
	}

	@Override
	public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
		var auth = new TwoFactorAuth();
		auth.setEnabled(true);
		auth.setSendTo(verificationType);
		user.setTwoFactorAuth(auth);

		return userRepository.save(user);
	}

}
