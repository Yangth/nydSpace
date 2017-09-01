<%@page import="com.bean.BuildingBean"%>
<%@page import="com.bean.DomitoryBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    
     <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
$(function(){
// 省级 
	 $('#build').combobox({
		    url:'/sushe1/StudentRZ?flag=build',
		    valueField:'Building_ID', //值字段
		    textField:'Building_Name', //显示的字段
		    panelHeight:'auto',
		    required:true,
		    editable:true,//不可编辑，只能选择
		    value:'请选择',
		    onChange:function(province){
		    	//$('#city').combobox('clear');
			   $('#domitory').combobox({
			    url:'/sushe1/StudentRZ?flag=domitory&province='+province,
			    valueField:'Domitory_ID', //值字段
				textField:'Domitory_Name', //显示的字段
			    panelHeight:'auto',
			    required:true,
			    editable:true,//不可编辑，只能选择
			    value:'请选择'	
		        });
		    }
		 });
	//县市区 
		  $('#domitory').combobox({
		   url:'/sushe1/StudentRZ?flag=domitory&province='+$('#build').val(),
		    valueField:'Domitory_ID', //值字段
			textField:'Domitory_Name', //显示的字段
		    panelHeight:'auto',
		    required:true,
		    editable:true,//不可编辑，只能选择
		   value:'请选择'
		 });
});
function saveUser(){
     var url='StudentRZSave';
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.success){
				$.messager.show({
					title: '提示',
					msg: result.message
				});
				$('#dlg').dialog('close');		// close the dialog
				$('#mTb').datagrid('reload');	// reload the user data
			} else {
				$.messager.show({
					title: '提示',
					msg: result.message
				});
			}
		}
	});
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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生缺寝记录</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="AdminLogList" target="TopFram" onSubmit="return mycheck()" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="33%" height="30" align="right">&nbsp;</td>
                    <td width="67%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="30" align="right">学生学号：</td>
                    <td><label for="Student_ID"></label>
                      <input type="text" name="Student_Username" id="Student_Username"></td>
                  </tr>
                  <tr>
                    <td height="30">&nbsp;</td>
                    <td><input type="submit" name="button" id="button" value="开始查询"></td>
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
