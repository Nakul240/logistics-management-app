package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.logisticsmanangement.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
