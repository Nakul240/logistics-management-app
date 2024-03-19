package com.ff.logisticsmanangement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.logisticsmanangement.entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

	public List<Truck> findByCarrierId(int carrierId);
}
