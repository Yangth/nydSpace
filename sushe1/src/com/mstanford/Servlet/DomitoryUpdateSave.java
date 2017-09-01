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
import com.dao.DomitoryDao;
import com.google.gson.JsonObject;

public class DomitoryUpdateSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DomitoryUpdateSave() {
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
		
		String Domitory_ID=request.getParameter("Domitory_ID");
		String Domitory_Name=request.getParameter("Domitory_Name");
		String Domitory_BuildingID=request.getParameter("Requestbuil");
		String Domitory_Type=request.getParameter("Domitory_Type");
		String Domitory_Number=request.getParameter("Domitory_Number");
		String Domitory_Tel=request.getParameter("Domitory_Tel");

          JsonObject jsonb=new JsonObject();

		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session =request.getSession();
		
		    //权限验证
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');top.location='Login.jsp';</script>");
					out.flush();out.close();
				}
				
				//验证编号
				List<DomitoryBean> list=new DomitoryDao().GetList("Domitory_Name='"+Domitory_Name+"' and Domitory_BuildingID="+Domitory_BuildingID+" and Domitory_ID!="+Domitory_ID, "");
				if(list.size()>0)
				{
					out.print("<script language='javascript'>alert('宿舍编号有误！');history.back(-1);</script>");
					out.flush();out.close();
				}
				//更新操作
				
				DomitoryBean cnbean=new DomitoryBean();
				cnbean=new DomitoryDao().GetBean(Integer.parseInt(Domitory_ID));
				cnbean.setDomitory_BuildingID(Integer.parseInt(Domitory_BuildingID));
				cnbean.setDomitory_Name(Domitory_Name);
				cnbean.setDomitory_Type(Domitory_Type);
				cnbean.setDomitory_Number(Domitory_Number);
				cnbean.setDomitory_Tel(Domitory_Tel);
				
				if(new DomitoryDao().Update(cnbean)){
					//返回结果数据
					jsonb.addProperty("success", true);
					jsonb.addProperty("message", "更新成功！");
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
