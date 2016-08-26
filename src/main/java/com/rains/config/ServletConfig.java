package com.rains.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016-08-25.
 */
@Configuration
public class ServletConfig {

    /**
     * 修改DispatcherSerlvet匹配路径.,此处修改之后,在application.properties中设置的匹配路径将会失效
     * @param dispatcherServlet
     * @return
     */
	/*@Bean
	public ServletRegistrationBean dispatcherServeletRegistration(DispatcherServlet dispatcherServlet){
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		registration.addUrlMappings("*.do");
		return registration;
	}*/

	/*@Bean
	public ServletRegistrationBean payServletRegistration(){

		return new ServletRegistrationBean(new PayServlet() , "/wftpay");-
	}*/
}
