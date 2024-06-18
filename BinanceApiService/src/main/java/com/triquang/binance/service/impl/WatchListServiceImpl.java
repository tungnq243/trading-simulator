package com.triquang.binance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.model.Coin;
import com.triquang.binance.model.User;
import com.triquang.binance.model.WatchList;
import com.triquang.binance.repository.WatchListRepository;
import com.triquang.binance.service.WatchListService;

@Service
public class WatchListServiceImpl implements WatchListService {
	@Autowired
	private WatchListRepository watchListRepository;

	@Override
	public WatchList findUserWatchList(Long userId) throws Exception {
		WatchList watchList = watchListRepository.findByUserId(userId);
		if (watchList == null) {
			throw new Exception("WatchList not found");
		}
		return watchList;
	}

	@Override
	public WatchList createWatchList(User user) {
		WatchList watchList = new WatchList();
		watchList.setUser(user);
		return watchListRepository.save(watchList);
	}

	@Override
	public WatchList findById(Long id) throws Exception {
		Optional<WatchList> optional = watchListRepository.findById(id);
		if (optional.isEmpty()) {
			throw new Exception("WatchList not found");
		}
		return optional.get();
	}

	@Override
	public Coin addItemToWatchList(Coin coin, User user) throws Exception {
		WatchList list = findUserWatchList(user.getId());
		if (list.getCoins().contains(coin)) {
			list.getCoins().remove(coin);
		} else {
			list.getCoins().add(coin);
		}
		watchListRepository.save(list);

		return coin;
	}

}
