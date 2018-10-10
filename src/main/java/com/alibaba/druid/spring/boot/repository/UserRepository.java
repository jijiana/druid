package com.alibaba.druid.spring.boot.repository;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.spring.boot.entity.User;

@Repository
//@Scope("prototype")
@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<User, Long>,PagingAndSortingRepository<User, Long> {
	@Lock(value=LockModeType.PESSIMISTIC_READ)
	@Query(value="select o from com.alibaba.druid.spring.boot.entity.User o where o.id= :id ")
	User findUserByIdForUpdate(@Param("id") Long id);
}
