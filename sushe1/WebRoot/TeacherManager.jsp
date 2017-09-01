<%@page import="com.dao.TeacherDao"%>
<%@page import="com.bean.TeacherBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
  		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','新增');
			$('#fm').form('clear');
			url = 'TeacherAddSave';
		}
		function editUser(){
		$('#passworddiv').css("display","none");
			 var row = $('#mTb').datagrid('getSelected');
			if (row){
				 $('#dlg').dialog('open').dialog('setTitle','修改');
				$('#fm').form('load',row);
				url = 'TeacherUpdateSave?Teacher_ID=' + row.Teacher_ID; 
			} 
			
		}
		function saveUser(){
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
			  ids+=row[i].Teacher_ID+",";
			}
			if (row){
				$.messager.confirm('确认','您确定要删除吗？',function(r){
					if (r){
						$.post('TeacherDel',{Teacher_ID:ids},function(result){
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
		 function doSearch(){
			$('#mTb').datagrid('load',{
				SearchRow: $('#combSel').val(),
				SearchKey: $('#searchkey').val()
			});
		}
		
  </script>
<body>
<center>
      <table width="709" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">楼宇管理员管理</td>
            </tr>
            <tr>
              
      <table id="mTb" 
  		class="easyui-datagrid" width="100%" 
  		url="TeacherManager"
  		toolbar="#toolbar" 
		rownumbers="true" 
		fitColumns="true">
		<thead>
			<tr>
				<th field="Teacher_ID" width="50" data-options="hidden: true">编号</th>
				<th field="Teacher_Name" width="50">姓名</th>
				<th field="Teacher_Username" width="50" >用户名</th>
				<th field="Teacher_Sex" width="50">性别</th>
				<th field="Teacher_Tel" width="50">电话</th>
				
			</tr>
		</thead>
  	</table>
  	
	<div id="toolbar">
		<input id="combSel" class="easyui-combobox" data-options="panelHeight:'auto'" style="width:100px;" url="data/combobox_data.json" valueField="id" textField="text">
		<input id="searchkey" style="line-height:16px;border:1px solid #ccc">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>用户名:</label>
				<input name="Teacher_Username"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem" id="passworddiv">
				<label>请输入密码:</label>
				<input name="Teacher_Password" id="password" type="password" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>姓名:</label>
				<input name="Teacher_Name"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>性别:</label>
				<input name="Teacher_Sex"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>电话:</label>
				<input name="Teacher_Tel"  class="easyui-validatebox" required="true">
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
            </table>
      </td>
    </tr>
     <tr>
      <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
    </tr>
  </table>

</center>
</body>
</html>
