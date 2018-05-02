package com.xuexi.v2.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MyDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mySqlSessionFactory")
public class MyDataSourceConfig {

	// 精确到 core 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.xuexi.v2.mapper";
	static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;

	@Value("${spring.datasource.type}")
	private String type;
	
	@Bean(name = "myDataSource")
	@Primary
	public DataSource coreDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setDbType(type);
		return dataSource;
	}

	@Bean(name = "myTransactionManager")
	@Primary
	public DataSourceTransactionManager coreTransactionManager() {
		return new DataSourceTransactionManager(coreDataSource());
	}

	@Bean(name = "mySqlSessionFactory")
	@Primary
	public SqlSessionFactory coreSqlSessionFactory(@Qualifier("myDataSource") DataSource coreDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(coreDataSource);
		sessionFactory.setTypeAliasesPackage("com.xuexi.v2.domain");
		//sessionFactory.setConfigLocation(configLocation);
		  //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);

        //添加插件
       // bean.setPlugins(new Interceptor[]{pageHelper});

		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(MyDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
}
