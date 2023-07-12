package com.greedy.waterfall.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptRequestWrapper extends HttpServletRequestWrapper{

	public EncryptRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {

		/* memberPwd라는 키값으로 들어오는 값에 대해 암호화 */
		String value = "";
		if("membe222rPwd".equals(key)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			value = passwordEncoder.encode(super.getParameter(key));
		} else {
			value = super.getParameter(key);
		}
		
		return super.getParameter(key);
	}

}
