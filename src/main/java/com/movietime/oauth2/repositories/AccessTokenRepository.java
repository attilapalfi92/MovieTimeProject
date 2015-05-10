package com.movietime.oauth2.repositories;

import com.movietime.oauth2.tokens.AccessToken;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Attila on 2015-05-06.
 */
@Repository
public class AccessTokenRepository {

    public AccessToken findByTokenId(String tokenId) {

        return null;
    }

    public AccessToken findByRefreshToken(String refreshToken) {

        return null;
    }

    public AccessToken findByAuthenticationId(String authenticationId) {

        return null;
    }

    public List<AccessToken> findByClientIdAndUserName(String clientId, String userName) {

        return null;
    }

    public List<AccessToken> findByClientId(String clientId) {

        return null;
    }

    public void save(AccessToken accessToken) {

    }

    public void delete(AccessToken accessToken) {

    }
}
