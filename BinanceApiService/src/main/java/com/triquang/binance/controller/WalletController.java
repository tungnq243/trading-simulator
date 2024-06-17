package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.Order;
import com.triquang.binance.model.User;
import com.triquang.binance.model.Wallet;
import com.triquang.binance.model.WalletTransaction;
import com.triquang.binance.service.OrderService;
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
}
