<%@page import="com.bean.OutBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<OutBean> list=(List<OutBean>)request.getAttribute("list");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
      <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<center>
  <table width="709" border="0" cellspacing="0" cellpadding="0">
   
    <tr>
      <td height="500" align="center" valign="top"><table width="709" border="0" cellspacing="0" cellpadding="0">
        <tr>
          
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生迁出记录</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr align="center"  class="t1">
                    <td height="25" bgcolor="#D5E4F4"><strong>日期</strong></td>
                    <td bgcolor="#D5E4F4"><strong>姓名</strong></td>
                    <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                    <td bgcolor="#D5E4F4"><strong>班级</strong></td>
                    <td bgcolor="#D5E4F4"><strong>备注</strong></td>
                    </tr>
                  <%for(OutBean o:list){ %>
                    <tr align="center">
                      <td height="25" align="center"><%=o.getOut_Date() %></td>
                      <td><%=o.getStudent_Name() %></td>
                      <td><%=o.getStudent_Sex() %></td>
                      <td><%=o.getStudent_Class() %></td>
                      <td><%=o.getOut_Remark() %></td>
                      </tr>
                  <%} %>
              </table></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
    </tr>
  </table>

</center>
</body>
</html>