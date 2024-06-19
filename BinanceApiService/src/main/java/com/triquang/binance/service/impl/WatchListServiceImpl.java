package com.triquang.binance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triquang.binance.model.Coin;
import com.triquang.binance.model.User;
import com.triquang.binance.model.Watchlist;
import com.triquang.binance.repository.WatchListRepository;
import com.triquang.binance.service.WatchListService;

@Service
public class WatchListServiceImpl implements WatchListService {
	@Autowired
	private WatchListRepository watchListRepository;

	@Override
	public Watchlist findUserWatchList(Long userId) throws Exception {
		Watchlist watchList = watchListRepository.findByUserId(userId);
		if (watchList == null) {
			throw new Exception("WatchList not found");
		}
		return watchList;
	}

	@Override
	public Watchlist createWatchList(User user) {
		Watchlist watchList = new Watchlist();
		watchList.setUser(user);
		return watchListRepository.save(watchList);
	}

	@Override
	public Watchlist findById(Long id) throws Exception {
		Optional<Watchlist> optional = watchListRepository.findById(id);
		if (optional.isEmpty()) {
			throw new Exception("WatchList not found");
		}
		return optional.get();
	}

	@Override
	public Coin addItemToWatchList(Coin coin, User user) throws Exception {
		Watchlist list = findUserWatchList(user.getId());
		if (list.getCoins().contains(coin)) {
			list.getCoins().remove(coin);
		} else {
			list.getCoins().add(coin);
		}
		watchListRepository.save(list);

		return coin;
	}

}
