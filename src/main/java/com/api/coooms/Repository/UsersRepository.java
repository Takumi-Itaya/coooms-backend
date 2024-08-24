package com.api.coooms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.coooms.Model.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);
	
	@Transactional
	void deleteByEmail(String email);
}
