package com.movietime.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-05-06.
 */
@Component
public class MyUserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyAuthenticationProxy myAuthenticationProxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        boolean result = myAuthenticationProxy.isValidUser(
                authentication.getPrincipal().toString(), authentication.getCredentials().toString());

        if (result) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            MyUserAuthenticationToken auth = new MyUserAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(), grantedAuthorities);

            return auth;

        } else {
            throw new BadCredentialsException("Bad user credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
