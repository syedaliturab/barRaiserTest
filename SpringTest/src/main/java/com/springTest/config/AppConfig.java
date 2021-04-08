package com.springTest.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:mysql.properties")
@ComponentScan("com.springTest")
public class AppConfig {
	@Autowired
	Environment env;
	@Bean
	ViewResolver viewResolver()
	{
		InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	@Bean
	DataSource dataSource()
	{
		ComboPooledDataSource combo=new ComboPooledDataSource();
		try {
			combo.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		combo.setJdbcUrl(env.getProperty("jdbc.url"));
		combo.setUser(env.getProperty("jdbc.user"));
		combo.setPassword(env.getProperty("jdbc.password"));
		combo.setInitialPoolSize(IntProperty("connection.pool.intitialPoolSize"));
		combo.setMinPoolSize(IntProperty("connection.pool.minPoolSize"));
		combo.setMaxPoolSize(IntProperty("connection.pool.maxPoolSize"));
		combo.setMaxIdleTime(IntProperty("coonection.pool.maxIdleTime"));
		return combo;
	}
	@Bean
LocalSessionFactoryBean sessionFactory()
{
		LocalSessionFactoryBean local=new LocalSessionFactoryBean();
		local.setDataSource(dataSource());
		local.setPackagesToScan("com.springTest.entites");
		local.setHibernateProperties(getProperties());
		return local;
}
	@Bean
	PlatformTransactionManager teansaction()
	{
		HibernateTransactionManager pt=new HibernateTransactionManager();
		pt.setSessionFactory(sessionFactory().getObject());
		return pt;
		
	}
	
	private Properties getProperties() {
		Properties prop=new Properties();
		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return prop;
	}
	private int IntProperty(String str)
	{
		String st=env.getProperty(str);
		return Integer.parseInt(st);
	}

}
