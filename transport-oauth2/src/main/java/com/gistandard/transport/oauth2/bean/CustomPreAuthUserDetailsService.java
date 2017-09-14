package com.gistandard.transport.oauth2.bean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by shenzhijun on 2016/10/10.
 */
@Service
public class CustomPreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        Assert.notNull(token.getDetails());
        Assert.isInstanceOf(GrantedAuthoritiesContainer.class, token.getDetails());
        Collection<? extends GrantedAuthority> authorities = ((GrantedAuthoritiesContainer) token.getDetails()).getGrantedAuthorities();
        return createUserDetails(token, authorities);
    }

    protected UserDetails createUserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities) {
        return createuserDetails(token, authorities);
    }

    @Deprecated
    protected UserDetails createuserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities) {
        return new User(token.getName(), "N/A", true, true, true, true, authorities);
    }
}
