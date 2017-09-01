<%@page import="com.dao.StudentDao"%>
<%@page import="com.dao.TeacherDao"%>
<%@page import="com.dao.AdminDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[]cookie=request.getCookies();
if(cookie!=null){
String name=null;String pw=null;String type=null;
for(Cookie cs:cookie){
     if(cs.getName().equals("Username")){
       name=cs.getValue();
     }
     if(cs.getName().equals("Password")){
       pw=cs.getValue();
     }
     if(cs.getName().equals("Type")){
     type=cs.getValue();
     }
}

if(name!=null&&pw!=null&&type!=null){
  if(type.equals("SystemManager")){
  String Admin_ID=new AdminDao().CheckLogin(name, pw);
  if(Admin_ID!=null){
  session.setAttribute("id", Admin_ID);
  session.setAttribute("type", 1);
  request.getRequestDispatcher("Index.jsp").forward(request, response);
  }
  }
  
  if(type.equals("BuildingManager")){
   String Teacher_ID=new TeacherDao().CheckLogin(name, pw);
  if(Teacher_ID!=null){
   session.setAttribute("id", Teacher_ID);
  session.setAttribute("type", 2);
  request.getRequestDispatcher("Index.jsp").forward(request, response);
  }
  }
  
  if(type.equals("Student")){
   String Student_ID=new StudentDao().CheckLogin(name, pw);
  if(Student_ID!=null){
  session.setAttribute("id", Student_ID);
  session.setAttribute("type", 3);
  request.getRequestDispatcher("Index.jsp").forward(request, response);
  }
  }
   
}
}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>"> 
  <title>校园宿舍管理系统</title>
  <meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="Style/Style.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript">

function mycheck(){
   if(isNull(form1.Type.value)){  
   alert("请选择身份！"); 
   return false;
   }
   if(isNull(form1.Username.value)){  
   alert("请输入用户名！"); 
   return false;
   }
   if(isNull(form1.Password.value)){
   alert("请输入密码！");
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
  <br><br><br><br><br>
  <table width="684" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="292" align="center" valign="top" background="Images/LoginBg.jpg">
      <table width="350" height="201" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="72" align="center"><h3>校园宿舍管理系统</h3></td>
        </tr>
        <tr>
          <td align="center" valign="top">
             <form name="form1" action="GoLogin" method="post" onSubmit="return mycheck()">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
               
                <tr>
                  <td height="30" align="right" class="STYLE2">身份：</td>
                  <td align="left"><select name="Type" id="Type">
                    <option value="">请选择</option>
                    <option value="SystemManager">系统管理员</option>
                    <option value="BuildingManager">楼宇管理员</option>
                    <option value="Student">学生</option>
                  </select></td>
                  </tr>
                <tr>
                  <td width="37%" height="30" align="right" class="STYLE2">用户名：</td>
                  <td width="300" align="left"><input type="text" name="Username" id="Username" class="text1" /></td>
                  </tr>
                <tr>
                  <td height="30" align="right" class="STYLE2">密码：</td>
                  <td align="left"><input type="password" name="Password" id="Password" class="text1" /></td>
                  </tr>
                <tr>
                  <td height="30" colspan="2" align="center">
                  <label>
                    <input type="submit" name="button" id="button" value="登录">
                    <input type="checkbox" name="autodl" id="autodl" value="ok">自动登录
                  </label>
                  </td>
                   
                  </tr>
              </table>
              </form>
          
          </td>
        </tr>
      </table></td>
    </tr>
  </table>


</center>
</body>
</html>
