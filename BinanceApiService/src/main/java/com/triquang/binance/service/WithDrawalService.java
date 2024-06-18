package com.triquang.binance.service;

import java.util.List;

import com.triquang.binance.model.User;
import com.triquang.binance.model.WithDrawal;

public interface WithDrawalService {
	WithDrawal requestWithDrawal(Long amount, User user);

	WithDrawal proceedWithDrawal(Long withdrawalId, boolean accept) throws Exception;
	
	List<WithDrawal> getUserWithDrawalsHistory(User user);
	
	List<WithDrawal> getAllDrawals();
}
