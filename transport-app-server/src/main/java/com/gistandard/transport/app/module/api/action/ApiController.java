package com.gistandard.transport.app.module.api.action;

import com.gistandard.transport.app.system.common.define.AppServerDefine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;


/**
 * Created by yujie on 2016/7/21.
 */
@ApiIgnore
@Controller
@RequestMapping(value = AppServerDefine.API_URL)
public class ApiController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView goAccessSystemManagePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("api");
        return modelAndView;
    }
}
