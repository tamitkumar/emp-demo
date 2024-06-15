package com.org.starter.pro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.starter.pro.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
	public EmployeeEntity findByEidAndPhone(Long eid, Long phone);
}
