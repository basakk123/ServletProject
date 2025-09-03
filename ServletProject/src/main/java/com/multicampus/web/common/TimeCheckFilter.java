package com.multicampus.web.common;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TimeCheckFilter implements Filter {
	
	public TimeCheckFilter() {
		System.out.println("===> TimeCheckFilter 생성");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		
		long start = System.currentTimeMillis();
		
		// 이 시점에 브라우저가 호출한 서블릿이 실행된다.
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		System.out.println(uri + " : 요청 처리에 소요된 시간" + (end - start) + "(ms)초");
	}
}


