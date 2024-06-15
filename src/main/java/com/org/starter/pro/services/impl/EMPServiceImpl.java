package com.org.starter.pro.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.org.starter.pro.entity.EmployeeEntity;
import com.org.starter.pro.repository.EmployeeRepository;
import com.org.starter.pro.services.EMPService;
import com.org.starter.pro.to.EmployeeTO;

@Service
public class EMPServiceImpl implements EMPService {

	@Autowired
	private EmployeeRepository empRepository;
	
	@Override
	public String saveEmployee(EmployeeTO eto) {
		EmployeeEntity oldEmp = empRepository.findByEidAndPhone(eto.getEid(), eto.getPhone());
		if (!ObjectUtils.isEmpty(oldEmp)) {
			copyNonNullProperties(eto, oldEmp);
			EmployeeEntity updatedEmployee = empRepository.save(oldEmp);
			return updatedEmployee.getName() + "'s Data Updated.. ";
		} else {
			EmployeeEntity entity = new EmployeeEntity();
			BeanUtils.copyProperties(eto, entity);
			EmployeeEntity savedEmployee = empRepository.save(entity);
			return savedEmployee.getName() + "'s Data Saved.. ";
		}
	}

}
