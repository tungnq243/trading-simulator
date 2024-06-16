package com.triquang.binance.response;

import lombok.Data;

@Data
public class AuthResponse {
	private String jwt;
	private String message;
	private boolean status;
	private boolean isTwoFactoraAuth;
	private String session;
}
