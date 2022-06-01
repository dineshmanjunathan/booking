package com.ba.app.service;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BatchConfig {
	@Autowired
	private EntityManager entityManager;
	
    @Scheduled(cron="0 0 0 * * ?")
	public void cronJobUpdateLRSequence() throws Exception {
    	
    entityManager.createNativeQuery("ALTER SEQUENCE LRNUMBER_SEQ RESTART WITH 101").getFirstResult();
    
    log.info("SEQUENCE Updated Date Time : {}",new Date());
    	
	}
}
