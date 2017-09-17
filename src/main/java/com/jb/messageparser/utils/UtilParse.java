package com.jb.messageparser.utils;

public abstract class UtilParse {
	
	public static String extractNumber(final String str) {                
	    if(str == null || str.isEmpty()) return null;
	    StringBuilder sb = new StringBuilder();
	    boolean found = false;
	    for(char c : str.toCharArray()){
	        if(Character.isDigit(c)){
	            sb.append(c);
	            found = true;
	        } else if(found){
	            break;                
	        }
	    }
	    return sb.toString();
	}
	
	public static boolean isDigit(final String str) {                
	    if(str == null || str.isEmpty()) 
	    	return false;
	    for(char c : str.toCharArray()){
	        if(!Character.isDigit(c)){
	        	return false;                
	        }
	    }
	    return true;
	}
	
	public static String toSingular(String message){
		String msg = message;
		msg = msg.replaceAll("oes", "o");
		msg = msg.replaceAll("ies$","y");     
		msg = msg.replaceAll("s$","");   
		return msg;
	}

}
