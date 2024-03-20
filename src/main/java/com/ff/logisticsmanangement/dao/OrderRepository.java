package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.logisticsmanangement.entity.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer>{

}
