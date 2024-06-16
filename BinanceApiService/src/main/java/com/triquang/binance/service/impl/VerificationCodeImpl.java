package com.triquang.binance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.domain.VerificationType;
import com.triquang.binance.model.User;
import com.triquang.binance.model.VerificationCode;
import com.triquang.binance.repository.VerificationCodeRepository;
import com.triquang.binance.service.VerificationCodeService;
import com.triquang.binance.utils.OtpUtils;

@Service
public class VerificationCodeImpl implements VerificationCodeService {
	@Autowired
	private VerificationCodeRepository codeRepository;

	@Override
	public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
		var code = new VerificationCode();
		code.setOtp(OtpUtils.generateOTP());
		code.setVerificationType(verificationType);
		code.setUser(user);
		return codeRepository.save(code);
	}

	@Override
	public VerificationCode getVerificationCodeById(Long id) throws Exception {
		Optional<VerificationCode> optional = codeRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new Exception("Verification code not found");
	}

	@Override
	public VerificationCode getVerificationCodeByUser(Long userId) {
		return codeRepository.findByUserId(userId);
	}

	@Override
	public void deleteVerificationCodeById(VerificationCode verificationCode) {
		codeRepository.delete(verificationCode);
	}
}
