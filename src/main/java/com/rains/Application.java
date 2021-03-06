package com.rains;

import com.rains.entity.RainsTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @SpringBootApplication
 等同于以下三个注解
 @Configuration
 @EnableAutoConfiguration
 @ComponentScan

 * @ServletComponent 对Servlet(@WebServlet),Fillter组件进行扫描~
 *
 * 若项目打包成War则需要继承SpringBootServletInitializer 并重写configure方法.  类似于配置web.xml中的上下文
 **/
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {


	@Override
	protected
	SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {

		System.out.println("" + System.getProperty("java.awt.headless"));
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);

	}


	@Bean
	@ConfigurationProperties(prefix="custom.datasource")
	public RainsTest getTest(){
		return new RainsTest();
	}


}
