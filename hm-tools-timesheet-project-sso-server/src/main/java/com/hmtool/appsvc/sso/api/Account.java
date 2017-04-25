package com.hmtool.appsvc.sso.api;

import com.hmtool.appsvc.sso.DTO.UserDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * Contains user related apis
 */
public interface Account {
    UserDTO getCurrentUser(HttpServletResponse response);
}
