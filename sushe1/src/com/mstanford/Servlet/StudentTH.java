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
import com.bean.DomitoryBean;
import com.bean.StudentBean;
import com.bean.TeacherBean;
import com.dao.BuildingDao;
import com.dao.DomitoryDao;
import com.dao.StudentDao;

public class StudentTH extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentTH() {
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
			
			/*System.out.println(SearchKey);
			System.out.println(SearchRow);*/
			String Student_Username=request.getParameter("Student_Username");
			String BuildingID=request.getParameter("BuildingID");

			PrintWriter out = response.getWriter();
			
			//创建session对象
			HttpSession session =request.getSession();
		
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
			out.flush();
			out.close();
			return;
		}
		
		//查询是否存在
		List<StudentBean> list=new StudentDao().GetList("Student_Username='"+Student_Username+"'", "");
		if(list.size()<1)
		{
			out.print("<script language='javascript'>alert('学号不存在，或学生未处于迁出状态！');history.back(-1);</script>");
			out.flush();
			out.close();
			return;
		}
		
		
		//查询楼宇
		List<BuildingBean> buildinglist=new BuildingDao().GetList("","Building_Name");
//		System.out.println(BuildingID);
		//查询条件
		String strWhere="1=1 ";
		if(!(isInvalid(BuildingID)))
		{
			strWhere+=" and Domitory_BuildingID='"+BuildingID+"'";
		}
		else{
			strWhere+=" and 1=2";
		}
		//查询寝室
		List<DomitoryBean> domitorylist=new DomitoryDao().GetList(strWhere,"Domitory_Name");
		
		//查询学生信息
		StudentBean cnbean=new StudentDao().GetFirstBean("Student_Username='"+Student_Username+"'");
		
		request.setAttribute("buildinglist", buildinglist);
		request.setAttribute("domitorylist", domitorylist);
		if(!isInvalid(BuildingID)){
			request.setAttribute("BuildingID", BuildingID);
		}
		request.setAttribute("cnbean", cnbean);
		request.getRequestDispatcher("StudentTH2.jsp").forward(request, response);
		
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
