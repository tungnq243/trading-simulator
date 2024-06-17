package com.triquang.binance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triquang.binance.model.Asset;
import com.triquang.binance.model.User;
import com.triquang.binance.service.AssetService;
import com.triquang.binance.service.UserService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
	@Autowired
	private AssetService assetService;

	@Autowired
	private UserService userService;

	@GetMapping("/{assetId}")
	public ResponseEntity<Asset> getAssetById(@PathVariable Long assetId) throws Exception {
		Asset asset = assetService.getAssetById(assetId);
		return ResponseEntity.ok().body(asset);
	}

	@GetMapping("/coin/{coinId}/user")
	public ResponseEntity<Asset> getAssetByUserIdAndCoinId(@PathVariable String coinId,
			@RequestParam("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Asset asset = assetService.findAssetByUserIdAndCoinId(user.getId(), coinId);
		return ResponseEntity.ok().body(asset);
	}

	@GetMapping()
	public ResponseEntity<List<Asset>> getAssetForUser(@RequestParam("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		List<Asset> usersAsset = assetService.getUsersAsset(user.getId());
		return ResponseEntity.ok().body(usersAsset);
	}
}
