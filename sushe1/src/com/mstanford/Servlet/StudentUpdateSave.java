package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.StudentBean;
import com.dao.StudentDao;
import com.google.gson.JsonObject;

public class StudentUpdateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentUpdateSave() {
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
		
		String Student_Username=request.getParameter("Student_Username");
		String Student_ID=request.getParameter("Student_ID");
		String Student_Name=request.getParameter("Student_Name");
		String Student_Sex=request.getParameter("Student_Sex");
		String Student_Class=request.getParameter("Student_Class");
		String Student_Password=request.getParameter("Student_Password");



		PrintWriter out = response.getWriter();
		
		JsonObject jsonb=new JsonObject();
		//创建session对象
		HttpSession session =request.getSession();
		
		//权限验证
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				
				//编号验证
				List<StudentBean> list=new StudentDao().GetList("Student_Username='"+Student_Username+"' and Student_ID!="+Student_ID, "");
				if(list.size()>0)
				{
					out.print("<script language='javascript'>alert('编号有误！');history.back(-1);</script>");
					out.flush();out.close();
				}
				//修改
				
				StudentBean cnbean=new StudentBean();
				cnbean=new StudentDao().GetAllBean(Integer.parseInt(Student_ID));
				cnbean.setStudent_Username(Student_Username);
				cnbean.setStudent_Name(Student_Name);
				cnbean.setStudent_Sex(Student_Sex);
				cnbean.setStudent_Class(Student_Class);
				if(!(isInvalid(Student_Password)))
				{
					cnbean.setStudent_Password(Student_Password);
				}
				if(new StudentDao().Update(cnbean)){
					jsonb.addProperty("success", true);
					jsonb.addProperty("message", "添加成功！");
					out.write(jsonb.toString());
					out.flush();out.close();
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
