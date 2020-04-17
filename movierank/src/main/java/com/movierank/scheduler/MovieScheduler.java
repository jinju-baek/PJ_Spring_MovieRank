package com.movierank.scheduler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.movierank.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MovieScheduler {
	
	@Autowired
	MovieService mService;
	
	@Scheduled(cron = "0 34 15 * * *")
	public void movieCollect() throws IOException {
		log.info(">>>>> Movie ticketrank Collect :)");
		mService.ticketRank();
	}
}
