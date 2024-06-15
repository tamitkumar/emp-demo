package com.org.starter.pro.config;

import org.hibernate.dialect.MySQL8Dialect;

public class DialectConfig extends MySQL8Dialect {

	@Override
	public String getDropSequenceString(String sequenceName) {
		return "drop sequence if exists " + sequenceName;
	}
	
	@Override
	public boolean dropConstraints() {
		return false;
	}
}
