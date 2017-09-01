package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.OutBean;
import com.bean.StudentBean;
import com.dao.OutDao;
import com.dao.StudentDao;

public class StudentQCSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentQCSave() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//解决乱码，用于页面输出
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				request.setCharacterEncoding("utf-8");
				
				String Out_Remark=request.getParameter("Out_Remark");
				String Student_ID=request.getParameter("Student_ID");



				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
		
		//验证是否正常登录
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();
					out.close();
				}
				

				//修改学生状态
				StudentBean cnbean=new StudentBean();
				cnbean=new StudentDao().GetBean(Integer.parseInt(Student_ID));
				cnbean.setStudent_State("迁出");
				new StudentDao().Update(cnbean);
				
				//添加迁出记录
				OutBean outbean=new OutBean();
				outbean.setOut_StudentID(Integer.parseInt(Student_ID));
				outbean.setOut_Date(getNowdate());
				outbean.setOut_Remark(Out_Remark);

				new OutDao().Add(outbean);
				    
				//跳转
				out.print("<script language='javascript'>alert('学生迁出操作成功！');window.location='StudentQC.jsp';</script>");
				out.flush();
				out.close();
				
	}
	
	//获取当前日期
	public String getNowdate(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		int date=c.get(Calendar.DATE);
		return year+"-"+month+"-"+date;
	}
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
