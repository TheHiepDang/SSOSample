package com.hmtool.common.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * To use this: Config http as below inside your UI projects
 * protected void configure(HttpSecurity http) throws Exception {
 * http.exceptionHandling().authenticationEntryPoint(PreAuthenticationEntryPoint);
 * }
 */
public class PreAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint implements InitializingBean {
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public PreAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //When auth fail, do whatever is needed here e.g logging, checking cookies for access_token and re-attempting
        //Then redirect to places
        
        /*if (fail) {*/
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        /*}*/
        
        /*or else, redirect to sso with current URL after succeeded logging in
        * URL Handling method is up to future implementation
        * */
        response.sendRedirect(super.getLoginFormUrl() + "?returnto=" + request.getServletPath());
    }
}
