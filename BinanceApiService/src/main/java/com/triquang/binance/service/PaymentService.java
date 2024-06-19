package com.triquang.binance.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.triquang.binance.domain.PaymentMethod;
import com.triquang.binance.model.PaymentOrder;
import com.triquang.binance.model.User;
import com.triquang.binance.response.PaymentResponse;

public interface PaymentService {
	PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

	PaymentOrder getPaymentOrderById(Long id) throws Exception;

	Boolean ProccedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;

	PaymentResponse createRazorpayPaymentLink(User user, Long Amount, Long orderId) throws RazorpayException;

	PaymentResponse createStripePaymentLink(User user, Long Amount, Long orderId) throws StripeException;
}
