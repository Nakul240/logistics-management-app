package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.logisticsmanangement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName(String userName);

}
