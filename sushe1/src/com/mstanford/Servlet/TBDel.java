package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TBDao;
import com.google.gson.JsonObject;

public class TBDel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TBDel() {
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
				
				String TB_ID=request.getParameter("TB_ID");
				String[]ids=TB_ID.split(",");

				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
		
				JsonObject jsonb=new JsonObject();
		       //权限验证
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				
				
				//验证是否成功
				if(new TBDao().Delete(ids)){
					jsonb.addProperty("success", true);
					jsonb.addProperty("message", "删除成功！");
					out.write(jsonb.toString());
					out.flush();
					out.close();
				}
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
