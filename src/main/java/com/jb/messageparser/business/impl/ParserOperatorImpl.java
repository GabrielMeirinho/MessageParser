package com.jb.messageparser.business.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.jb.messageparser.business.ParserOperator;
import com.jb.messageparser.model.Adjustment;
import com.jb.messageparser.model.Sale;
import com.jb.messageparser.utils.UtilParse;

@Component
public class ParserOperatorImpl implements ParserOperator{
	
	private List<Sale>  		listOfSales = new ArrayList<Sale>();
	private Map<String,Integer> productsMap = new HashMap<String,Integer>();
	
	public ParserOperatorImpl(){}

	public boolean parseMessage(String message) 
			throws Exception{
		boolean isParsed = false;
		String adjustmentObject = searchForAdjustment(message);
		if (adjustmentObject!=null ){
			Adjustment adjustment = parseAdjustment(message);
			if (adjustment!=null && productsMap.containsKey(adjustmentObject)){
				int index = productsMap.get(adjustmentObject);
				Sale oldSale = listOfSales.get(productsMap.get(adjustmentObject));
				oldSale.applyAdjustment(adjustment);
				listOfSales.set(index, oldSale);
				isParsed = true;
			}
			else
			{
				System.out.println("Not possible to parse message:" + message + " object " + adjustmentObject + " has never been sold previously.");
			}
		}
		else
		{
			Sale newSale = parseSale(prepareMessageSale(message));	
			if (newSale!=null){
				if (productsMap.containsKey(newSale.getProduct())){
					int index = productsMap.get(newSale.getProduct());
					Sale oldSale = listOfSales.get(index);
					oldSale.setTotalValues(oldSale.getTotalValues().add(newSale.getTotalValues()));
					oldSale.setTotalSales(oldSale.getTotalSales()+newSale.getTotalSales());
					listOfSales.set(index, oldSale);
				}
				else{
					productsMap.put(newSale.getProduct(), productsMap.size());
					listOfSales.add(newSale);
				}
				isParsed = true;
			}
		}
		return isParsed;
	}
	
	private String searchForAdjustment(String message){
		int i = message.indexOf(' ');
		String word = message.substring(0, i);
		if ( word.toLowerCase().equals("add") || word.toLowerCase().equals("subtract") || word.toLowerCase().equals("multiply") ){
			List<String> listParams = Arrays.asList(message.split("\\s+"));
			if (listParams.size()==3 && UtilParse.isDigit(UtilParse.extractNumber(listParams.get(1))))
				return UtilParse.toSingular(listParams.get(2));
		}
		return null;
	}
	
	private Adjustment parseAdjustment(String message) throws Exception {
		List<String> listParams = Arrays.asList(message.split("\\s+"));
		Adjustment adjustment = new Adjustment();
		try{
			adjustment.setOperation(listParams.get(0));
			adjustment.setValue(new BigDecimal (UtilParse.extractNumber(listParams.get(1))));
		}
		catch(Exception e){
			System.out.println( "Impossible to parse message: " + message +" Exception: " + e);
			return null;
		}
		return adjustment;
	}
	
	private Sale parseSale(String message) throws Exception {
		List<String> listParams = Arrays.asList(message.split("\\s+"));
		Sale sale = new Sale();
		int qty= 1;
		int counter = 0;
		try{
			if (UtilParse.isDigit(listParams.get(0))){
			   qty = Integer.valueOf(listParams.get(0));
			   counter = 1;
			}
			sale.setProduct(UtilParse.toSingular(listParams.get(counter)));
			sale.setTotalSales(qty);
			sale.setTotalValues((new BigDecimal (UtilParse.extractNumber(listParams.get(counter+1)))).multiply(new BigDecimal(qty)));
		}
		catch(Exception e){
			System.out.println( "Impossible to parse message: " + message +" Exception: " + e);
			return null;
		}
		return sale;
	}
	
	private String prepareMessageSale(String message){
		String msg = message;
		msg = msg.replaceAll(" sales ", " ");
		msg = msg.replaceAll(" of ", " ");
		msg = msg.replaceAll(" at ", " ");    
		msg = msg.replaceAll(" each.","");     
		return msg;
	}
	
	public List<Sale> getListOfSales() {
		return listOfSales;
	}
	
	public void saveAndClearMessages(){
		// save messages failed and messages parsed on tables [not required for this test]
		listOfSales.clear();
		productsMap.clear();
	}
}
