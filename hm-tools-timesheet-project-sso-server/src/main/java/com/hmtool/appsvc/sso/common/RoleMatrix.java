package com.hmtool.appsvc.sso.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum RoleMatrix {
    APPROVER(Arrays.asList("TIMESHEET_APPROVAL", "EXPENSE_APPROVAL")),
    ADMIN(Collections.singletonList("ALMIGHTY"));
    private final List<String> permission;

    /**
     * @param permission
     */
    private RoleMatrix(final List<String> permission) {
        this.permission = permission;
    }

    public String getRole() {
        return this.name();
    }

    public List<String> getPermission() {
        return permission;
    }
}
