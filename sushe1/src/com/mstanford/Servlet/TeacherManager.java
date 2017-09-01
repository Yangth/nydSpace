package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.bean.TeacherBean;
import com.dao.TeacherDao;
import com.google.gson.Gson;

public class TeacherManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TeacherManager() {
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
		String flag="";
		if(request.getParameter("flag")!=null){
			flag=request.getParameter("flag");
		}
		
		 PrintWriter out = response.getWriter();
			//创建session对象
			HttpSession session =request.getSession();
		
		 String SearchRow=request.getParameter("SearchRow");
	      String SearchKey=request.getParameter("SearchKey");
	      
	      System.out.print(SearchRow+""+SearchKey);
	      
			//验证是否正常登录
			if(session.getAttribute("id")==null){
				out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
				out.flush();
				out.close();
				return;
			}
			//查询条件
			String strWhere="1=1";
			if(!(isInvalid(SearchKey)))
			{
				strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
			}
			//查询所有
			List<TeacherBean> lists=new TeacherDao().GetList(strWhere,"Teacher_Name");
//	      request.setAttribute("lists", lists);
//		   request.getRequestDispatcher("TeacherTable.jsp").forward(request, response);
			out.write(new Gson().toJson(lists));
		}
		
	//判断是否空值
		private boolean isInvalid(String value) {
			return (value == null || value.length() == 0);
		}
		
		//测试
		public static void main(String[] args) {
			System.out.println();
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
