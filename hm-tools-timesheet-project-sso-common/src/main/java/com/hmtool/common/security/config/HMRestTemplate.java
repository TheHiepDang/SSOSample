package com.hmtool.common.security.config;

import com.hmtool.common.security.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

/**
 * To be injected into every ui project, with values read from application.prop
 */
public class HMRestTemplate {

    @Autowired
    private UserDTO currentUser;

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    public OAuth2RestTemplate getRestTemplate() {
        if (currentUser.getUsername().equals("user"))
            ((ClientCredentialsResourceDetails) oAuth2ProtectedResourceDetails).setScope(Arrays.asList("read"));
        else
            ((ClientCredentialsResourceDetails) oAuth2ProtectedResourceDetails).setScope(Arrays.asList("write"));
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }
}
