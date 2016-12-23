package com.linguo.sharecenter.scheduler;

import com.linguo.sharecenter.service.BookCleanUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@Configuration
public class BookCleanUpScheduler {
	private final Logger log = LoggerFactory.getLogger(BookCleanUpScheduler.class);

	@Autowired
	private BookCleanUpService bookCleanUpService;


	@Scheduled(cron = "${cron.book.cleanup}")
	public void scheduleProcessAndUpdateVehicleStats() throws Exception {
			int randomDelay = ThreadLocalRandom.current().nextInt(1, 60);
			log.info("Delaying cleanup {} seconds.", randomDelay);
			TimeUnit.SECONDS.sleep(randomDelay);
			bookCleanUpService.cleanup();

	}

}
