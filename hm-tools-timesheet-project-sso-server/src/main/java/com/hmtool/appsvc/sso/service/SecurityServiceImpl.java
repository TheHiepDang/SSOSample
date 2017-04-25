package com.hmtool.appsvc.sso.service;

import com.hmtool.appsvc.sso.DTO.UserDTO;
import com.hmtool.appsvc.sso.domain.Authorities;
import com.hmtool.appsvc.sso.repository.AuthoritiesRepository;
import com.hmtool.appsvc.sso.security.util.AuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Autowired
    private UserDTO currentUser;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private AuthorityUtil authorityUtil;

    @Value("${security.oauth2.client.scopes}")
    private String[] SCOPES;

    @Override
    public OAuth2AccessToken getAccessToken() {
        ((ClientCredentialsResourceDetails) oAuth2ProtectedResourceDetails).setScope(authorityUtil.getScopes());
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
        return oAuth2RestTemplate.getAccessToken();
    }

    @Override
    public List<Authorities> getAuthorities(String usercode) {
        return authoritiesRepository.findByUsername(usercode).get();
    }
}
