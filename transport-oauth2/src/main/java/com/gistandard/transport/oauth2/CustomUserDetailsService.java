package com.gistandard.transport.oauth2;


import com.gistandard.transport.oauth2.config.OAuth2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息
 */
@Component
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private OAuth2Config oAuth2Configurer;

    /**
     * 获取用户信息
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        return oAuth2Configurer.getSecurityUser(userName);
    }
}
