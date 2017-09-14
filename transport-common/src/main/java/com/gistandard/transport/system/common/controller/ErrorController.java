package com.gistandard.transport.system.common.controller;

import com.gistandard.transport.base.define.SystemDefine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yujie
 * @ClassName ErrorController
 * @Description
 * @Version 1.0
 * @Date 2015-08-15
 */
@Controller
@RequestMapping(value = SystemDefine.SYS_ERROR)
@springfox.documentation.annotations.ApiIgnore
public class ErrorController {

    @RequestMapping(value = {"/notFound"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView notFound(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/notFound");
        return modelAndView;
    }

    @RequestMapping(value = {"/internalError"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView internalError(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/internalError");
        return modelAndView;
    }

    @RequestMapping(value = {"/notHasRight"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView notHasRight(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/unauthorized");
        return modelAndView;
    }

}
