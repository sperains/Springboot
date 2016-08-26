package com.rains.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2016-08-22.
 */

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Autowired
    DataSource dataSource;


    /**
     * 自定义数据源,覆盖默认配置
     * @return
     */
	/*@Bean
	@ConfigurationProperties(prefix="custom.datasource")
	public DataSource dataSource() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}*/


    /**
     * 配置mybatis事务管理
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.rains.dao");
        return mapperScannerConfigurer;
    }*/


    /**
     * 手动配置SqlSessionFactory
     * @return
     */
	/*@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.rains.entity");

		return sqlSessionFactoryBean.getObject();
	}*/



}
