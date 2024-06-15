package com.org.starter.pro.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.org.starter.pro.utils.EMPConstant;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.org.starter.pro.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "platformTransactionManager")
public class EmployeeDBConfiguration {
	
	@Autowired
	private DBConfig dbConfig;

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

    @Bean
    @Autowired
    PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put(EMPConstant.DIALECT_KEY, EMPConstant.DIALECT_VALUE);
		properties.put("hibernate.dialect", "com.org.starter.pro.config.DialectConfig");
		properties.put(EMPConstant.SHOW_SQL_KEY, EMPConstant.SHOW_SQL_VALUE);
		properties.put(EMPConstant.FORMAT_SQL_KEY, EMPConstant.FORMAT_SQL_VALUE);
		properties.put("spring.jpa.hibernate.ddl-auto", "create");
		return properties;
	}

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dbConfig.dataSource());
		factoryBean.setPackagesToScan(new String [] {"com.org.starter.pro.entity"});
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
}
