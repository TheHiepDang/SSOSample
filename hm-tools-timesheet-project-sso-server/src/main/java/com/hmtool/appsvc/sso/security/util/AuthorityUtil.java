package com.hmtool.appsvc.sso.security.util;

import com.hmtool.appsvc.sso.DTO.UserDTO;
import com.hmtool.appsvc.sso.domain.Authorities;
import com.hmtool.appsvc.sso.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.hmtool.appsvc.sso.common.RoleMatrix.APPROVER;

/**
 * Resolve access_token scope using userNfo
 */
@Component
public class AuthorityUtil {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDTO currentUser;

    public List<String> getScopes() {
        List<String> scopes = new ArrayList<>();
        List<Authorities> authorities = securityService.getAuthorities(currentUser.getUsername());
        //Now we return a list of scopes depend on how we want it to be with this given set of authorities (TBD work that utilizes the role matrix)
        //Below is an example
        for (Authorities authority : authorities) {
            if (authority.getAuthoritiesData().getAuthority().equals("ADMIN")) {
                scopes.add("read");
                scopes.add("write");
            } else if (authority.getAuthoritiesData().getAuthority().equals("USER")) {
                scopes.add("read");
            }
            //Example of handling role and permission
            else if (authority.getAuthoritiesData().getAuthority().equals(APPROVER.getRole())) {
                scopes.addAll(APPROVER.getPermission());
            } /*else if (authority.getAuthoritiesData().getAuthority().equals(ADMIN.getRole())) {
                scopes.addAll(ADMIN.getPermission());
            }*/
        }
        return scopes;
    }
}
