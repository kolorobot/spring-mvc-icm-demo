package com.github.kolorobot.icm.operator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

final class OperatorHelper {
	
	private static final String OPERATOR_ID = "operatorId";
	private static final String OPERATOR_COOKIE = "OPERATOR_ID_COOKIE";

	static void setOperatorCookie(HttpServletRequest request,
			HttpServletResponse response, String operatorId) {
		
		Cookie cookie = new Cookie(OPERATOR_COOKIE, operatorId);
		cookie.setMaxAge(60 * 60 * 48);
		response.addCookie(cookie);
		
		HttpSession session = request.getSession();
		session.setAttribute(OPERATOR_ID, operatorId);
	}
	
	static String getOperatorId(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Cookie operatorCookie = WebUtils.getCookie(request, OPERATOR_COOKIE);
		String operatorId = (String) session.getAttribute(OPERATOR_ID);
		
		if (operatorCookie != null && operatorId == null) {
			operatorId = operatorCookie.getValue();
		}
		
		if (operatorId != null) {
			setOperatorCookie(request, response, operatorId);
		}
		
		return operatorId;
	}
	
}
