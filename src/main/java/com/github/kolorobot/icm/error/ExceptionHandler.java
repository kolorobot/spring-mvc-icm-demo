package com.github.kolorobot.icm.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

@ControllerAdvice
class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AjaxRequestException.class)	
	public ModelAndView ajaxRequestException(Exception exception, WebRequest request) {
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
