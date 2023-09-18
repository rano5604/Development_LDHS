package com.mysql.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;




















public class JDBC42ServerPreparedStatement
  extends JDBC4ServerPreparedStatement
{
  public JDBC42ServerPreparedStatement(MySQLConnection conn, String sql, String catalog, int resultSetType, int resultSetConcurrency)
    throws SQLException
  {
    super(conn, sql, catalog, resultSetType, resultSetConcurrency);
  }
  


  private int checkSqlType(int sqlType)
    throws SQLException
  {
    return JDBC42Helper.checkSqlType(sqlType, getExceptionInterceptor());
  }
  
  private int translateAndCheckSqlType(SQLType sqlType) throws SQLException {
    return JDBC42Helper.translateAndCheckSqlType(sqlType, getExceptionInterceptor());
  }
  






  public void setObject(int parameterIndex, Object x)
    throws SQLException
  {
    synchronized (checkClosed().getConnectionMutex()) {
      super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x));
    }
  }
  







  public void setObject(int parameterIndex, Object x, int targetSqlType)
    throws SQLException
  {
    synchronized (checkClosed().getConnectionMutex()) {
      super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), checkSqlType(targetSqlType));
    }
  }
  








  public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
    throws SQLException
  {
    synchronized (checkClosed().getConnectionMutex()) {
      super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), checkSqlType(targetSqlType), scaleOrLength);
    }
  }
  







  public void setObject(int parameterIndex, Object x, SQLType targetSqlType)
    throws SQLException
  {
    synchronized (checkClosed().getConnectionMutex()) {
      super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType));
    }
  }
  








  public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength)
    throws SQLException
  {
    synchronized (checkClosed().getConnectionMutex()) {
      super.setObject(parameterIndex, JDBC42Helper.convertJavaTimeToJavaSql(x), translateAndCheckSqlType(targetSqlType), scaleOrLength);
    }
  }
}
