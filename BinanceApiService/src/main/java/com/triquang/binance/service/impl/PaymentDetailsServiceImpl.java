package com.triquang.binance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.model.PaymentDetails;
import com.triquang.binance.model.User;
import com.triquang.binance.repository.PaymentDetailsRepository;
import com.triquang.binance.service.PaymentDetailsService;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;

	@Override
	public PaymentDetails addPaymentDetails(String accountNumber, String accountHodlerName, String ifsc,
			String bankName, User user) {
		var paymentDetail = new PaymentDetails();
		paymentDetail.setAccountNumber(accountNumber);
		paymentDetail.setAccountHolderName(accountHodlerName);
		paymentDetail.setBankName(bankName);
		paymentDetail.setUser(user);
		return paymentDetailsRepository.save(paymentDetail);
	}

	@Override
	public PaymentDetails getUserPaymentDetails(User user) {
		return paymentDetailsRepository.findByUserId(user.getId());
	}

}
