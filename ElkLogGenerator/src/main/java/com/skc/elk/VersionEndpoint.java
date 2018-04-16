/**
 * 
 */
package com.skc.elk;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */
@RestController
public class VersionEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionEndpoint.class);
	
	@GetMapping("/details")
	public Map<String,String> getVersion(){
		LOGGER.info("Got request for version");
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("applicationName", "ElkLogGenerator");
		responseMap.put("version", "1.0");
		responseMap.put("developer", "sitakant");
		responseMap.put("developer-home", "https://cooligc.github.io");
		LOGGER.info("I responded to the request");
		return responseMap;
	}
	
}
