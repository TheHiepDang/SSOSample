package com.hmtool.common.security.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Generic toString that's to be inherited by BaseDTO
 */
public abstract class ToStringAllFieldsSupport {
    private static final String[] EXCLUDED_FIELD_NAMES_FROM_TOSTRING = new String[]{"password"};

    public ToStringAllFieldsSupport() {
    }

    public String toString() {
        return (new ReflectionToStringBuilder(this)).setExcludeFieldNames(EXCLUDED_FIELD_NAMES_FROM_TOSTRING).toString();
    }
}
