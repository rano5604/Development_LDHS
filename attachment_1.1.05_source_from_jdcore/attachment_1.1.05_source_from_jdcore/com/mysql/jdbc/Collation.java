package com.mysql.jdbc;

import java.util.Map;



































































































































































































































































































































































































































































































































































































































































































































































































































































































































































class Collation
{
  public final int index;
  public final String collationName;
  public final int priority;
  public final MysqlCharset mysqlCharset;
  
  public Collation(int index, String collationName, int priority, String charsetName)
  {
    this.index = index;
    this.collationName = collationName;
    this.priority = priority;
    mysqlCharset = ((MysqlCharset)CharsetMapping.CHARSET_NAME_TO_CHARSET.get(charsetName));
  }
  
  public String toString()
  {
    StringBuilder asString = new StringBuilder();
    asString.append("[");
    asString.append("index=");
    asString.append(index);
    asString.append(",collationName=");
    asString.append(collationName);
    asString.append(",charsetName=");
    asString.append(mysqlCharset.charsetName);
    asString.append(",javaCharsetName=");
    asString.append(mysqlCharset.getMatchingJavaEncoding(null));
    asString.append("]");
    return asString.toString();
  }
}
