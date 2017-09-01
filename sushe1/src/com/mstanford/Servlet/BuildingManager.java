package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.BuildingBean;
import com.bean.StudentBean;
import com.dao.BuildingDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BuildingManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BuildingManager() {
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


		 List<BuildingBean> list;
			//解决乱码，用于页面输出
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			String SearchKey=request.getParameter("SearchKey");
			System.out.print(SearchKey);
   
			JsonObject jsonobj=new JsonObject();

			PrintWriter out = response.getWriter();
			
			//创建session对象
			HttpSession session =request.getSession();
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
			out.flush();out.close();
		}

		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and Building_Name='"+SearchKey+"'";
		}
		
		list=new BuildingDao().GetList(strWhere,"Building_Name");
		out.write(new Gson().toJson(list));
		/*request.setAttribute("lists", list);
		request.getRequestDispatcher("BuildingManager.jsp").forward(request, response);*/
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
