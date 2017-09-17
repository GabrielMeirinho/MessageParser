package com.jb.messageparser.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	private String 				product;
	private int 				totalSales;
	private BigDecimal			totalValue;
	private List<Adjustment>    listAdjustments;
	
	public Sale(){
		this.product = "";
		this.totalSales = 0;
		this.totalValue =  BigDecimal.ZERO;
		this.listAdjustments =  new ArrayList<Adjustment>();
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public int getTotalSales() {
		return totalSales;
	}
	
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	public BigDecimal getTotalValues() {
		return totalValue;
	}
	
	public void setTotalValues(BigDecimal totalValues) {
		this.totalValue = totalValues;
	}

	public List<Adjustment> getListAdjustments() {
		return listAdjustments;
	}
	
	public void setListAdjustments(List<Adjustment> listAdjustments) {
		this.listAdjustments = listAdjustments;
	}
	
	public void appendAdjustment( Adjustment adj ){
		listAdjustments.add(adj);
	}
	
	public void applyAdjustment( Adjustment adj ){
		appendAdjustment(adj);
		switch (adj.getOperation().toLowerCase()){
			case "add":      this.totalValue = this.totalValue.add(adj.getValue().multiply(new BigDecimal(totalSales)));
						     break;
						     
			case "subtract": this.totalValue = this.totalValue.subtract(adj.getValue().multiply(new BigDecimal(totalSales)));
							 break;
							 
			case "multiply": this.totalValue = this.totalValue.multiply(adj.getValue());
			 				 break;	
			default: 
							 break; 				 
		}
	}
	
}
