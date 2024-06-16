package com.triquang.binance.service;

import com.triquang.binance.model.TwoFactorOTP;
import com.triquang.binance.model.User;

public interface TwoFactorOTPService {
	TwoFactorOTP createFactorOTP(User user, String otp, String jwt);

	TwoFactorOTP findByUser(Long userId);

	TwoFactorOTP findById(String id);

	boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp);

	void deleteTwoFactorOtp(TwoFactorOTP factorOTP);
}
