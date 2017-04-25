package com.hmtool.appsvc.sso.rest;

import com.hmtool.appsvc.sso.api.Security;
import com.hmtool.appsvc.sso.security.oauth2.OAuth2JDBCTokenStore;
import com.hmtool.appsvc.sso.service.SecurityService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/app/rest/security")
public class SecurityResourceImpl implements Security {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private OAuth2JDBCTokenStore tokenStore;

    @Override
    @RequestMapping(value = "/accessToken",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public OAuth2AccessToken getAccessToken(HttpServletResponse sResponse) {
        OAuth2AccessToken accessToken = securityService.getAccessToken();
        Cookie accessTokenCookie = setupCookie(accessToken);
        sResponse.addCookie(accessTokenCookie);
        return accessToken;
    }

    private Cookie setupCookie(OAuth2AccessToken accessToken) {
        Cookie accessTokenCookie = new Cookie("access_token", accessToken.getValue());
        accessTokenCookie.setDomain("http://hmte.vn");
        accessTokenCookie.setPath("/hmte");
        accessTokenCookie.setMaxAge(accessToken.getExpiresIn());
        accessTokenCookie.setHttpOnly(true);
        return accessTokenCookie;
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout(HttpServletRequest request) {
        if (securityService.getAccessToken() != null) {
            tokenStore.removeAccessToken(securityService.getAccessToken());
        }
    }
}
