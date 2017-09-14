package com.gistandard.transport.oauth2.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by shenzhijun on 2016/10/10.
 */
@Component("preAuthProvider")
public class CustomPreAuthProvider extends PreAuthenticatedAuthenticationProvider {

    @Autowired
    private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> userService;

    public CustomPreAuthProvider(){
        super();
    }

    @PostConstruct
    public void init(){
        super.setPreAuthenticatedUserDetailsService(userService);
    }
}
