<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
	
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript">
  		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','新增');
			$('#fm').form('clear');
			url = 'BuildingAddSave';
		}
		function editUser(){
			 var row = $('#mTb').datagrid('getSelected');
			if (row){
				 $('#dlg').dialog('open').dialog('setTitle','修改');
				$('#fm').form('load',row);
				url = 'BuildingUpdateSave?Building_ID=' + row.Building_ID; 
			} 
			
		}
		
		function addteacher(){
		   var row = $('#mTb').datagrid('getSelected');
	      	if (row){
				$("#mfram").attr("src","TBManager.jsp?Building_ID="+row.Building_ID);
				 $('#dd').dialog('open').dialog('setTitle','楼宇管理员设置');
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
			  ids+=row[i].Building_ID+",";
			}
			if (row){
				$.messager.confirm('确认','您确定要删除吗？',function(r){
					if (r){
						$.post('BuildingDel',{Building_ID:ids},function(result){
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
				SearchKey: $('#buildingname').val()
			});
		}
		
  </script>
</head>
<body>
<center>
  <table width="709" border="0" cellspacing="0" cellpadding="0">
   
    <tr>
      <td height="500" align="center" valign="top">
      <table width="709" border="0" cellspacing="0" cellpadding="0">
        <tr>
          
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE">
          <table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">楼宇管理</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
      <table id="mTb" 
  		class="easyui-datagrid" width="100%" 
  		url="BuildingManager"
  		toolbar="#toolbar" 
		rownumbers="true" 
		fitColumns="true">
		<thead>
			<tr>
				<th field="Building_ID" width="50" data-options="hidden: true">编号</th>
				<th field="Building_Name" width="50">楼宇名称</th>
				<th field="Building_Introduction" width="50">简介</th>
			</tr>
		</thead>
  	</table>
  	
	<div id="toolbar">
		<span>楼宇名称:</span>
		<input id="buildingname" style="line-height:16px;border:1px solid #ccc">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="addteacher()">添加管理员</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-button1">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>楼宇名称:</label>
				<input name="Building_Name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>简介:</label>
				<textarea name="Building_Introduction" id="Building_Introduction" cols="45" rows="5"></textarea>
			</div>
		</form>
	</div>
	<div id="dd" class="easyui-dialog" style="width:600px;height:300px;padding:10px 20px" 
	         closed="true">
	<iframe id="mfram" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto" src="" width="100%" height="100%"></iframe>
	</div>
	<div id="dlg-button1">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
		<a href="javascript:void(0)" id="dlgdia" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	</td>
            </tr>
          </table>
          </td>
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
