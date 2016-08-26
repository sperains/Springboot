package com.rains.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016-08-22.
 */

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary.datasource")
    public DataSource primaryDataSource(){
        System.out.println("-------------------- primaryDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "secondary.datasource")
    public DataSource secondaryDataSource(){
        System.out.println("-------------------- secondaryDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private DataSource dataSource;


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
