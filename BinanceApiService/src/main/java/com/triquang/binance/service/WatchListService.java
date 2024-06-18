package com.triquang.binance.service;

import com.triquang.binance.model.Coin;
import com.triquang.binance.model.User;
import com.triquang.binance.model.WatchList;

public interface WatchListService {
	WatchList findUserWatchList(Long userId) throws Exception;

	WatchList createWatchList(User user);

	WatchList findById(Long id) throws Exception;

	Coin addItemToWatchList(Coin coin, User user) throws Exception;
}
