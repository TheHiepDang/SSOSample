package com.hmtool.appsvc.sso.security.oauth2;

import com.hmtool.appsvc.sso.DTO.UserDTO;
import com.hmtool.appsvc.sso.config.CustomTokenEnhancer;
import com.hmtool.appsvc.sso.service.OAuth2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableAuthorizationServer
@EnableOAuth2Client
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private OAuth2UserDetailsService userDetailsService;

    @Value("${security.oauth2.client.access-token-uri}")
    private String ACCESS_TOKEN_URI;

    @Value("${security.oauth2.client.scopes}")
    private String[] SCOPES;

    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;

    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;

    @Value("${security.oauth2.client.grant-type}")
    private String GRANT_TYPE;

    @Bean
    public UserDTO currentUser() {
        return new UserDTO();
    }

    @Bean
    public OAuth2JDBCTokenStore tokenStore() {
        return new OAuth2JDBCTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(ACCESS_TOKEN_URI);
        resource.setClientId(CLIENT_ID);
        resource.setClientSecret(CLIENT_SECRET);
        resource.setGrantType(GRANT_TYPE);
        return resource;
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource)
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .accessTokenValiditySeconds(1800);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(tokenEnhancer()));
        endpoints
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeServices())
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");//TODO: isAuthenticated() access
    }
}
