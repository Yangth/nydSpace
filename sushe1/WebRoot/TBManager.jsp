<%@page import="com.bean.TBBean"%>
<%@page import="com.bean.TeacherBean"%>
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
<script language="JavaScript">
 var url;
function newUser(){
	url = 'TBAddSave?Teacher_ID='+$('#combx').val()+'&addbuilding_id='+$('#addbuilding_id').val();
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
function removeUser(){
    var ids="";
	var row = $('#mTb').datagrid('getSelections');
	for(var i=0;i<row.length;i++){
	  ids+=row[i].TB_ID+",";
	}
	if (row){
		$.messager.confirm('确认','您确定要删除吗？',function(r){
			if (r){
				$.post('TBDel',{TB_ID:ids},function(result){
					if (result.success){
						$.messager.show({	// show error message
								title: '提示',
								msg: result.message
							});
						$('#mTb').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: '提示',
							msg: result.message
						});
					}
				},'json');
			}
		});
	}
}                                                                                                                                                                             
function loadData() {
   $.ajax({
    type : "POST",                                            // 使用post方法访问后台
    dataType : "json",                                        // 返回json格式的数据
    url: "TBManager?flag=comb",                                    // 要访问的后台地址
    data : {
           "classId": "8a58ab44419c341d01419c5595580027"
        },
    complete : function() {}, 
    success : function(result) {// result为返回的数据
     $('#combx').combobox({
      data : result,
      valueField:'Teacher_ID',
      textField:'Teacher_Name'
     });
    }
    
   });
  }
</script>
<body onload="loadData()">
<center>
  <table width="709" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td height="500" align="center" valign="top"><table width="709" border="0" cellspacing="0" cellpadding="0">
        <tr>
         
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
     <table id="mTb" 
  		class="easyui-datagrid" width="100%" 
  		url="TBManager?flag=query&Building_ID=<%=request.getParameter("Building_ID")%>"
  		toolbar="#toolbar" 
		rownumbers="true" 
		fitColumns="true">
		<thead>
			<tr>
			    <th field="TB_ID" width="50" data-options="hidden: true">管理员ID</th>
				<th field="Teacher_Name" width="50">管理员姓名</th>
				<th field="Building_Name" width="50">楼宇名称</th>
				<th field="Teacher_Sex" width="50">管理员性别</th>
				<th field="Teacher_Tel" width="50">管理员电话</th>
			</tr>
		</thead>
  	</table>
  	
	<div id="toolbar">
	  <form id="fm" method="post" novalidate>
		<span>选择管理员:</span>
		<input id="combx" class="easyui-combobox" data-options="panelHeight:'auto'" style="width:100px;" url="">
	    <input type="hidden" id="addbuilding_id" name="addbuilding_id" value="<%=request.getParameter("Building_ID")%>">
	 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="newUser()">点击添加</a>
	  </form>
	</div>
             </td>
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
