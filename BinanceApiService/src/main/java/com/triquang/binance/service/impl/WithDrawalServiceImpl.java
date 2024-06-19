package com.triquang.binance.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.domain.WithDrawalStatus;
import com.triquang.binance.model.User;
import com.triquang.binance.model.Withdrawal;
import com.triquang.binance.repository.WithDrawalRepository;
import com.triquang.binance.service.WithDrawalService;

@Service
public class WithDrawalServiceImpl implements WithDrawalService {
	@Autowired
	private WithDrawalRepository drawalRepository;

	@Override
	public Withdrawal requestWithDrawal(Long amount, User user) {
		Withdrawal drawal = new Withdrawal();
		drawal.setAmount(amount);
		drawal.setUser(user);
		drawal.setStatus(WithDrawalStatus.PENDING);
		return drawalRepository.save(drawal);
	}

	@Override
	public Withdrawal proceedWithDrawal(Long withdrawalId, boolean accept) throws Exception {
		Optional<Withdrawal> opt = drawalRepository.findById(withdrawalId);
		if (opt.isEmpty()) {
			throw new Exception("WithDrawal not found");
		}
		Withdrawal drawal = opt.get();
		drawal.setDate(LocalDateTime.now());
		if (accept) {
			drawal.setStatus(WithDrawalStatus.SUCCESS);
		} else {
			drawal.setStatus(WithDrawalStatus.PENDING);
		}
		return drawalRepository.save(drawal);
	}

	@Override
	public List<Withdrawal> getUserWithDrawalsHistory(User user) {
		return drawalRepository.findByUserId(user.getId());
	}

	@Override
	public List<Withdrawal> getAllDrawals() {
		return drawalRepository.findAll();
	}

}
