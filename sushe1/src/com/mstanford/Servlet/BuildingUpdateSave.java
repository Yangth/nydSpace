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
import com.dao.BuildingDao;
import com.google.gson.JsonObject;

public class BuildingUpdateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BuildingUpdateSave() {
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
				
				String Building_Name=request.getParameter("Building_Name");
				String Building_ID=request.getParameter("Building_ID");
				String Building_Introduction=request.getParameter("Building_Introduction");

				JsonObject jsonb=new JsonObject();

				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
		
		
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
					return;
				}
				
				
				List<BuildingBean> list=new BuildingDao().GetList("Building_Name='"+Building_Name+"' and Building_ID!="+Building_ID, "");
				if(list.size()>0)
				{
					jsonb.addProperty("success", false);
				     jsonb.addProperty("message", "名称已存在！");
				     out.write(jsonb.toString());		
				     out.flush();
					out.close();
					return;
				}
				
				BuildingBean cnbean;
				cnbean=new BuildingDao().GetBean(Integer.parseInt(Building_ID));
				cnbean.setBuilding_Name(Building_Name);
				cnbean.setBuilding_Introduction(Building_Introduction);
				if(new BuildingDao().Update(cnbean)){
					//插入数据
					jsonb.addProperty("success", true);
				     jsonb.addProperty("message", "修改成功！");
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
