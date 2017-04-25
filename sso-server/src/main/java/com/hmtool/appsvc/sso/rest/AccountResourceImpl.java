package com.hmtool.appsvc.sso.rest;

import com.hmtool.appsvc.sso.DTO.UserDTO;
import com.hmtool.appsvc.sso.api.Account;

import javax.servlet.http.HttpServletResponse;

/**
 * Add CRUD for user
 */
public class AccountResourceImpl implements Account {
    @Override
    public UserDTO getCurrentUser(HttpServletResponse response) {
        return null;
    }
}
