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
import com.dao.StudentDao;
import com.google.gson.JsonObject;

public class StudentRZSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentRZSave() {
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

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String Domitory_ID=request.getParameter("Domitory_ID");
		String Student_Username=request.getParameter("username");

     System.out.print("获取的信息"+Student_Username+Domitory_ID);

		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session =request.getSession();
		
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
			out.flush();out.close();
		}
		
		StudentBean sbean=new StudentBean();
		
		List<StudentBean> list=new StudentDao().GetAllList("Student_Username='"+Student_Username+"'", "");
		if(list.size()<1)
		{
			out.print("<script language='javascript'>alert('学号不存在！');</script>");
			out.flush();
			out.close();
			return;
		}
		else
		{
			sbean=new StudentDao().GetAllFirstBean("Student_Username='"+Student_Username+"'");
			if(!(sbean.getStudent_State().equals("未入住")))
			{
				out.print("<script language='javascript'>alert('对不起该学生处于"+sbean.getStudent_State()+"状态禁止入住！״̬');</script>");
				out.flush();
				out.close();
				return;
			}
		}
	     JsonObject jsonb=new JsonObject();
		
		StudentBean cnbean;
		cnbean=new StudentDao().GetAllBean(sbean.getStudent_ID());
		cnbean.setStudent_DomitoryID(Integer.parseInt(Domitory_ID));
		cnbean.setStudent_State("入住");
		if(new StudentDao().Update(cnbean)){
			jsonb.addProperty("success", true);
			jsonb.addProperty("message", "入住成功");
			out.write(jsonb.toString());
			out.flush();
			out.close();
		}
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
