package com.jb.messageparser.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jb.messageparser.business.ParserOperator;
import com.jb.messageparser.business.impl.ParserOperatorImpl;
import com.jb.messageparser.service.MessageParserService;
import com.jb.messageparser.service.impl.MessageParserServiceImpl;

@Configuration
public class InterfacesConfiguration {
	
	@Bean
	public MessageParserService messageParserService(){
		return new MessageParserServiceImpl();
	}
	
	@Bean
	public ParserOperator		parserOperator(){
		return new ParserOperatorImpl();
	}

}
