package com.mmt.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mmt.enterprise.entity.EnterpriseUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnterpriseRepository extends JpaRepository<EnterpriseUser, Long>{

    @Query(value="select a.* from enterprise_user a where a.name=:name", nativeQuery = true)
    EnterpriseUser getEnterpriseByName(@Param("name") String name);

    @Query(value="select a.* from enterprise_user a where a.name=:name and a.password=:password", nativeQuery = true)
    EnterpriseUser getEnterpriseByNameAndPwd(@Param("name") String name, @Param("password") String password);
}
