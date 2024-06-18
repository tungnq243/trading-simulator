package com.triquang.binance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.triquang.binance.model.WithDrawal;

public interface WithDrawalRepository extends JpaRepository<WithDrawal, Long> {
	List<WithDrawal> findByUserId(Long userId);
	
}
