package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AdminBean;
import com.bean.StudentBean;
import com.bean.TeacherBean;
import com.dao.AdminDao;
import com.dao.StudentDao;
import com.dao.TeacherDao;

public class PasswordUpdateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PasswordUpdateSave() {
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
				
				String Password=request.getParameter("Password");
				String Password2=request.getParameter("Password2");



				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
		
				String Msg=null;
		//验证是否正常登录
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();
					out.close();
					return;
				}
				String type=session.getAttribute("type").toString();
				if(type.equals("1"))//校园管理员身份
				{
					//查询原密码是否正确
					if (new AdminDao().CheckPassword(session.getAttribute("id").toString(), Password)) {
						//修改密码
						AdminBean cnbean=new AdminBean();
						cnbean=new AdminDao().GetBean(Integer.parseInt(session.getAttribute("id").toString()));
						cnbean.setAdmin_Password(Password2);
						new AdminDao().Update(cnbean);
						out.print("<script language='javascript'>alert('修改成功！');window.location='PasswordUpdate.jsp';</script>");
						out.flush();
						out.close();
						return;
					}
					else
					{
						Msg = "用户名或者密码错误";
						
					}
				}
				else if(type.equals("2"))//楼宇管理员身份
				{
					//查询原密码是否正确
					if (new TeacherDao().CheckPassword(session.getAttribute("id").toString(), Password)) {
						//修改密码
						TeacherBean cnbean=new TeacherBean();
						cnbean=new TeacherDao().GetBean(Integer.parseInt(session.getAttribute("id").toString()));
						cnbean.setTeacher_Password(Password2);
						new TeacherDao().Update(cnbean);
						out.print("<script language='javascript'>alert('修改成功！');window.location='PasswordUpdate.jsp';</script>");
						out.flush();
						out.close();
						return;
					}
					else
					{
						Msg = "用户名或者密码错误";
						
					}
				}
				else if(type.equals("3"))//学生身份
				{
					//查询原密码是否正确
					if (new StudentDao().CheckPassword(session.getAttribute("id").toString(), Password)) {
						//修改密码
						StudentBean cnbean=new StudentBean();
						cnbean=new StudentDao().GetBean(Integer.parseInt(session.getAttribute("id").toString()));
						cnbean.setStudent_Password(Password2);
						new StudentDao().Update(cnbean);
						out.print("<script language='javascript'>alert('修改成功！');window.location='PasswordUpdate.jsp';</script>");
						out.flush();
						out.close();
						return;
					}
					else
					{
						Msg = "用户名或者密码错误";
						
					}
				}
				else
				{
					out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
					out.flush();
					out.close();
					return;
				}
				
				request.setAttribute("Msg", Msg);
				request.getRequestDispatcher("PasswordUpdate.jsp").forward(request, response);
				
				
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
