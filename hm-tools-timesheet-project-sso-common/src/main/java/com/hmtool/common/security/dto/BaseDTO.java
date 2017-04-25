package com.hmtool.common.security.dto;

import com.hmtool.common.security.common.ToStringAllFieldsSupport;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Generic utility class that's to be inherited by every DTO that needs these features
 */
public abstract class BaseDTO extends ToStringAllFieldsSupport {

    public BaseDTO() {
    }

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, false);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
