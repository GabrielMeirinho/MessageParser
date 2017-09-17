package com.jb.messageparser.business;
import java.util.List;
import org.springframework.stereotype.Component;
import com.jb.messageparser.model.Sale;

@Component
public interface ParserOperator {
	public boolean parseMessage(String saleMessage) throws Exception;
	public List<Sale> getListOfSales();
	public void saveAndClearMessages();
}
