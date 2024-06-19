package com.triquang.binance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.User;
import com.triquang.binance.model.Wallet;
import com.triquang.binance.model.Withdrawal;
import com.triquang.binance.service.UserService;
import com.triquang.binance.service.WalletService;
import com.triquang.binance.service.WithDrawalService;

@RestController
public class WithDrawalController {
	@Autowired
	private WithDrawalService drawalService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/withdrawal/{amount}")
	public ResponseEntity<?> withDrawalRequest(@PathVariable Long amount, @RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Wallet wallet = walletService.getUserWallet(user);

		Withdrawal drawal = drawalService.requestWithDrawal(amount, user);
		walletService.addBalance(wallet, -drawal.getAmount());
		
		/* */

		return new ResponseEntity<>(drawal, HttpStatus.OK);
	}

	@PostMapping("/api/withdrawal/{id}/proceed/{accept}")
	public ResponseEntity<?> proceedWithDrawal(@PathVariable Long id, @PathVariable boolean accept,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		Withdrawal drawal = drawalService.proceedWithDrawal(id, accept);
		Wallet wallet = walletService.getUserWallet(user);
		if (!accept) {
			walletService.addBalance(wallet, drawal.getAmount());
		}

		return new ResponseEntity<>(drawal, HttpStatus.OK);
	}

	@GetMapping("/api/withdrawal")
	public ResponseEntity<List<Withdrawal>> getWithDrawalHistory(@RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		List<Withdrawal> drawal = drawalService.getUserWithDrawalsHistory(user);

		return new ResponseEntity<>(drawal, HttpStatus.OK);
	}

	@GetMapping("/api/admin/withdrawal")
	public ResponseEntity<List<Withdrawal>> getAllWithDrawalRequest(@RequestHeader("Authorization") String jwt)
			throws Exception {
		userService.findUserProfileByJwt(jwt);

		List<Withdrawal> drawal = drawalService.getAllDrawals();

		return new ResponseEntity<>(drawal, HttpStatus.OK);
	}

}
