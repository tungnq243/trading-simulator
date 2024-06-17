package com.triquang.binance.service;

import com.triquang.binance.model.Order;
import com.triquang.binance.model.User;
import com.triquang.binance.model.Wallet;

public interface WalletService {
	Wallet getUserWallet(User user);
	Wallet addBalance(Wallet wallet, Long money);
	Wallet findWalletById(Long id) throws Exception;
	Wallet walletToTransfer(User sender, Wallet receiveWallet, Long amount) throws Exception;
	Wallet payOrderPayment(Order order, User user) throws Exception;
}
