package com.hmtool.appsvc.sso.domain;


import com.hmtool.appsvc.sso.common.ToStringAllFieldsSupport;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseDomain extends ToStringAllFieldsSupport implements Serializable {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getAuthoritiesData() {
        return id;
    }

    public void setAuthoritiesData(Integer id) {
        this.id = id;
    }*/
}
