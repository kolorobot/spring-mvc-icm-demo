package com.github.kolorobot.icm.operator;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionStateVerificationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(!pathMatches(request) && OperatorHelper.getOperatorId(request, response) == null) {
			request.getRequestDispatcher("/operator").forward(request, response);
		}
		filterChain.doFilter(request, response);
	}

	private boolean pathMatches(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		return uri.equals(contextPath + "/operator") || uri.startsWith(contextPath + "/resources/");
	}
}
