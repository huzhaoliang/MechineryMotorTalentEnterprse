package com.mmt.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.enterprise.entity.User;

@Repository("UserRepository")
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long>{
	
	@Query(value="select a.* from user a where a.name=:name", nativeQuery = true)
	User getUserByName(@Param("name") String name);
	
	@Modifying
	@Transactional
	@Query(value="update user set status=:status where id=:id", nativeQuery = true)
	void updateUserStatusById(@Param("id") Long id, @Param("status") Integer status);
}
