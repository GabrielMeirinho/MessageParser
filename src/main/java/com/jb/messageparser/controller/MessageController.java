package com.jb.messageparser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jb.messageparser.service.MessageParserService;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageParserService			messageParserService;
	
	@RequestMapping(value = "/{message}", produces = { "application/json" })
	public ResponseEntity<String> parseSaleMessage(
			@PathVariable("message") String message ) throws Exception{
			if ( messageParserService.serviceIsAvaliable()){
				if (messageParserService.parseSaleMessage(message))
					return new ResponseEntity<String>( new String("Message parsed"), HttpStatus.OK );
				else return new ResponseEntity<String>( new String("Bad request on message: "+ message), HttpStatus.BAD_REQUEST );
					
			}
			else
				return new ResponseEntity<String>(new String("Service Unavaliable."), HttpStatus.SERVICE_UNAVAILABLE );
	}
}
