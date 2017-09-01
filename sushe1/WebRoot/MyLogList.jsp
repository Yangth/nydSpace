<%@page import="com.bean.TBBean"%>
<%@page import="com.bean.LogBean"%>
<%@page import="com.bean.DomitoryBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<DomitoryBean> domitorylist=(List<DomitoryBean>)request.getAttribute("domitorylist");
List<LogBean> lists=(List<LogBean>)request.getAttribute("lists");

List<TBBean> Buildinglist=(List<TBBean>)request.getAttribute("Buildinglist");

int DomitoryID=0;
String Domitory_ID=(String)request.getAttribute("Domitory_ID");

if(Domitory_ID!=null){
  DomitoryID=Integer.parseInt(Domitory_ID);
}

String Building_ID=(String)request.getAttribute("BuildingID");
int BuildingID=Integer.parseInt(Building_ID);
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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生缺寝记录</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="MyLogList" target="TopFram">
                <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="28%" height="30" style="padding-left:20px;"> 功能导航： <a href="MyStudent" target="TopFram">返回上层</a></td>
                    <td width="72%">查询：
                    <select name="BuildingID" id="BuildingID" onChange="javascript:window.location='MyLogList?BuildingID='+this.value;">
                        <option value="">全部楼宇</option>
                        <%for(TBBean t:Buildinglist){ %>
                        <option value="<%=t.getTB_BuildingID()%>" <c:if test="<%=t.getTB_BuildingID()==BuildingID %>">selected</c:if>><%=t.getBuilding_Name()%></option>
                       <%} %>
                      </select>
                      <select name="Domitory_ID" id="Domitory_ID">
                        <option value="">全部寝室</option>
                        <%for(DomitoryBean d:domitorylist){ %>
                        <option value="<%=d.getDomitory_ID()%>" <c:if test="<%=d.getDomitory_ID()==DomitoryID %>">selected</c:if>><%=d.getDomitory_Name()%></option>
                       <%} %>
                      </select>
                      <select name="SearchRow" id="SearchRow">
                        <option value="Student_Name">姓名</option>
                        <option value="Student_Username">学号</option>
                        <option value="Student_Class">班级</option>
                      </select>
                      <input name="SearchKey" type="text" class="text1" id="SearchKey">
                      <input type="submit" name="button" id="button" value="点击查询">
                      <label for="Building_ID"></label>
                      <input name="Building_ID" type="text" class="noshow" id="Building_ID" value="<%=BuildingID%> "></td>
                  </tr>
                </table>
              </form>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr align="center"  class="t1">
                    <td height="25" bgcolor="#D5E4F4"><strong>寝室号</strong></td>
                    <td bgcolor="#D5E4F4"><strong>姓名</strong></td>
                    <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                    <td bgcolor="#D5E4F4"><strong>班级</strong></td>
                    <td bgcolor="#D5E4F4"><strong>日期</strong></td>
                    <td bgcolor="#D5E4F4"><strong>备注</strong></td>
                    </tr>
                  <%for(LogBean l:lists){ %>
                    <tr align="center">
                      <td height="25" align="center"><%=l.getDomitory_Name() %></td>
                      <td><%=l.getStudent_Name() %></td>
                      <td><%=l.getStudent_Sex() %></td>
                      <td><%=l.getStudent_Class() %></td>
                      <td><%=l.getLog_Date() %></td>
                      <td><%=l.getLog_Remark() %></td>
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
