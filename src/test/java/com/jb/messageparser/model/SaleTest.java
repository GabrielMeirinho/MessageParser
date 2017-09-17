package com.jb.messageparser.model;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class SaleTest {
	
	private Adjustment adjusmentAdd =  new Adjustment();
	private Adjustment adjusmentSubtract =  new Adjustment();
	private Adjustment adjusmentMultiply =  new Adjustment();
	
	@Before
	public void setUp() throws Exception {
		
		adjusmentAdd.setOperation("add");
		adjusmentAdd.setValue(new BigDecimal("10"));
		
		adjusmentSubtract.setOperation("subtract");
		adjusmentSubtract.setValue(new BigDecimal("10"));
		
		adjusmentMultiply.setOperation("multiply");
		adjusmentMultiply.setValue(new BigDecimal("10"));
	}

	@Test
	public final void testAddApplyAdjustment() {
		Sale sale = new Sale();
		sale.setProduct("apple");
		sale.setTotalSales(1);
		sale.setTotalValues(new BigDecimal("10"));
		
		sale.applyAdjustment(adjusmentAdd);
		assertEquals(1, sale.getListAdjustments().size());
		assertEquals(1, sale.getTotalSales());
		assertEquals("20", sale.getTotalValues().toString());
	}
	
	@Test
	public final void testSubtractApplyAdjustment() {
		Sale sale = new Sale();
		sale.setProduct("apple");
		sale.setTotalSales(1);
		sale.setTotalValues(new BigDecimal("10"));
		
		sale.applyAdjustment(adjusmentSubtract);
		assertEquals(1, sale.getListAdjustments().size());
		assertEquals(1, sale.getTotalSales());
		assertEquals("0", sale.getTotalValues().toString());
	}
	
	@Test
	public final void testMultiplyApplyAdjustment() {
		Sale sale = new Sale();
		sale.setProduct("apple");
		sale.setTotalSales(1);
		sale.setTotalValues(new BigDecimal("10"));
		
		sale.applyAdjustment(adjusmentMultiply);
		assertEquals(1, sale.getListAdjustments().size());
		assertEquals(1, sale.getTotalSales());
		assertEquals("100", sale.getTotalValues().toString());
	}


}
