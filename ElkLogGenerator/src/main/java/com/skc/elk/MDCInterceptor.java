/**
 * 
 */
package com.skc.elk;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author sitakant
 *
 */
@Component
public class MDCInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MDCInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MDC.put("transactionId", UUID.randomUUID().toString());
		LOGGER.info("------------------------------MDCInterceptor#preHandle()------------------------------");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("------------------------------MDCInterceptor#postHandle()------------------------------");
		MDC.clear();
		super.postHandle(request, response, handler, modelAndView);
	}
}
