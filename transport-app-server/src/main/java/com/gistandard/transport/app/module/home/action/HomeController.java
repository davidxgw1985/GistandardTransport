package com.gistandard.transport.app.module.home.action;

import com.gistandard.transport.system.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yujie on 2016/9/30.
 */
@Controller
@springfox.documentation.annotations.ApiIgnore
public class HomeController extends BaseController{

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView goHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
