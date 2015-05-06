package com.movietime.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-05-06.
 */
public class MyGuestService implements ClientDetailsService {
    private String id;
    private String secretKey;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        if (clientId.equals(id)) {
            List<String> authorizedGrantTypes = new ArrayList<>();
            authorizedGrantTypes.add("password");
            authorizedGrantTypes.add("refresh_token");
            authorizedGrantTypes.add("client_credentials");
            authorizedGrantTypes.add("implicit");
            authorizedGrantTypes.add("redirect");

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_CLIENT";
                }
            });

            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_TRUSTED_CLIENT";
                }
            });

            List<String> scopes = new ArrayList<>();
            scopes.add("read");
            scopes.add("write");
            scopes.add("trust");

//            authorities="ROLE_CLIENT, ROLE_TRUSTED_CLIENT"
//            redirect-uri="/web
//            scope="read,write,trust"
//            access-token-validity="30"
//            refresh-token-validity="600"

            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId(id);
            clientDetails.setClientSecret(secretKey);
            clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
            clientDetails.setAuthorities(authorities);
            clientDetails.setScope(scopes);
            clientDetails.setAccessTokenValiditySeconds(30);
            clientDetails.setRefreshTokenValiditySeconds(600);

            return clientDetails;

        } else {
            throw new NoSuchClientException("No client recognized with id: " + clientId);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
