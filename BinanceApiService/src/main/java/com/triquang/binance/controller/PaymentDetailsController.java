package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.PaymentDetails;
import com.triquang.binance.model.User;
import com.triquang.binance.service.PaymentDetailsService;
import com.triquang.binance.service.UserService;

@RestController
@RequestMapping("/api")
public class PaymentDetailsController {
	@Autowired
	private PaymentDetailsService detailsService;

	@Autowired
	private UserService userService;

	@PostMapping("/payment-details")
	public ResponseEntity<PaymentDetails> addPaymentDetails(@RequestBody PaymentDetails details,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		PaymentDetails paymentDetails = detailsService.addPaymentDetails(details.getAccountNumber(),
				details.getAccountHolderName(), details.getIfsc(), details.getBankName(), user);
		return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);

	}

	@GetMapping("/payment-details")
	public ResponseEntity<PaymentDetails> getPaymentDetails(@RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		PaymentDetails paymentDetails = detailsService.getUserPaymentDetails(user);
		return new ResponseEntity<>(paymentDetails, HttpStatus.OK);

	}
}
