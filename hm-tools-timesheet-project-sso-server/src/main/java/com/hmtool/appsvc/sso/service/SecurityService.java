package com.hmtool.appsvc.sso.service;

import com.hmtool.appsvc.sso.domain.Authorities;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

public interface SecurityService {
    OAuth2AccessToken getAccessToken();
    List<Authorities> getAuthorities(String usercode);
}
