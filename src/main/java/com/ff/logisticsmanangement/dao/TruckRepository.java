package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.logisticsmanangement.entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

}
