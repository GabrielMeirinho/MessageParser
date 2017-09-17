package com.jb.messageparser.service;

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
import com.jb.messageparser.configuration.AspectsConfiguration;
import com.jb.messageparser.configuration.InterfacesConfiguration;
import com.jb.messageparser.configuration.WsMessageControl;
import com.jb.messageparser.service.impl.MessageParserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AspectsConfiguration.class, InterfacesConfiguration.class, WsMessageControl.class }, loader = AnnotationConfigContextLoader.class)
public class MessageParserServiceTest {
	
	@Autowired
	MessageParserService		messageParserService;
	
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
	public final void testParse50Messages() 
			throws Exception {
		for (String s :messages){
			messageParserService.parseSaleMessage(s);
		}
		assertEquals(messageParserService.getListOfSales().size(), 4);
	}
	
	
	@Test
	public final void testBadSaleGrammarMessageFailure() 
			throws Exception {
		String message = "apple at p";
		MessageParserService mock = Mockito.mock(MessageParserServiceImpl.class);
		when(mock.parseSaleMessage(message)).thenReturn(false);
	    assertEquals(mock.parseSaleMessage(message), messageParserService.parseSaleMessage(message));
	}
	
	@Test
	public final void testBadSaleGrammarMessageAdjustmentFailure() 
			throws Exception {
		String message1 = "Add adas";
		String message2 = "Add adas p";
		String message3 = "Addadas p 10p";
		MessageParserServiceImpl mock = Mockito.mock(MessageParserServiceImpl.class);
		when(mock.parseSaleMessage(message1)).thenReturn(false);
		when(mock.parseSaleMessage(message2)).thenReturn(false);
		when(mock.parseSaleMessage(message3)).thenReturn(false);
		assertEquals(mock.parseSaleMessage(message1), messageParserService.parseSaleMessage(message1));
	    assertEquals(mock.parseSaleMessage(message2), messageParserService.parseSaleMessage(message2));
	    assertEquals(mock.parseSaleMessage(message3), messageParserService.parseSaleMessage(message3));
	}

}
