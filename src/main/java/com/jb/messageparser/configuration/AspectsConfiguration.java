package com.jb.messageparser.configuration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectsConfiguration {

	@Autowired
	WsMessageControl		wsMessageControl;
	
	@After("execution(* com.jb.messageparser.service.MessageParserService.parseSaleMessage(..))")
	public void trackParseMessage(JoinPoint joinPoint){
		wsMessageControl.serviceCounterIntegration();
	}
	
	
}
