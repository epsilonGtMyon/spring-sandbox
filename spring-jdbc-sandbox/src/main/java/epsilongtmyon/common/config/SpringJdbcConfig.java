package epsilongtmyon.common.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class SpringJdbcConfig {

	@Bean
	HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:tcp://localhost/test");
		config.setUsername("sa");
		config.setPassword("");

		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean
	DataSourceTransactionManager transactionManager(DataSource dataSource) {
		JdbcTransactionManager jdbcTransactionManager = new JdbcTransactionManager(dataSource);
		return jdbcTransactionManager;
	}
}
