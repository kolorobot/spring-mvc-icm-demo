package com.github.kolorobot.icm.support.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AjaxViewException.class)	
	public ModelAndView ajaxViewException(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("ajaxError");
		addError(exception, modelAndView);
		return modelAndView;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)	
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		addError(exception, modelAndView);
		return modelAndView;
	}
	
	private void addError(Exception exception, ModelAndView modelAndView) {
		modelAndView.addObject("rootCause", Throwables.getRootCause(exception));
		modelAndView.addObject("stackTraceAsString", Throwables.getStackTraceAsString(exception));
	}
}
