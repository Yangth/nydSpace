<%@page import="com.bean.StudentBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

StudentBean cnbean=(StudentBean)request.getAttribute("cnbean");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" language="JavaScript">

$(function(){
$("#Log_Date").change(function(){
   if(!/^(\d{4})-(\d{2})-(\d{2})$/gi.test(this.value)){
     $("#msg").text("日期格式错误!");
     $("#button").attr("disabled",true);
       }else{
        $("#msg").text("日期格式正确!");
        $("#button").attr("disabled",false);
     }

});

});

function mycheck(){
   if(isNull(form1.Log_Date.value)){  
   alert("请输入选择缺寝日期！"); 
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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生缺寝登记</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="LogAddSave" target="TopFram" onSubmit="return mycheck()" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="30" colspan="2" align="center" style="color:red;">请仔细确认信息是否正确，缺寝登记提交后将不可修改！</td>
                    </tr>
                  <tr>
                    <td width="33%" height="30" align="right">学号：</td>
                    <td width="67%"><%=cnbean.getStudent_Username() %>
                      <label for="Log_StudentID"></label>
                      <input name="Log_StudentID" type="text" class="noshow" id="Log_StudentID" value="<%=cnbean.getStudent_ID()%>"></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">姓名：</td>
                    <td><%=cnbean.getStudent_Name() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">性别：</td>
                    <td><%=cnbean.getStudent_Sex() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">班级：</td>
                    <td><%=cnbean.getStudent_Class() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">寝室：</td>
                    <td><%=cnbean.getDomitory_Name() %></td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>缺寝日期：</td>
                    <td><label for="Log_Date"></label>
                      <input type="text" name="Log_Date" id="Log_Date" class="Wdate">
                      <span style="color:red;" id="msg">日期格式为：YYYY-MM-DD格式</span>
                     </td>
                  </tr>
                  <tr>
                    <td height="30" align="right">缺寝备注：</td>
                    <td><textarea name="Log_Remark" id="Log_Remark" cols="45" rows="5"></textarea></td>
                  </tr>
                  <tr>
                    <td height="30">&nbsp;</td>
                    <td><input type="submit" disabled="true" name="button" id="button" value="确认提交">
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
