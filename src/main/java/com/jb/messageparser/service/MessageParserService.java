package com.jb.messageparser.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jb.messageparser.model.Sale;

@Service
public interface MessageParserService {
	public boolean parseSaleMessage(String message) throws Exception;
	public List<String> getListOfMessagesFailed();
	public List<Sale> getListOfSales();
	public void pauseService();
	public void unpauseService();
	public boolean serviceIsAvaliable();
}
