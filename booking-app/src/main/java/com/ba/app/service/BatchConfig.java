package com.ba.app.service;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ba.app.model.BookingRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BatchConfig {
	@Autowired
	private BookingRepository bookingRepository;
	
	@Scheduled(cron="0 0 0 * * ?")	
	public void cronJobUpdateLRSequence() throws Exception {
    	
		bookingRepository.updateLRSequence();
    
    log.info("SEQUENCE Updated Date Time : {}",new Date());
    	
	}
}
