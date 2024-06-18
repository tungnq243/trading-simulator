package com.triquang.binance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triquang.binance.model.Coin;
import com.triquang.binance.service.CoinService;

@RestController
@RequestMapping("/coins")
public class CoinController {
	@Autowired
	private CoinService coinService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<List<Coin>> getCoins(@RequestParam(required = false, name = "page") int page)
			throws Exception {
		List<Coin> coinList = coinService.getCoinList(page);
		return new ResponseEntity<>(coinList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{coinId}/chart")
	public ResponseEntity<JsonNode> getMarketChart(@PathVariable String coinId, @RequestParam("days") int days)
			throws Exception {
		String res = coinService.getMarketChart(coinId, days);
		JsonNode jsonNode = objectMapper.readTree(res);
		return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
	}

	@GetMapping("/search")
	public ResponseEntity<JsonNode> searchCoin(@RequestParam("q") String keyword) throws Exception {
		String coin = coinService.searchCoin(keyword);
		JsonNode jsonNode = objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}

	@GetMapping("/top50")
	public ResponseEntity<JsonNode> getTop50CoinByMarketCapRank() throws Exception {
		String coin = coinService.getTopCoinsByMarketCapRank();
		JsonNode jsonNode = objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}

	@GetMapping("/trending")
	public ResponseEntity<JsonNode> getTrendingCoin() throws Exception {
		String coin = coinService.getTrendingCoins();
		JsonNode jsonNode = objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}

	@GetMapping("/details/{coidId}")
	public ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId) throws Exception {
		String coin = coinService.getCoinDetails(coinId);
		JsonNode jsonNode = objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}
}
