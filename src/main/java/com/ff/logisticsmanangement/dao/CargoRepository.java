package com.ff.logisticsmanangement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ff.logisticsmanangement.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
