package com.hmtool.appsvc.sso.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuthoritiesID implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AuthoritiesID)) return false;

        AuthoritiesID that = (AuthoritiesID) o;

        return new EqualsBuilder()
                .append(getUsername(), that.getUsername())
                .append(getAuthority(), that.getAuthority())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUsername())
                .append(getAuthority())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
