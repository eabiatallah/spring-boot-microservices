package com.in28minutes.rest.webservices.restfulwebservices.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.rest.webservices.restfulwebservices.filter.UniqueIdFilter;

import lombok.Data;

//@Configuration
//@Data
public class UniqueIdFilterConfiguration {
	
//	private static final Logger log = LoggerFactory.getLogger(UniqueIdFilterConfiguration.class);
//
//	public static final String DEFAULT_HEADER_TOKEN = "correlationId";
//	public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "correlationId";
//
//	private String responseHeader = DEFAULT_HEADER_TOKEN;
//	private String mdcKey = DEFAULT_MDC_UUID_TOKEN_KEY;
//	private String requestHeader = DEFAULT_HEADER_TOKEN;
//
//	@Bean
//	public FilterRegistrationBean<UniqueIdFilter> servletRegistrationBean() {
//		log.info("--- FilterRegistrationBean -----");
//		final FilterRegistrationBean<UniqueIdFilter> registrationBean = new FilterRegistrationBean<>();
//		final UniqueIdFilter log4jMDCFilterFilter = new UniqueIdFilter(responseHeader, mdcKey, requestHeader);
//		registrationBean.setFilter(log4jMDCFilterFilter);
//		registrationBean.setOrder(2);
//		return registrationBean;
//	}

}
