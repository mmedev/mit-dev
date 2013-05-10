package com.melbourneit.exception;

public class BaseException extends Exception {
  
	  private static final long serialVersionUID = 65656556L;

	  public BaseException() {
	    
	  }
  
	  public BaseException(String s) {
	    super(s);
	  }

	  public BaseException(String s, Throwable throwable) {
	    super(s, throwable);
	  }

	  public BaseException(Throwable throwable) {
	    super(throwable);
	  }
  
	  public static BaseException getInstance(Exception e) {
	    if (BaseException.class.isAssignableFrom(e.getClass())) {
	    	return (BaseException) e;
	    } else {
	    	StringBuilder errMsg = new StringBuilder("");
	    	try {
	    		errMsg.append(e.getMessage().toString());
	    	} catch (NullPointerException npex) {}
	    	return new BaseException(e);
	    }
	  }

}
