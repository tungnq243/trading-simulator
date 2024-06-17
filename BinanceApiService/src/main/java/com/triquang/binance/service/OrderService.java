package com.triquang.binance.service;

import java.util.List;

import com.triquang.binance.domain.OrderType;
import com.triquang.binance.model.Coin;
import com.triquang.binance.model.Order;
import com.triquang.binance.model.OrderItem;
import com.triquang.binance.model.User;

public interface OrderService {
	Order createOrder(User user, OrderItem item, OrderType type);

	Order getOrderById(Long orderId) throws Exception;

	List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbo);

	Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;
}
