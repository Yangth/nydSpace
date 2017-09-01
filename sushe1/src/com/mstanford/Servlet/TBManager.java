package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.TBBean;
import com.bean.TeacherBean;
import com.dao.TBDao;
import com.dao.TeacherDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TBManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TBManager() {
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
				
				String Building_ID=request.getParameter("Building_ID");
				String flag=request.getParameter("flag");
				
				System.out.print("bulidingid="+Building_ID);
				System.out.print("flag="+flag);

				PrintWriter out = response.getWriter();
				
				JsonObject jsonb=new JsonObject();
				
				//创建session对象
				HttpSession session =request.getSession();
		
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
			out.flush();out.close();
		}

		
		List<TeacherBean> teacherlist=new TeacherDao().GetList("","");
		
		
		List<TBBean> list=new TBDao().GetList("TB_BuildingID="+Building_ID,"");
		if(flag.equals("query")){
			 out.write(new Gson().toJson(list));
		}else if(flag.equals("comb")){
	        out.write(new Gson().toJson(teacherlist));
		}
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
