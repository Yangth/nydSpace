<%@page import="com.bean.StudentBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

StudentBean cnbean=(StudentBean)request.getAttribute("cnbean");
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
<script language="JavaScript">


function mycheck(){
   
   if(isNull(form1.Building_ID.value)){
   alert("请选择楼宇！");
   return false;
   }
   if(isNull(form1.Domitory_ID.value)){
   alert("请选择寝室！");
   return false;
   }

}

function isNull(str){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
}
   
   
</script>
<body>
<center>
  <table width="709" border="0" cellspacing="0" cellpadding="0">
   
    <tr>
      <td height="500" align="center" valign="top"><table width="709" border="0" cellspacing="0" cellpadding="0">
        <tr>
         
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生迁出登记</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="StudentQCSave" target="TopFram" onSubmit="return mycheck()" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="30" colspan="2" align="center" style="color:red;">此功能只适用于毕业的学生迁出，迁出后将不可恢复！ 确定要迁出学生吗？</td>
                    </tr>
                  <tr>
                    <td height="30" align="right">学生学号：</td>
                    <td><%=cnbean.getStudent_Username() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">学生姓名：</td>
                    <td><%=cnbean.getStudent_Name() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">学生性别：</td>
                    <td><%=cnbean.getStudent_Sex() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">目前楼宇：</td>
                    <td><%=cnbean.getBuilding_Name() %></td>
                  </tr>
                  <tr>
                    <td width="33%" height="30" align="right">目前寝室：</td>
                    <td width="67%"><%=cnbean.getDomitory_Name() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">迁出备注：</td>
                    <td><textarea name="Out_Remark" id="Out_Remark" cols="45" rows="5"></textarea></td>
                  </tr>
                  <tr>
                    <td height="30"><input name="Student_ID" type="text" class="noshow" id="Student_ID" value="<%=cnbean.getStudent_ID() %>"></td>
                    <td><input type="submit" name="button" id="button" value="确定迁出">
                      &nbsp;&nbsp;
                      <input type="button" name="button2" id="button2" value="返回上页" onClick="javascript:history.back(-1);"></td>
                  </tr>
                </table>
              </form></td>
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
