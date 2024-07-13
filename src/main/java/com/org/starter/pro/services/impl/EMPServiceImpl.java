package com.org.starter.pro.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.org.starter.pro.entity.AddressEntity;
import com.org.starter.pro.entity.EmployeeEntity;
import com.org.starter.pro.repository.EmployeeRepository;
import com.org.starter.pro.services.EMPService;
import com.org.starter.pro.to.EmployeeTO;

@Service
public class EMPServiceImpl implements EMPService {

	private EmployeeRepository empRepository;
	
	public EMPServiceImpl(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}
	
	@Override
	public String saveEmployee(EmployeeTO eto) {
		EmployeeEntity oldEmp = empRepository.findByEidAndPhone(eto.getEid(), eto.getPhone());
		if (!ObjectUtils.isEmpty(oldEmp)) {
			copyNonNullProperties(eto, oldEmp);
			oldEmp.setDob(getDateFromString(eto.getDob()));
			AddressEntity addressEntity = oldEmp.getAddress();
			copyNonNullProperties(eto.getAddress(), addressEntity);
			EmployeeEntity updatedEmployee = empRepository.save(oldEmp);
			return updatedEmployee.getName() + "'s Data Updated.. ";
		} else {
			EmployeeEntity entity = new EmployeeEntity();
			BeanUtils.copyProperties(eto, entity);
			entity.setDob(getDateFromString(eto.getDob()));
			AddressEntity addEntity = new AddressEntity();
			BeanUtils.copyProperties(eto.getAddress(), addEntity);
			entity.setAddress(addEntity);
			EmployeeEntity savedEmployee = empRepository.save(entity);
			return savedEmployee.getName() + "'s Data Saved.. ";
		}
	}
	
	private Date getDateFromString(String stringDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

}
