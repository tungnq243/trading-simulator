package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.exception.UserException;
import com.triquang.binance.model.PaymentDetails;
import com.triquang.binance.model.User;
import com.triquang.binance.service.PaymentDetailsService;
import com.triquang.binance.service.UserService;

@RestController
public class PaymentDetailsController {
	@Autowired
	private UserService userService;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@PostMapping("/payment-details")
	public ResponseEntity<PaymentDetails> addPaymentDetails(@RequestBody PaymentDetails paymentDetailsRequest,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(
				paymentDetailsRequest.getAccountNumber(), paymentDetailsRequest.getAccountHolderName(),
				paymentDetailsRequest.getIfsc(), paymentDetailsRequest.getBankName(), user);
		return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
	}

	@GetMapping("/payment-details")
	public ResponseEntity<PaymentDetails> getUsersPaymentDetails(

			@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		PaymentDetails paymentDetails = paymentDetailsService.getUsersPaymentDetails(user);
		return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
	}
}