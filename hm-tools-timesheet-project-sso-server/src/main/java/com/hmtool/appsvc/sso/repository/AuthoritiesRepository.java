package com.hmtool.appsvc.sso.repository;

import com.hmtool.appsvc.sso.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>, JpaSpecificationExecutor<Authorities> {
    @Query("from Authorities au where au.authoritiesData.username = :username")
    Optional<List<Authorities>> findByUsername(@Param("username") String username);
}
