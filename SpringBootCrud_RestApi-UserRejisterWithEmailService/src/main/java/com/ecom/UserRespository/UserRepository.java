package com.ecom.UserRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	boolean existsByMobile(String mobile);

	Users findByMobile(String mobile);
	
	
}
