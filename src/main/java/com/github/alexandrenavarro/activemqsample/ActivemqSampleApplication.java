package com.github.alexandrenavarro.activemqsample;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.web.MessageServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJms
public class ActivemqSampleApplication /* extends SpringBootServletInitializer */{

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");
	}

	@Bean
	public MessageServlet dispatcherServlet() {
		final MessageServlet messageServlet = new MessageServlet();
		return messageServlet;
	}

	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		final ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
		final  Map<String,String> params = new HashMap<String,String>();
		params.put("topic","false");
		registration.setInitParameters(params);
		return registration;
	}


	public static void main(String[] args) {
		SpringApplication.run(ActivemqSampleApplication.class, args);
	}
}
