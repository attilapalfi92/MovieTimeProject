package com.movietime.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

/**
 * Created by Attila on 2015-05-06.
 * Service which loads user-specific data.
 * It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider.
 * Contains only one read-only method, which simplifies support for new data-access strategies.
 */
public class MyUserDetailsService implements UserDetailsService {

    private final MyClientDetailsService myClientDetailsService;

    //public MyUserDetailsService() {}

    public MyUserDetailsService(MyClientDetailsService myClientDetailsService) {
        this.myClientDetailsService = myClientDetailsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails = this.myClientDetailsService.loadClientByClientId(username);
        String clientSecret = clientDetails.getClientSecret();
        if(clientSecret == null || clientSecret.trim().length() == 0) {
            clientSecret = "";
        }

        return new User(username, clientSecret, clientDetails.getAuthorities());
    }
}
