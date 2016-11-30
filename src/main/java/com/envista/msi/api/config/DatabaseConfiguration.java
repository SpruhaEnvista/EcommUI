package com.envista.msi.api.config;

import java.util.Arrays;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("com.envista.msi.api.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Inject
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		log.debug("Configuring Datasource");
		DataSource dataSource = null;
		String dbUrl = dataSourceProperties.getUrl();

		if (StringUtils.isNotBlank(dataSourceProperties.getJndiName())
				&& !dataSourceProperties.getJndiName().startsWith("boot")) {
			try {
				final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
				dsLookup.setResourceRef(true);
				dataSource = dsLookup.getDataSource(dataSourceProperties.getJndiName().replace("\"", ""));
			} catch (DataSourceLookupFailureException e) {
				// Ignore nothing to initialize, reset dataSource value
				dataSource = null;
			}
		}
		if (dataSource != null) {
			return dataSource;
		}
		if (dbUrl == null) {
			log.error(
					"Your database connection pool configuration is incorrect! The application"
							+ " cannot start. Please check your Spring profile, current profiles are: {}",
					Arrays.toString(env.getActiveProfiles()));

			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder
				.create(dataSourceProperties.getClassLoader()).type(HikariDataSource.class)
				.driverClassName(dataSourceProperties.getDriverClassName().replace("\"", ""))
				.url(dbUrl.replace("\"", "")).username(dataSourceProperties.getUsername())
				.password(dataSourceProperties.getPassword()).build();
		return hikariDataSource;
	}

	@Bean
	public Hibernate4Module hibernate4Module() {
		return new Hibernate4Module();
	}
}
