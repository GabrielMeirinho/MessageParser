package com.jb.messageparser.configuration;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jb.messageparser.model.Adjustment;
import com.jb.messageparser.model.Sale;
import com.jb.messageparser.service.MessageParserService;
@Component
public class WsMessageControl{
	
	@Autowired
	MessageParserService messageParserService;
	
	private static final int messagePeriod = 10;
	private static final int pausePeriod   = 50;
	private int 			 serviceCounter= 0;

	public void serviceCounterIntegration() {
		this.serviceCounter++;
		if (serviceCounter%messagePeriod == 0){
			System.out.println("Total of " + serviceCounter +" messages, printing log.");
			List<Sale> listSales = messageParserService.getListOfSales();
			for (Sale s: listSales){
				System.out.println("Product: " + s.getProduct() +", total sales: " +  s.getTotalSales() + ", total sales value: " +  s.getTotalValues());
			}
		}
		if (serviceCounter%pausePeriod == 0){
			System.out.println("Service paused, no more messages will be parsed");
			messageParserService.pauseService();
			List<Sale> listSales = messageParserService.getListOfSales();
			for (Sale s: listSales){
				System.out.println("Product: " + s.getProduct());
				for ( Adjustment a: s.getListAdjustments()) {
					System.out.println("Adjustment : " + a.getOperation() + ", Value: " + a.getValue().toString());
				}
			}
		}
	} 
}