package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.logisticsmanangement.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
