package com.org.starter.pro.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.org.starter.pro.to.EmployeeTO;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeTO.class.equals(clazz);
	}

	@Override
	public void validate(Object input, Errors result) {
		EmployeeTO eto = (EmployeeTO) input;
		if(ObjectUtils.isEmpty(eto.getEid())) {
			result.rejectValue("eid", "EID Must be Provided...");
		}
		if(!validateDate(eto)) {
			result.rejectValue("dob", "Date of Birth Format must be in yyyy-MM-dd...");
		}
		if(ObjectUtils.isEmpty(eto.getName())) {
			result.rejectValue("name", "Name Must be Provided...");
		}
	}

	private boolean validateDate(EmployeeTO eto) {
		boolean response = true;
		response = isValidFormat("yyyy-MM-dd", eto.getDob());
		return response;
	}
	
	private boolean isValidFormat(String format, String value) {
		boolean valid;
		try {
			if(StringUtils.isNotEmpty(value)){
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date = sdf.parse(value);
				valid = true;
				if(!value.equals(sdf.format(date))) {
					valid = false;
				}
			} else {
				valid = true;
			}
		} catch (ParseException e) {
			return false;
		}
		return valid;
	}
}
