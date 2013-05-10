package com.melbourneit.exception;

import com.melbourneit.logger.BaseLogger;

public class BaseException extends Exception {
  
	  private static final long serialVersionUID = 65656556L;
	  
	  public BaseException() {  
	  }
  
	  public BaseException(String s) {
	    super(s);
	    BaseLogger.getInstance().logException(s);
	  }

	  public BaseException(String s, Throwable throwable) {
	    super(s, throwable.getCause());
	    BaseLogger.getInstance().logException(s,throwable.getCause());
	  }

	  public BaseException(Throwable throwable) {
	    super(throwable.getCause());
	    BaseLogger.getInstance().logException(throwable.getCause());
	  }
}
