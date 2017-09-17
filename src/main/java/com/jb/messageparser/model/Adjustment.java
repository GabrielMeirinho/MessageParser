package com.jb.messageparser.model;

import java.math.BigDecimal;

public class Adjustment {
	
	private String 		operation;
	private BigDecimal 	value;
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	} 

}
