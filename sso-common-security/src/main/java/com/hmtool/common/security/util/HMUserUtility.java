package com.hmtool.common.security.util;

import com.hmtool.common.security.domain.HMUser;

public interface HMUserUtility {
    HMUser getCurrentUser();

    boolean isUserInContext();

    String getCurrentUserCode();
}
