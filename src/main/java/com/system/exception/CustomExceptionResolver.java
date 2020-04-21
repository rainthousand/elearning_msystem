package com.system.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();

        CustomException customException;
        if (e instanceof CustomException) {
            customException = (CustomException)e;
        } else if (e instanceof UnknownAccountException) {
            
            modelAndView.addObject("message", "no such user");
            modelAndView.setViewName("error");
            return modelAndView;
        } else if (e instanceof IncorrectCredentialsException) {
            
            modelAndView.addObject("message", "wrong password");
            modelAndView.setViewName("error");
            return modelAndView;
        } else {
            customException = new CustomException("unknown error");
        }

        String message = customException.getMessage();

        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");


        return modelAndView;
    }
}
