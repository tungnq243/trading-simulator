package com.triquang.binance.service;

import java.util.List;

import com.triquang.binance.model.User;
import com.triquang.binance.model.Withdrawal;

public interface WithDrawalService {
	Withdrawal requestWithDrawal(Long amount, User user);

	Withdrawal proceedWithDrawal(Long withdrawalId, boolean accept) throws Exception;
	
	List<Withdrawal> getUserWithDrawalsHistory(User user);
	
	List<Withdrawal> getAllDrawals();
}
