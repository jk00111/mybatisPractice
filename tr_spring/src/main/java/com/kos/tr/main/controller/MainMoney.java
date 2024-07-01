package com.kos.tr.main.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainMoney {
	Logger log = LogManager.getLogger(MainMoney.class);
	
	@Scheduled(cron="0 0/10 * * * *") // 초 분 시 
	public void checkFiles() throws Exception{
		log.info("File Check Test run...");
		log.info("======================");
		
		Flask a = new Flask();
		a.flask_test();
	}
}
