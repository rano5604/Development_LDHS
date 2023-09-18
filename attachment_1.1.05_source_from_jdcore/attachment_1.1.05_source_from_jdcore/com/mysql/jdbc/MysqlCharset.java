package com.mysql.jdbc;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

















































































































































































































































































































































































































































































































































































































































































































































































































































class MysqlCharset
{
  public final String charsetName;
  public final int mblen;
  public final int priority;
  public final List<String> javaEncodingsUc = new ArrayList();
  
  public int major = 4;
  public int minor = 1;
  public int subminor = 0;
  











  public MysqlCharset(String charsetName, int mblen, int priority, String[] javaEncodings)
  {
    this.charsetName = charsetName;
    this.mblen = mblen;
    this.priority = priority;
    
    for (int i = 0; i < javaEncodings.length; i++) {
      String encoding = javaEncodings[i];
      try {
        Charset cs = Charset.forName(encoding);
        addEncodingMapping(cs.name());
        
        Set<String> als = cs.aliases();
        Iterator<String> ali = als.iterator();
        while (ali.hasNext()) {
          addEncodingMapping((String)ali.next());
        }
      }
      catch (Exception e) {
        if (mblen == 1) {
          addEncodingMapping(encoding);
        }
      }
    }
    
    if (javaEncodingsUc.size() == 0) {
      if (mblen > 1) {
        addEncodingMapping("UTF-8");
      } else {
        addEncodingMapping("Cp1252");
      }
    }
  }
  
  private void addEncodingMapping(String encoding) {
    String encodingUc = encoding.toUpperCase(Locale.ENGLISH);
    
    if (!javaEncodingsUc.contains(encodingUc)) {
      javaEncodingsUc.add(encodingUc);
    }
  }
  
  public MysqlCharset(String charsetName, int mblen, int priority, String[] javaEncodings, int major, int minor) {
    this(charsetName, mblen, priority, javaEncodings);
    this.major = major;
    this.minor = minor;
  }
  
  public MysqlCharset(String charsetName, int mblen, int priority, String[] javaEncodings, int major, int minor, int subminor) {
    this(charsetName, mblen, priority, javaEncodings);
    this.major = major;
    this.minor = minor;
    this.subminor = subminor;
  }
  
  public String toString()
  {
    StringBuilder asString = new StringBuilder();
    asString.append("[");
    asString.append("charsetName=");
    asString.append(charsetName);
    asString.append(",mblen=");
    asString.append(mblen);
    

    asString.append("]");
    return asString.toString();
  }
  
  boolean isOkayForVersion(Connection conn) throws SQLException {
    return conn.versionMeetsMinimum(major, minor, subminor);
  }
  






  String getMatchingJavaEncoding(String javaEncoding)
  {
    if ((javaEncoding != null) && (javaEncodingsUc.contains(javaEncoding.toUpperCase(Locale.ENGLISH)))) {
      return javaEncoding;
    }
    return (String)javaEncodingsUc.get(0);
  }
}
