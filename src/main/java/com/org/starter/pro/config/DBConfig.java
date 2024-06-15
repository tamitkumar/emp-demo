package com.org.starter.pro.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.org.starter.pro.exception.EMPException;
import com.org.starter.pro.exception.ErrorCode;
import com.org.starter.pro.exception.ErrorSeverity;
import com.org.starter.pro.utils.EMPConstant;

@Configuration
public class DBConfig {

    @Bean
    DataSource dataSource() {
		String dbUser = EMPConstant.DB_USER_NAME;
		String dbPassword = EMPConstant.DB_PASSWORD;
		String driverClassName = EMPConstant.DB_DRIVER_CLASS_NAME;
		DriverManagerDataSource ds = new DriverManagerDataSource(getDBUrl(), dbUser, dbPassword);
		try {
			ds.setDriverClassName(driverClassName);
		} catch (Exception e) {
			throw new EMPException(ErrorCode.ERR002.getErrorCode(), ErrorSeverity.FATAL,
					ErrorCode.ERR002.getErrorMessage(), e);
		}
		try {
			ds.getConnection().close();
		} catch (SQLException e) {
			throw new EMPException(ErrorCode.ERR002.getErrorCode(), ErrorSeverity.FATAL,
					ErrorCode.ERR002.getErrorMessage(), e);
		}
		return ds;
	}
	
	private String getDBUrl() {
		String dbHost = EMPConstant.DB_HOST;
		String dbPort = EMPConstant.DB_PORT;
		String dbName = EMPConstant.DB_NAME;
		String dbUrlPrefix = EMPConstant.DB_URL_PREFIX;
		StringBuilder baseUrl = new StringBuilder(dbUrlPrefix);
		baseUrl.append(dbHost);
		baseUrl.append(EMPConstant.COLON);
		baseUrl.append(dbPort);
//		baseUrl.append(EMPConstant.COLON);
		baseUrl.append(dbName);
		return baseUrl.toString();
	}
}
