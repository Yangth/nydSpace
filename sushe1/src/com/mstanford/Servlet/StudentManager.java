package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.StudentBean;
import com.bean.TeacherBean;
import com.dao.StudentDao;
import com.google.gson.Gson;

public class StudentManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentManager() {
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

		 List<StudentBean> list;
			//解决乱码，用于页面输出
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			String State=request.getParameter("State");
			String SearchRow=request.getParameter("SearchRow");
			String SearchKey=request.getParameter("SearchKey");


			PrintWriter out = response.getWriter();
			
			//创建session对象
			HttpSession session =request.getSession();
		//验证是否正常登录
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				//查询条件
				String strWhere="1=1";
				if(!(isInvalid(SearchKey)))
				{
					strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
				}
				if(!(isInvalid(State)))
				{
					strWhere+=" and Student_State='"+State+"'";
				}
				else
				{
					strWhere+=" and Student_State='入住'";
				}
				//查询所有
				list=new StudentDao().GetAllList(strWhere,"Student_Name");
				out.write(new Gson().toJson(list));
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
