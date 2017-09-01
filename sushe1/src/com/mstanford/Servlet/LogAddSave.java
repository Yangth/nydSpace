package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.LogBean;
import com.dao.LogDao;

public class LogAddSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LogAddSave() {
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
				
				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
				
				String Log_StudentID=request.getParameter("Log_StudentID");
				String Log_Date=request.getParameter("Log_Date");
				String Log_Remark=request.getParameter("Log_Remark");
				
		//��֤�Ƿ����¼
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();return;
				}
				
				//���
				LogBean cnbean=new LogBean();
				cnbean.setLog_StudentID(Integer.parseInt(Log_StudentID));
				cnbean.setLog_TeacherID(Integer.parseInt(session.getAttribute("id").toString()));
				cnbean.setLog_Date(Log_Date);
				cnbean.setLog_Remark(Log_Remark);

				new LogDao().Add(cnbean);
				    
				//��ת
				out.print("<script language='javascript'>alert('登记成功！');window.location='welcome.jsp';</script>");
				out.flush();out.close();return;
				
				}
	
	//�ж��Ƿ��ֵ
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
