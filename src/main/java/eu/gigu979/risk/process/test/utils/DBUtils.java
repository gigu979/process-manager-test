package eu.gigu979.risk.process.test.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * The Class DBUtils.
 */
public final class DBUtils {

	private static JdbcTemplate JDBC_TEMPLATE;

	private static void initJdbc() throws ClassNotFoundException {
		if (JDBC_TEMPLATE == null) {
			Class.forName(ConfigUtils.getJdbcDriver());
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(ConfigUtils.getJdbcDriver());
			dataSource.setUrl(ConfigUtils.getJdbcUrl());
			dataSource.setUsername(ConfigUtils.getJdbcUser());
			dataSource.setPassword(ConfigUtils.getJdbcPass());
			JDBC_TEMPLATE = new JdbcTemplate(dataSource);
		}
	}

	private DBUtils() {

	}


	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static JdbcTemplate getJdbcTemplate() throws ClassNotFoundException {
		initJdbc();
		return JDBC_TEMPLATE;
	}
}