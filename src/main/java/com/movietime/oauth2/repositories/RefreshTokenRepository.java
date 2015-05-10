package com.movietime.oauth2.repositories;

import com.movietime.oauth2.tokens.AccessToken;
import com.movietime.oauth2.tokens.RefreshToken;
import org.springframework.stereotype.Repository;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;


/**
 * Created by Attila on 2015-05-06.
 */
//@Repository
public class RefreshTokenRepository {

    public RefreshToken findByTokenId(String tokenId) {

        return null;
    }

    public void save(RefreshToken refreshToken) {

    }

    public void delete(RefreshToken tokenId) {

    }
}
