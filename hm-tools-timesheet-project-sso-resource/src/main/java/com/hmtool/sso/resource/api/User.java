package com.hmtool.sso.resource.api;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * Created by Hiep Dang on 4/18/2017.
 */
public interface User {
    /**
     * @param auth
     * @return clientNfo or userNfo depend on the strategy used to implemetn oAuth2 (As described in sso-server)
     */
    public Map<String, Object> getExtraInfo(OAuth2Authentication auth);
}
