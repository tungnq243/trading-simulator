package com.triquang.binance.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.model.TwoFactorOTP;
import com.triquang.binance.model.User;
import com.triquang.binance.repository.TwoFactorOTPRepository;
import com.triquang.binance.service.TwoFactorOTPService;

@Service
public class TwoFactorOTPServiceImpl implements TwoFactorOTPService {
	@Autowired
	private TwoFactorOTPRepository factorOTPRepository;

	@Override
	public TwoFactorOTP createFactorOTP(User user, String otp, String jwt) {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();

		TwoFactorOTP factorOTP = new TwoFactorOTP();
		factorOTP.setOtp(otp);
		factorOTP.setJwt(jwt);
		factorOTP.setId(id);
		factorOTP.setUser(user);

		return factorOTPRepository.save(factorOTP);
	}

	@Override
	public TwoFactorOTP findById(String id) {
		Optional<TwoFactorOTP> optional = factorOTPRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
		return twoFactorOTP.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOtp(TwoFactorOTP factorOTP) {
		factorOTPRepository.delete(factorOTP);
	}

	@Override
	public TwoFactorOTP findByUser(Long userId) {
		return factorOTPRepository.findByUserId(userId);
	}

}
