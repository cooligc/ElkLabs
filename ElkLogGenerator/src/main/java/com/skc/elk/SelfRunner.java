/**
 * 
 */
package com.skc.elk;

import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author sitakant
 *
 */
@Component
public class SelfRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SelfRunner.class);
	
	@Scheduled(fixedDelay=5000)
	public void runForLogs() {
		MDC.put("transactionId", "[scheduled="+UUID.randomUUID().toString()+"]");
		LOGGER.info("Self Runner started");
		LOGGER.info("Self Runner completed");
		MDC.clear();
	}
	
}
