package com.hmtool.common.security.interceptor;

import com.hmtool.common.security.util.HMUserUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Global interceptor responsible for adding userInfo and access_token into request header (If available)
 * Add/remove as needed. UserCode is just a generic example
 */
public class HttpClientInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientInterceptor.class);
    private static final String USER_CODE = "userCode";
    private static final String ACCESS_TOKEN = "access_token";
    @Autowired
    private HMUserUtility HMUserUtility;
    @Autowired
    private HttpServletRequest servletRequest;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        injectDataIntoHeader(requestWrapper);
        return execution.execute(requestWrapper, body);
    }

    private void injectDataIntoHeader(HttpRequestWrapper requestWrapper) {
        injectAccessToken(requestWrapper);
        if (HMUserUtility.isUserInContext()) {
            injectUserData(requestWrapper, USER_CODE, HMUserUtility.getCurrentUserCode());
        } else {
            LOG.debug("Usercode not found!");
        }
    }

    /**
     * Request will get suspended immediately if forwarded without a valid access_token
     *
     * @param requestWrapper
     */
    private void injectAccessToken(HttpRequestWrapper requestWrapper) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ACCESS_TOKEN.equals(cookie.getName())) {
                    injectDataToHeader(requestWrapper, "Authentication", "Bearer " + cookie.getValue());
                    break;
                }
            }
        }
    }

    private void injectUserData(HttpRequestWrapper requestWrapper, String dataname, String data) {
        injectDataToHeader(requestWrapper, dataname, data);
    }

    private void injectDataToHeader(HttpRequestWrapper requestWrapper, String dataname, String data) {
        HttpHeaders headers = requestWrapper.getHeaders();
        addHeader(headers, dataname, data);
    }

    private void addHeader(HttpHeaders headers, String headerName, String headerValue) {
        Optional.ofNullable(headerValue).ifPresent(code -> headers.add(headerName, headerValue));
    }
}
