package com.triquang.binance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.triquang.binance.model.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {
	WatchList findByUserId(Long userId);
}
