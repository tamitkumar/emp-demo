package com.org.starter.pro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.starter.pro.exception.ErrorCode;
import com.org.starter.pro.services.EMPService;
import com.org.starter.pro.to.EmployeeTO;
import com.org.starter.pro.utils.EMPConstant;
import com.org.starter.pro.utils.EmployeeValidator;

@RestController
@RequestMapping("/employee/v1")
public class EMPController {

	private final EmployeeValidator validator;
	
	private final EMPService service;
	
	public EMPController(EmployeeValidator validator, EMPService service) {
		this.validator = validator;
		this.service = service;
	}
	
	@PostMapping(value ="/saveAndUpdate")
	public List<String> saveEmployeeAndAddress(@RequestBody EmployeeTO eto, BindingResult result){
		List<String> respBody = new ArrayList<>();
		validator.validate(eto, result);
		if(!result.hasErrors()) {
			respBody.add(service.saveEmployee(eto));
		} else {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				respBody.add(ErrorCode.ERR002.getErrorCode()+EMPConstant.HYPHEN+ error.getCode());
			}
		}
		return respBody;		
	}
}
