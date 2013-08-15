package com.thomas.test.spring.contactor.services.config;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ImportResource("classpath:jpa-app-context.xml")
@Profile("test")
public class ServiceTestConfig {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean(name = "databaseTester")
	public DataSourceDatabaseTester dataSourceDatabaseTester() {
		DataSourceDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource());
		return databaseTester;
	}

	@Bean(name = "xlsDataFileLoader")
	public XlsDataFileLoader xlsDataFileLoader() {
		return new XlsDataFileLoader();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		Resource[] resourceLocations = new Resource[] { new ClassPathResource("domain.properties"),
				new ClassPathResource("services.properties"), };
		p.setLocations(resourceLocations);
		return p;
	}
}
