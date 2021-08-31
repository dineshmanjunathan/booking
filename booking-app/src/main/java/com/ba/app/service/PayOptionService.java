package com.ba.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ba.app.entity.PayType;
import com.ba.app.model.PayOptionRepository;

@Service
public class PayOptionService {
	@Autowired	
	private PayOptionRepository payOptionRepository;
	public void savePayout(PayType payout) {
		payOptionRepository.save(payout);
		
	}

}