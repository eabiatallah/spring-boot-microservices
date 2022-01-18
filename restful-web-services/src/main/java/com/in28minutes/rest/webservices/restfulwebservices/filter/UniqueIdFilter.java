package com.in28minutes.rest.webservices.restfulwebservices.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Data;

@Component
@Data
public class UniqueIdFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(UniqueIdFilter.class);

	public static final String DEFAULT_HEADER_TOKEN = "correlationId";
	public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "correlationId";

	private final String responseHeader;
	private final String mdcKey;
	private final String requestHeader;

	public UniqueIdFilter() {
		responseHeader = DEFAULT_HEADER_TOKEN;
		mdcKey = DEFAULT_MDC_UUID_TOKEN_KEY;
		requestHeader = DEFAULT_HEADER_TOKEN;
	}

//	public UniqueIdFilter(String responseHeader, String mdcKey, String requestHeader) {
//		this.responseHeader = responseHeader;
//		this.mdcKey = mdcKey;
//		this.requestHeader = requestHeader;
//	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			log.info("--- doFilterInternal --- ");
			final String token = extractToken(req);
			MDC.put(mdcKey, token);
			if (StringUtils.hasText(responseHeader)) {
				res.addHeader(responseHeader, token);
			}
			chain.doFilter(request, response);
		} finally {
			log.info("--- doFilterInternal finally--- ");
			MDC.remove(mdcKey);
		}

	}

	private String extractToken(final HttpServletRequest request) {
		final String token;
		if (StringUtils.hasText(requestHeader) && StringUtils.hasText(request.getHeader(requestHeader))) {
			token = request.getHeader(requestHeader);
		} else {
			token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
		}
		return token;
	}

}
