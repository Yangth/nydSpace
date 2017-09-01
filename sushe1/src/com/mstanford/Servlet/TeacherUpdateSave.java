package com.mstanford.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.TeacherBean;
import com.dao.TeacherDao;
import com.google.gson.JsonObject;

public class TeacherUpdateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TeacherUpdateSave() {
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
				
				String Teacher_ID=request.getParameter("Teacher_ID");
				String Teacher_Username=request.getParameter("Teacher_Username");
				String Teacher_Name=request.getParameter("Teacher_Name");
				String Teacher_Sex=request.getParameter("Teacher_Sex");
				String Teacher_Tel=request.getParameter("Teacher_Tel");
				String Teacher_Password=request.getParameter("Teacher_Password");

                 JsonObject jsonb=new JsonObject();

				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session =request.getSession();
		
		      //权限验证
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				
				//��ѯ�û����Ƿ����
				List<TeacherBean> list=new TeacherDao().GetList("Teacher_Username='"+Teacher_Username+"' and Teacher_ID!="+Teacher_ID, "");
				if(list.size()>0)
				{
					out.print("<script language='javascript'>alert('用户名已经存在！');history.back(-1);</script>");
					out.flush();out.close();
				}
				//
				TeacherBean cnbean=new TeacherBean();
				cnbean=new TeacherDao().GetBean(Integer.parseInt(Teacher_ID));
				cnbean.setTeacher_Username(Teacher_Username);
				cnbean.setTeacher_Name(Teacher_Name);
				cnbean.setTeacher_Sex(Teacher_Sex);
				cnbean.setTeacher_Tel(Teacher_Tel);
				if(!(isInvalid(Teacher_Password)))
				{
					cnbean.setTeacher_Password(Teacher_Password);
				}
				if(new TeacherDao().Update(cnbean)){
					jsonb.addProperty("success", true);
					jsonb.addProperty("message", "更新成功！");
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
