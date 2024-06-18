package com.triquang.binance.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.Order;
import com.triquang.binance.model.PaymentOrder;
import com.triquang.binance.model.User;
import com.triquang.binance.model.Wallet;
import com.triquang.binance.model.WalletTransaction;
import com.triquang.binance.response.PaymentResponse;
import com.triquang.binance.service.OrderService;
import com.triquang.binance.service.PaymentService;
import com.triquang.binance.service.UserService;
import com.triquang.binance.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
	@Autowired
	private WalletService walletService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<Wallet> getUserWaller(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Wallet wallet = walletService.getUserWallet(user);

		return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{walletId}/transfer")
	public ResponseEntity<Wallet> walletToTransfer(@RequestHeader("Authorization") String jwt,
			@PathVariable Long walletId, @RequestBody WalletTransaction req) throws Exception {

		User senderUser = userService.findUserProfileByJwt(jwt);
		Wallet receiveWallet = walletService.findWalletById(walletId);
		Wallet wallet = walletService.walletToTransfer(senderUser, receiveWallet, req.getAmount());

		return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
	}

	@PutMapping("/order/{orderId}/pay")
	public ResponseEntity<Wallet> orderPayment(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId)
			throws Exception {
		User senderUser = userService.findUserProfileByJwt(jwt);
		Order order = orderService.getOrderById(orderId);

		Wallet wallet = walletService.payOrderPayment(order, senderUser);

		return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
	}

	@PutMapping("/deposit")
	public ResponseEntity<Wallet> addBalanceToWallet(@RequestHeader("Authorization") String jwt,
			@RequestParam(name = "order_id") Long orderId, @RequestParam(name = "payment_id") String paymentId)
			throws Exception {
		User senderUser = userService.findUserProfileByJwt(jwt);

		Wallet wallet = walletService.getUserWallet(senderUser);

		PaymentOrder order = paymentService.getPaymentOrderById(orderId);
		boolean status = paymentService.proceedPaymentOrder(order, paymentId);
		if (wallet.getBalance() == null) {
			wallet.setBalance(BigDecimal.valueOf(0));
		}
		if (status) {
			wallet = walletService.addBalance(wallet, order.getAmount());
		}
		return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
	}
}
