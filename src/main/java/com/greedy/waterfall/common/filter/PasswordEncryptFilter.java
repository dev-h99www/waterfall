package com.greedy.waterfall.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.greedy.waterfall.common.wrapper.EncryptRequestWrapper;

@WebFilter("/member/*")
public class PasswordEncryptFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		
		String uri = hreq.getRequestURI();
		System.out.println("uri : " + uri);
		
		String intent = uri.substring(uri.lastIndexOf("/"));
		System.out.println("intent : " + intent);
		
		if(!"/login".equals(intent) && !"/update".equals(intent)) {
			EncryptRequestWrapper wrapper = new EncryptRequestWrapper(hreq);
			
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {}

}
