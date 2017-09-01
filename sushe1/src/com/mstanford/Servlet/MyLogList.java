package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.DomitoryBean;
import com.bean.LogBean;
import com.bean.TBBean;
import com.dao.DomitoryDao;
import com.dao.LogDao;
import com.dao.TBDao;

public class MyLogList extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MyLogList() {
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
				
				List<TBBean> Buildinglist=new TBDao().GetList("TB_TeacherID="+session.getAttribute("id"),"Building_Name");
				String Building_ID=String.valueOf(Buildinglist.get(0).getTB_BuildingID());
				
				String SearchKey=request.getParameter("SearchKey");
				String SearchRow=request.getParameter("SearchRow");
				String Domitory_ID=request.getParameter("Domitory_ID");
				
				if(!isInvalid((String)request.getParameter("BuildingID"))){
					Building_ID=request.getParameter("BuildingID");
				}

		
		     //验证是否正常登录
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				

				//查询条件
				String strWhere="Student_State='入住' and Building_ID="+Building_ID;
				if(!(isInvalid(SearchKey)))
				{
					strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
				}
				if(!(isInvalid(Domitory_ID)))
				{
					strWhere+=" and Domitory_ID='"+Domitory_ID+"'";
				}
				//查询所有
				List<LogBean> lists=new LogDao().GetList(strWhere,"Log_Date desc");
				
				//查询所有寝室
				List<DomitoryBean> domitorylist=new DomitoryDao().GetList("Domitory_BuildingID="+Building_ID,"Domitory_Name");
				
				request.setAttribute("Buildinglist", Buildinglist);
				request.setAttribute("lists", lists);
				request.setAttribute("domitorylist", domitorylist);
				if(!isInvalid(Domitory_ID)){
					request.setAttribute("Domitory_ID", Domitory_ID);
				}
					request.setAttribute("BuildingID", Building_ID);
				
				request.getRequestDispatcher("MyLogList.jsp").forward(request, response);
		
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
