package com.github.kolorobot.icm.support.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(value = Exception.class)	
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		modelAndView.addObject("rootCause", Throwables.getRootCause(exception));
		modelAndView.addObject("stackTraceAsString", Throwables.getStackTraceAsString(exception));
		return modelAndView;
	}
}
