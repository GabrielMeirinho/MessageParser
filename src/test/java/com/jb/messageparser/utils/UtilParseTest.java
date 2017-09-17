package com.jb.messageparser.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class UtilParseTest {

	@Test
	public final void testExtractNumber() {
		String number = "55APC";
		assertEquals("55", UtilParse.extractNumber(number));
	}

	@Test
	public final void testIsDigit() {
		String number = "10";
		assertEquals(true, UtilParse.isDigit(number));
	}
	
	@Test
	public final void testIsNotDigit() {
		String number = "1AB2";
		assertEquals(false, UtilParse.isDigit(number));
	}

	@Test
	public final void testToSingular() {
		String apples = "apples";
		String oranges = "oranges";
		String bannanas = "bannanas";
		String potatoes = "potatoes";
		String mangoes = "mangoes";
		String rabbits = "rabbits";
		assertEquals("apple", UtilParse.toSingular(apples));
		assertEquals("orange", UtilParse.toSingular(oranges));
		assertEquals("bannana", UtilParse.toSingular(bannanas));
		assertEquals("potato", UtilParse.toSingular(potatoes));
		assertEquals("mango", UtilParse.toSingular(mangoes));
		assertEquals("rabbit", UtilParse.toSingular(rabbits));
	}
	
	@Test
	public final void testToSingularFailure() { // not all singularities are implemented
		String wife = "wives";
		String cactus  = "cacti";
		String series   = "series";
		String child   = "children";
		assertNotEquals("wife", UtilParse.toSingular(wife));
		assertNotEquals("cactus", UtilParse.toSingular(cactus));
		assertNotEquals("series", UtilParse.toSingular(series));
		assertNotEquals("child", UtilParse.toSingular(child));
	}

}
