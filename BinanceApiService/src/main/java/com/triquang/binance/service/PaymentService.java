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

	boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;

	PaymentResponse createRazorPayment(User user, Long amount, Long orderId) throws RazorpayException;

	PaymentResponse createStripePayment(User user, Long amount, Long orderId) throws StripeException;
}
