package com.triquang.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.Coin;
import com.triquang.binance.model.User;
import com.triquang.binance.model.WatchList;
import com.triquang.binance.service.CoinService;
import com.triquang.binance.service.UserService;
import com.triquang.binance.service.WatchListService;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {
	@Autowired
	private WatchListService watchListService;

	@Autowired
	private UserService userService;

	@Autowired
	private CoinService coinService;

	@GetMapping("/user")
	public ResponseEntity<WatchList> getUserWatchList(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		WatchList watchList = watchListService.findUserWatchList(user.getId());

		return ResponseEntity.ok(watchList);
	}

	@PostMapping("/create")
	public ResponseEntity<WatchList> createUserWatchList(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		WatchList watchList = watchListService.createWatchList(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(watchList);
	}

	@GetMapping("/{watchlistId}")
	public ResponseEntity<WatchList> getWatchListById(@PathVariable Long watchlistId) throws Exception {
		WatchList list = watchListService.findById(watchlistId);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/add/coin/{coinId}")
	public ResponseEntity<Coin> addItemToWatchList(@RequestHeader("Authorization") String jwt,
			@PathVariable String coinId) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Coin coin = coinService.findById(coinId);
		Coin addCoin = watchListService.addItemToWatchList(coin, user);

		return ResponseEntity.status(HttpStatus.CREATED).body(addCoin);
	}

}
