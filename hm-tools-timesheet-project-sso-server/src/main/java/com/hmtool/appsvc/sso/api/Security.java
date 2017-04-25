package com.hmtool.appsvc.sso.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletResponse;

public interface Security {
    /**
     * This method returns an access token, along with injects (Implicitly) that token into cookie so that it can be consumed by clients later
     *
     * @param sResponse
     * @return An access token
     */
    OAuth2AccessToken getAccessToken(HttpServletResponse sResponse);

    void logout(HttpServletRequest request);
}
