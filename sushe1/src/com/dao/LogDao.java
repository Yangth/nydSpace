package com.dao;

import com.db.DBHelper;
import com.bean.LogBean;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

public class LogDao {
	
	//获取列表
	public List<LogBean> GetList(String strwhere,String strorder){
		String sql="select * from Log where ";
		if(!(isInvalid(strwhere)))
		{
			sql+=strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
//		System.out.println(sql);
		PreparedStatement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<LogBean> list=new ArrayList<LogBean>();
		try{
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()){
				LogBean cnbean=new LogBean();
				cnbean.setLog_ID(rs.getInt("Log_ID"));
				cnbean.setLog_StudentID(rs.getInt("Log_StudentID"));
				cnbean.setLog_TeacherID(rs.getInt("Log_TeacherID"));
				cnbean.setLog_Date(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("Log_Date")));
				cnbean.setLog_Remark(rs.getString("Log_Remark"));
				
				String sql1="select*from teacher where Teacher_ID="+rs.getInt("Log_TeacherID");
				PreparedStatement stat1 = conn.prepareStatement(sql1);
				ResultSet rs1 = stat1.executeQuery();
				while(rs1.next()){
					cnbean.setTeacher_Name(rs1.getString("Teacher_Name"));
					cnbean.setTeacher_Sex(rs1.getString("Teacher_Sex"));
					cnbean.setTeacher_Tel(rs1.getString("Teacher_Tel"));
				}
				String sql2="select*from student where Student_ID="+rs.getInt("Log_StudentID");
				PreparedStatement stat2 = conn.prepareStatement(sql2);
				ResultSet rs2 = stat2.executeQuery();
				while(rs2.next()){
					cnbean.setStudent_Username(rs2.getString("Student_Username"));
					cnbean.setStudent_Name(rs2.getString("Student_Name"));
					cnbean.setStudent_Sex(rs2.getString("Student_Sex"));
					cnbean.setStudent_Class(rs2.getString("Student_Class"));
				}
				String sql3="select B.Building_Name,D.Domitory_Name from tb X,teacher T,building B,Student S,Domitory D " +
						"where T.Teacher_ID=X.TB_TeacherID and B.Building_ID=X.TB_BuildingID and S.Student_DomitoryID=D.Domitory_ID and " +
						"T.Teacher_ID="+rs.getInt("Log_TeacherID")+" and S.Student_ID="+rs.getInt("Log_StudentID");
				PreparedStatement stat3 = conn.prepareStatement(sql3);
				ResultSet rs3 = stat3.executeQuery();
				while(rs3.next()){
					cnbean.setDomitory_Name(rs3.getString("Domitory_Name"));
					cnbean.setBuilding_Name(rs3.getString("Building_Name"));
				}
				
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//获取指定ID的实体Bean
	public LogBean GetBean(int id){
		String sql="select * from Log,Teacher,Student,Domitory,Building where Log_TeacherID=TeacherID and Log_StudentID=Student_ID and Student_DomitoryID=Domitory_ID and Domitory_BuildingID=Building_ID and Log_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		LogBean cnbean=new LogBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setLog_ID(rs.getInt("Log_ID"));
				cnbean.setLog_StudentID(rs.getInt("Log_StudentID"));
				cnbean.setLog_TeacherID(rs.getInt("Log_TeacherID"));
				cnbean.setLog_Date(rs.getString("Log_Date"));
				cnbean.setLog_Remark(rs.getString("Log_Remark"));
				cnbean.setTeacher_Name(rs.getString("Teacher_Name"));
				cnbean.setTeacher_Sex(rs.getString("Teacher_Sex"));
				cnbean.setTeacher_Tel(rs.getString("Teacher_Tel"));
				cnbean.setStudent_Username(rs.getString("Student_Username"));
				cnbean.setStudent_Name(rs.getString("Student_Name"));
				cnbean.setStudent_Sex(rs.getString("Student_Sex"));
				cnbean.setStudent_Class(rs.getString("Student_Class"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	
	//添加
	public void Add(LogBean cnbean){
		String sql="insert into Log (";
		sql+="Log_StudentID,Log_TeacherID,Log_Date,Log_Remark";
		sql+=") values(";
		sql+="'"+cnbean.getLog_StudentID()+"','"+cnbean.getLog_TeacherID()+"','"+cnbean.getLog_Date()+"','"+cnbean.getLog_Remark()+"'";
		sql+=")";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//修改
	public void Update(LogBean cnbean){
		String sql="update Log set ";
		sql+="Log_StudentID='"+cnbean.getLog_StudentID()+"',";
		sql+="Log_TeacherID='"+cnbean.getLog_TeacherID()+"',";
		sql+="Log_Date='"+cnbean.getLog_Date()+"',";
		sql+="Log_Remark='"+cnbean.getLog_Remark()+"'";
		
		sql+=" where Log_ID='"+cnbean.getLog_ID()+"'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//删除
	public void Delete(String strwhere){
		String sql="delete Log where ";
		sql+=strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println("");
	}
	
}

