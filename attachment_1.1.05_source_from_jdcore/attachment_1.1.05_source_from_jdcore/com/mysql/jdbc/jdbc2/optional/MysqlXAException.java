package com.mysql.jdbc.jdbc2.optional;

import javax.transaction.xa.XAException;

























class MysqlXAException
  extends XAException
{
  private static final long serialVersionUID = -9075817535836563004L;
  private String message;
  protected String xidAsString;
  
  public MysqlXAException(int errorCode, String message, String xidAsString)
  {
    super(errorCode);
    this.message = message;
    this.xidAsString = xidAsString;
  }
  

  public MysqlXAException(String message, String xidAsString)
  {
    this.message = message;
    this.xidAsString = xidAsString;
  }
  
  public String getMessage()
  {
    String superMessage = super.getMessage();
    StringBuilder returnedMessage = new StringBuilder();
    
    if (superMessage != null) {
      returnedMessage.append(superMessage);
      returnedMessage.append(":");
    }
    
    if (message != null) {
      returnedMessage.append(message);
    }
    
    return returnedMessage.toString();
  }
}
