package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Quit extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Quit() {
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

		//清除session
				HttpSession session =request.getSession();
				session.removeAttribute("id");
				session.removeAttribute("type");
				
			//清除Cookie
				Cookie[]cookie=request.getCookies();
				if(cookie!=null){
				String name=null;String pw=null;
				for(Cookie cs:cookie){
				     if(cs.getName().equals("Username")){
				       name=cs.getValue();
				     }
				     if(cs.getName().equals("Password")){
				       pw=cs.getValue();
				     }
				}

				if(name!=null&&pw!=null){
					Cookie usercookie=new Cookie("Username",name);
					Cookie passwordcookie=new Cookie("Password",pw);
					
					usercookie.setMaxAge(0);
					passwordcookie.setMaxAge(0);
					
					response.addCookie(usercookie);
					response.addCookie(passwordcookie);
				}
				}
				response.sendRedirect("Login.jsp");
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
