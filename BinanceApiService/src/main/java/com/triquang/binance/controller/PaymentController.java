package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.domain.PaymentMethod;
import com.triquang.binance.model.PaymentOrder;
import com.triquang.binance.model.User;
import com.triquang.binance.response.PaymentResponse;
import com.triquang.binance.service.PaymentService;
import com.triquang.binance.service.UserService;

@RestController
public class PaymentController {
	@Autowired
	private UserService userService;

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
	public ResponseEntity<PaymentResponse> paymentHandler(@PathVariable PaymentMethod paymentMethod,
			@PathVariable Long amount, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		PaymentResponse paymentResponse;

		PaymentOrder order = paymentService.createOrder(user, amount, paymentMethod);
		if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
			paymentResponse = paymentService.createRazorPayment(user, amount, order.getId());
		} else {
			paymentResponse = paymentService.createStripePayment(user, amount, order.getId());
		}
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);

	}
}
