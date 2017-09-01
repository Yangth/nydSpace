	package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {	

	private String dbUrl="jdbc:mysql://localhost:3306/sushe?Unicode=true&characterEncoding=GBK";
	private String dbUser="root";
	private String dbPassword="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	//获取Connection链接+
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(jdbcName);
		}
		catch(Exception e){}
		try{
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		}
		catch(SQLException ex){}
		return conn;		
	}
	
//    测试
	public static void main(String[] args)
	{
		System.out.println(new DBHelper().getConn());
		
	}
	
}
