package com.jb.messageparser.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jb.messageparser.business.ParserOperator;
import com.jb.messageparser.model.Sale;
import com.jb.messageparser.service.MessageParserService;
@Component
public class MessageParserServiceImpl implements MessageParserService{
	private boolean runningSystem = true;
	List<String> 	listMessageFailed = new ArrayList<String>();
	@Autowired
	ParserOperator	parserOperator;
	
	public MessageParserServiceImpl(){}
	
	public boolean parseSaleMessage(String message) throws Exception{
			if (parserOperator.parseMessage(message)){
				return true;
			}
			else {
				listMessageFailed.add(message);
				return false;
			}
	}
	
	public List<Sale> getListOfSales(){	return parserOperator.getListOfSales(); }
	public List<String> getListOfMessagesFailed(){ return listMessageFailed; }
	public void pauseService(){ runningSystem = false;}
	public void unpauseService(){ runningSystem = true; }
	public boolean serviceIsAvaliable(){return runningSystem; }
}
