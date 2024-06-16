package com.triquang.binance.service;

import com.triquang.binance.domain.VerificationType;
import com.triquang.binance.model.User;
import com.triquang.binance.model.VerificationCode;

public interface VerificationCodeService {
	VerificationCode sendVerificationCode(User user ,VerificationType verificationType);

	VerificationCode getVerificationCodeById(Long id) throws Exception;

	VerificationCode getVerificationCodeByUser(Long userId);

	void deleteVerificationCodeById(VerificationCode verificationCode);
}
