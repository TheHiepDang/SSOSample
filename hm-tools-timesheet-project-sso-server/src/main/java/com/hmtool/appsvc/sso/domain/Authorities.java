package com.hmtool.appsvc.sso.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {
    @EmbeddedId
    private AuthoritiesID authoritiesData;

    public AuthoritiesID getAuthoritiesData() {
        return authoritiesData;
    }

    public void setAuthoritiesData(AuthoritiesID authoritiesData) {
        this.authoritiesData = authoritiesData;
    }
}
