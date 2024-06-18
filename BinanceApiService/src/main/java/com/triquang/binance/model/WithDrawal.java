package com.triquang.binance.model;

import java.time.LocalDateTime;

import com.triquang.binance.domain.WithDrawalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class WithDrawal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private WithDrawalStatus status;
	private Long amount;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime date = LocalDateTime.now();
}
