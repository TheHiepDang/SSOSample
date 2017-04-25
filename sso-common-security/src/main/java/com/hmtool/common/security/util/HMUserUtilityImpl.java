package com.hmtool.common.security.util;

import com.hmtool.common.security.domain.HMUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class HMUserUtilityImpl implements HMUserUtility {

    @Override
    public HMUser getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext.getAuthentication() == null) {
            return null;
        }

        Object principalObj = securityContext.getAuthentication().getPrincipal();
        if (principalObj instanceof HMUser) {
            return (HMUser) principalObj;
        } else {
            return null;
        }
    }

    @Override
    public boolean isUserInContext() {
        return getCurrentUser() != null;
    }

    @Override
    public String getCurrentUserCode() {
        HMUser currentUser = getCurrentUser();
        return currentUser.getUsername();
    }
//Add more getter here in-case HMUser has more than just one attribute
}
