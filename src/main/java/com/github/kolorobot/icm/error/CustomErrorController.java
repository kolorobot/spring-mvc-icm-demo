package com.github.kolorobot.icm.error;

import com.github.kolorobot.icm.support.thymeleaf.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Layout("layouts/blank")
class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(ERROR_PATH)
    public ModelAndView error(HttpServletRequest request) {
        return new ModelAndView("error/error", getErrorAttributes(request));
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, true);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
