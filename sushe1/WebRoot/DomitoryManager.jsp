<%@page import="com.bean.BuildingBean"%>
<%@page import="com.bean.DomitoryBean"%>
<%@page import="com.mstanford.Servlet.DomitoryManager"%>
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
	var url;
function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','新增');
	$('#fm').form('clear');
	url = 'DomitoryAddSave';
}
function editUser(){
	 var row = $('#mTb').datagrid('getSelected');
	if (row){
		 $('#dlg').dialog('open').dialog('setTitle','修改');
		$('#fm').form('load',row);
		url = 'DomitoryUpdateSave?Domitory_ID=' + row.Domitory_ID; 
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
	  ids+=row[i].Domitory_ID+",";
	}
	if (row){
		$.messager.confirm('确认','您确定要删除吗？',function(r){
			if (r){
				$.post('DomitoryDel',{Domitory_ID:ids},function(result){
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
		    Domitory_BuildingID:$('#searchbuilding').val(),
			SearchRow: $('#searchRow').val(),
			SearchKey: $('#searchkey').val()
		});
	}
		
function loadData() {
   $.ajax({
    type : "POST",                                            // 使用post方法访问后台
    dataType : "json",                                        // 返回json格式的数据
    url: "DomitoryManager?flag=comb",                                    // 要访问的后台地址
    data : {
           "classId": "8a58ab44419c341d01419c5595580027"
        },
    complete : function() {}, 
    success : function(result) {// result为返回的数据
     $('#searchbuilding').combobox({
      data : result,
      valueField:'Building_ID',
      textField:'Building_Name'
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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">宿舍管理</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
     <table id="mTb" 
  		class="easyui-datagrid" width="100%" 
  		url="DomitoryManager?flag=query"
  		toolbar="#toolbar" 
		rownumbers="true" 
		fitColumns="true">
           <thead>
	          <tr>
				<th field="Domitory_ID" width="50" data-options="hidden: true">编号</th>
				<th field="Domitory_Name" width="50">宿舍名</th>
				<th field="Domitory_Number" width="50" >宿舍编号</th>
				<th field="Domitory_Type" width="50">类型</th>
				<th field="Domitory_Tel" width="50">宿舍电话</th>
				<th field="Building_Name" width="50">所在楼宇</th>
             </tr>
          </thead>
  	</table>
  	
	<div id="toolbar">
	<Lable>选择楼宇</Lable>
		<input id="searchbuilding" name="searchbuilding" class="easyui-combobox" url="" data-options="panelHeight:'auto'">
	   <select name="SearchRow" id="SearchRow">
          <option value="Domitory_Name">寝室号</option>
          <option value="Domitory_Tel">电话</option>
        </select>
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
				<label>宿舍名称:</label>
				<input name="Domitory_Name"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>宿舍编号:</label>
				<input name="Domitory_Number"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>宿舍类型:</label>
				<input name="Domitory_Type"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>电话:</label>
				<input name="Domitory_Tel"  class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
			   <Label>选择楼宇:</Label>
		       <input name="Requestbuil" id="Requestbuil" class="easyui-combobox" value="" style="width:100px;" data-options="
					panelHeight:'auto',
					url:'/sushe1/DomitoryManager?flag=comb',
					method:'post',
					valueField:'Building_ID',
					textField:'Building_Name',
					multiple:true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
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
