package com.jb.messageparser.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.jb.messageparser.business.ParserOperator;
import com.jb.messageparser.business.impl.ParserOperatorImpl;
import com.jb.messageparser.configuration.AspectsConfiguration;
import com.jb.messageparser.configuration.InterfacesConfiguration;
import com.jb.messageparser.configuration.WsMessageControl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectsConfiguration.class, InterfacesConfiguration.class, WsMessageControl.class }, loader = AnnotationConfigContextLoader.class)
public class ParseOperatorTest {
	
	@Autowired
	ParserOperator	parserOperator;
	
	private List<String>  messages= new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		messages.add("apple at 5p");
		messages.add("bananna at 5p");
		messages.add("potato at 5p");
		messages.add("20 sales of apples at 5p each.");
		messages.add("apple at 5p");
		messages.add("10 sales of banannas at 5p each.");
		messages.add("potato at 5p");
		messages.add("apple at 5p");
		messages.add("orange at 5p");
		messages.add("bananna at 5p");
		messages.add("10 sales of potatoes at 5p each.");
		messages.add("10 sales of apples at 5p each.");
		messages.add("orange at 5p");
		messages.add("bananna at 5p");
		messages.add("potato at 5p");
		messages.add("Add 1p apples");
		messages.add("orange at 5p");
		messages.add("Add 2p banannas");
		messages.add("Subtract 1p potatoes");
		messages.add("apple at 5p");
		messages.add("apple at 5p");
		messages.add("bananna at 5p");
		messages.add("Multiply 2 potatoes");
		messages.add("apple at 15p");
		messages.add("orange at 15p");
		messages.add("apple at 15p");
		messages.add("bananna at 15p");
		messages.add("potato at 15p");
		messages.add("20 sales of apples at 15p each.");
		messages.add("apple at 5p");
		messages.add("10 sales of banannas at 5p each.");
		messages.add("potato at 5p");
		messages.add("apple at 15p");
		messages.add("apple at 5p");
		messages.add("orange at 5p");
		messages.add("10 sales of oranges at 5p each.");
		messages.add("apple at 5p");
		messages.add("apple at 5p");
		messages.add("bananna at 5p");
		messages.add("potato at 5p");
		messages.add("Add 1p oranges");
		messages.add("apple at 10p");
		messages.add("Add 2p banannas");
		messages.add("Subtract 1p oranges");
		messages.add("apple at 10p");
		messages.add("apple at 10p");
		messages.add("bananna at 10p");
		messages.add("Multiply 2 oranges");
		messages.add("apple at 10p");
		messages.add("potato at 10p");
	}

	@Test
	public final void testParseMessage() 
			throws Exception {
		for (String s :messages){
			parserOperator.parseMessage(s);
		}
		assertEquals(parserOperator.getListOfSales().size(), 4);
	}
	
	@Test
	public final void testBadSaleGrammarMessageFailure() 
			throws Exception {
		String message = "apple at p";
		ParserOperatorImpl mock = Mockito.mock(ParserOperatorImpl.class);
		when(mock.parseMessage(message)).thenReturn(false);
	    assertEquals(mock.parseMessage(message), parserOperator.parseMessage(message));
	}
	
	@Test
	public final void testGrammarAdjustmentMultipleMessages() 
			throws Exception {
		String message1 = "Add adas";
		String message2 = "Add adas p";
		String message3 = "Addadas p 10p";
		String message4 = "20 of apples at 10p each.";
		String message5 = "Add 10p apples";
		String message6 = "Subtract 10p apples";
		String message7 = "Multiply 10p apples";
		ParserOperatorImpl mock = Mockito.mock(ParserOperatorImpl.class);
		when(mock.parseMessage(message1)).thenReturn(false);
		when(mock.parseMessage(message2)).thenReturn(false);
		when(mock.parseMessage(message3)).thenReturn(false);
		when(mock.parseMessage(message4)).thenReturn(true);
		when(mock.parseMessage(message5)).thenReturn(true);
		when(mock.parseMessage(message6)).thenReturn(true);
		when(mock.parseMessage(message7)).thenReturn(true);
	    assertEquals(mock.parseMessage(message1), parserOperator.parseMessage(message1));
	    assertEquals(mock.parseMessage(message2), parserOperator.parseMessage(message2));
	    assertEquals(mock.parseMessage(message3), parserOperator.parseMessage(message3));
	    assertEquals(mock.parseMessage(message4), parserOperator.parseMessage(message4));
	    assertEquals(mock.parseMessage(message5), parserOperator.parseMessage(message5));
	    assertEquals(mock.parseMessage(message6), parserOperator.parseMessage(message6));
	    assertEquals(mock.parseMessage(message7), parserOperator.parseMessage(message7));
	}
	
	

}
