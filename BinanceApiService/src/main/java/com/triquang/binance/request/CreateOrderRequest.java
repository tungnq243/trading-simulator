package com.triquang.binance.request;

import com.triquang.binance.domain.OrderType;

import lombok.Data;

@Data
public class CreateOrderRequest {
	private String coindId;
	private double quantity;
	private OrderType orderType;
}
