package com.gistandard.transport.system.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gistandard.transport.system.common.bean.ErrorMessageBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author yujie
 * @ClassName BaseController
 * @Description
 * @Version 1.0
 * @Date 2015-06-08
 */
@Controller
public class BaseController {

    //自动化单元测试时，无request对象，导致注入失败，所以配置成required=false
    @Autowired(required=false)
    private  HttpServletRequest request;

    @Autowired(required=false)
    private HttpServletResponse response ;

    @Autowired(required=false)
    private HttpSession session ;



    /**
     *  json输出
     *  默认返回成功的消息对象
     * @param data 返回的json数据对象
     */
    public void writeResultJson(Object data){
        ResultBean resultBean = new ResultBean();
        resultBean.setData(data);
        writeJson(resultBean);
    }

    /**
     * 可设置返回消息状态
     * @param data 数据对象
     * @param state 返回返回数据的状态
     */
    public void writeResultJson(Object data, boolean state){
        ResultBean resultBean = new ResultBean();
        resultBean.setState(state);
        resultBean.setData(data);
        writeJson(resultBean);
    }

    /**
     * 可设置返回消息状态 和 返回信息
     * @param data 数据对象
     * @param state 返回返回数据的状态
     * @message 返回消息
     */
    public void writeResultJson(Object data,String message ,boolean state){
        ResultBean resultBean = new ResultBean();
        resultBean.setState(state);
        resultBean.setData(data);
        resultBean.setMessage(message);
        writeJson(resultBean);
    }

    /**
     * 关闭循环检测
     * @param resultBean
     */
    public void writeJsonDisableCircularReferenceDetect(Object resultBean){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(resultBean, SerializerFeature.DisableCircularReferenceDetect));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * json输出校验错误信息，默认返回json的状态是失败
     * @param errorList 校验错误列表
     */
    public void writeValidateErrorMessages(List<FieldError> errorList){
        ResultBean resultBean = new ResultBean();
        resultBean.setState(false);
        StringBuffer message = new StringBuffer();
        List<ErrorMessageBean> messageList = new ArrayList<ErrorMessageBean>();
        for(FieldError fieldError : errorList){
            ErrorMessageBean errorMessage = new ErrorMessageBean();
            errorMessage.setErrorField(fieldError.getField());
            errorMessage.setErrorMessage(fieldError.getDefaultMessage());
            errorMessage.setErrorObject(fieldError.getObjectName());
            messageList.add(errorMessage);
            message.append(fieldError.getDefaultMessage()).append("</br>");
        }
        resultBean.setErrorMessages(messageList);
        resultBean.setMessage(message.toString());
        writeJson(resultBean);
    }

    /**
     * 输出校验错误信息
     * @param errorList 校验错误列表
     */
    public String validateErrorMessages(List<FieldError> errorList){
        StringBuffer message = new StringBuffer();
        List<ErrorMessageBean> messageList = new ArrayList<ErrorMessageBean>();
        for(FieldError fieldError : errorList){
            ErrorMessageBean errorMessage = new ErrorMessageBean();
            errorMessage.setErrorField(fieldError.getField());
            errorMessage.setErrorMessage(fieldError.getDefaultMessage());
            errorMessage.setErrorObject(fieldError.getObjectName());
            messageList.add(errorMessage);
            message.append(fieldError.getDefaultMessage()).append("</br>");
        }
        return message.toString();
    }

    public HashMap<String, String> tranErrorListToMap(List<FieldError> errorList){
        HashMap<String, String> errorMap = new HashMap<String, String>();
        for(FieldError fieldError : errorList){
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorMap;
    }

    public void writeJson(Object resultBean){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(resultBean));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }


    public void writeJsonWithConfig(Object resultBean, SerializerFeature... features){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(resultBean));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 组装跳转路径公共方法
     * @param modulePath 跳转页面的相对路径
     */
	protected ModelAndView buildModelAndView(String modulePath) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(modulePath);
		return modelAndView;
	}
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
