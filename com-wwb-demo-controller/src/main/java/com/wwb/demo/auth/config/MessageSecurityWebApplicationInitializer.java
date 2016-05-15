package com.wwb.demo.auth.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.wwb.demo.auth.RootConfiguration;

public class MessageSecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfiguration.class };
	}
}
