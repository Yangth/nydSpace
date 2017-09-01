package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.dao.StudentDao;
import com.dao.TeacherDao;

public class GoLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GoLogin() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String Type=(String)request.getParameter("Type");
		String Username=(String)request.getParameter("Username");
		String Password=(String)request.getParameter("Password");
		String autodl=(String)request.getParameter("autodl");
		
		if(Type.equals("SystemManager"))
		{
			String Admin_ID=new AdminDao().CheckLogin(Username, Password);
			if (isInvalid(Admin_ID)) {
				out.print("<script>alert('用户名或密码错误！');history.back();</script>");
				return;
			}
			else
			{
				if(!isInvalid(autodl)){
					Cookie typecookie=new Cookie("Type",Type);
					Cookie usercookie=new Cookie("Username",Username);
					Cookie passwordcookie=new Cookie("Password",Password);
					//Cookie id=new Cookie("admin_ID",Admin_ID);
					
					typecookie.setMaxAge(60*60);
					usercookie.setMaxAge(60*60);
					passwordcookie.setMaxAge(60*60);
					//id.setMaxAge(60*60);
					
					response.addCookie(typecookie);
					response.addCookie(usercookie);
					response.addCookie(passwordcookie);
					//response.addCookie(id);
				}
				HttpSession session =request.getSession();
				session.setAttribute("id", Admin_ID);
				session.setAttribute("type", "1");
				request.getRequestDispatcher("Index.jsp").forward(request, response);

			  
			}
		}
		else if(Type.equals("BuildingManager"))
		{
			String Teacher_ID=new TeacherDao().CheckLogin(Username, Password);
			if (isInvalid(Teacher_ID)) {
				out.print("<script>alert('用户名或密码错误！');history.back();</script>");
				return;
			}
			else
			{
				if(!isInvalid(autodl)){
					Cookie typecookie=new Cookie("Type",Type);
					Cookie usercookie=new Cookie("Username",Username);
					Cookie passwordcookie=new Cookie("Password",Password);
				//	Cookie id=new Cookie("admin_ID",Teacher_ID);
					
					typecookie.setMaxAge(60*60);
					usercookie.setMaxAge(60*60);
					passwordcookie.setMaxAge(60*60);
					//id.setMaxAge(60*60);
					
					response.addCookie(typecookie);
					response.addCookie(usercookie);
					response.addCookie(passwordcookie);
					//response.addCookie(id);
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("id", Teacher_ID);
				session.setAttribute("type", "2");
				request.getRequestDispatcher("Index.jsp").forward(request, response);

				
			}
		}
		else if(Type.equals("Student"))
		{
			String Student_ID=new StudentDao().CheckLogin(Username, Password);
			if (isInvalid(Student_ID)) {
				out.print("<script>alert('用户名或密码错误！');history.back();</script>");
				return;
			}
			else
			{
				if(!isInvalid(autodl)){
					Cookie typecookie=new Cookie("Type",Type);
					Cookie usercookie=new Cookie("Username",Username);
					Cookie passwordcookie=new Cookie("Password",Password);
					//Cookie id=new Cookie("admin_ID",Student_ID);
					
					typecookie.setMaxAge(60*60);
					usercookie.setMaxAge(60*60);
					passwordcookie.setMaxAge(60*60);
					//id.setMaxAge(60*60);
					
					response.addCookie(typecookie);
					response.addCookie(usercookie);
					response.addCookie(passwordcookie);
					//response.addCookie(id);
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("id", Student_ID);
				session.setAttribute("type", "3");
				request.getRequestDispatcher("Index.jsp").forward(request, response);
				
			}
		}
		else
		{
			out.print("<script>alert('请选择身份！')</script>");
			return;
		}
		//response.sendRedirect("Index.jsp");
	
	}
	
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
