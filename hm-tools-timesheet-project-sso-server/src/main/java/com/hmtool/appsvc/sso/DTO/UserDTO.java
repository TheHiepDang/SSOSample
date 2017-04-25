package com.hmtool.appsvc.sso.DTO;

/**
 * Created by Hiep Dang on 4/17/2017.
 */
public class UserDTO extends BaseDTO {
    private String username;
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
