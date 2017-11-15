<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>jQuery EasyUI CRUD Demo</title>
    <link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.5.2/demo/demo.css">
    <script type="text/javascript" src="./js/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
    <style type="text/css">
        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }
    </style>
    <script type="text/javascript">
        var url;
        function newStuInfo() {
            $('#dlg').dialog('open').dialog('setTitle', '新建用户');
            $('input[name=studentID]').attr('readonly', false);
            $('#fm').form('clear');
            url = 'Student/addStuInfo';
        }
        function editStuInfo() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑用户');
                $('input[name=studentID]').attr('readonly', true);
                $('#fm').form('load', row);
                url = 'Student/updateStuInfo';
            }
        }
        function saveStuInfo() {
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.show({
                            title: 'Info',
                            msg: result.msg,
                            showType: 'fade',
                            style: {
                                right: '',
                                bottom: ''
                            }
                        });
                        $('#dlg').dialog('close');      // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }
            });
        }
        function removeStuInfo() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('Confirm', '你确定要删除用户吗?', function (r) {
                    if (r) {
                        $.post('Student/removeStuInfo', {studentID: row.studentID}, function (result) {
                            if (result.success) {
                                $.messager.show({
                                    title: 'Info',
                                    msg: result.msg,
                                    showType: 'fade',
                                    style: {
                                        right: '',
                                        bottom: ''
                                    }
                                });
                                $('#dg').datagrid('reload');    // reload the user data  
                            } else {
                                $.messager.show({   // show error message  
                                    title: 'Error',
                                    msg: result.msg
                                });
                            }
                        }, 'json');
                    }
                });
            }
        }
        function doSearch() {
            $('#dg').datagrid('load', {
                queryStudentID: $('#studentID').val(),
                queryStuName: $('#stuName').val()
            });
        }
        function doClear() {
            $('#studentID').val("");
            $('#stuName').val("");
            doSearch();
        }
    </script>
</head>
<body>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip"></div>
</div>

<table id="dg" title="My Users" class="easyui-datagrid" style="width:1000px;height:450px"
       url="Student/listStuInfo"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="studentID" width="50">学号</th>
        <th field="stuName" width="50">姓名</th>
        <th field="password" width="50">密码</th>
        <th field="academy" width="70">学院</th>
        <th field="major" width="70">专业</th>
        <th field="telNo" width="50">手机号码</th>
        <th field="sex" width="50">性别</th>
        <th field="email" width="70">邮箱</th>
        <th field="status" width="70">审核状态</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newStuInfo()">New
        User</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editStuInfo()">Edit
        User</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="removeStuInfo()">Remove User</a>
    <div>
        <span>学号:</span>
        <input id="studentID" style="line-height:26px;border:1px solid #ccc">
        <span>姓名:</span>
        <input id="stuName" style="line-height:26px;border:1px solid #ccc">
        <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doClear()">清除</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Student's Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>学号:</label>
            <input name="studentID" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>姓名:</label>
            <input name="stuName" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>密码:</label>
            <input name="password" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>学院:</label>
            <select name="academy">
                <option value="机电工程学院">机电工程学院</option>
                <option value="信息与通信学院">信息与通信学院</option>
                <option value="计算机与信息安全学院">计算机与信息安全学院</option>
                <option value="艺术与设计学院">艺术与设计学院</option>
                <option value="商学院">商学院</option>
                <option value="外国语学院">外国语学院</option>
                <option value="数学与计算科学学院">数学与计算科学学院</option>
                <option value="电子工程与自动化学院">电子工程与自动化学院</option>
                <option value="法学院">法学院</option>
                <option value="材料科学与工程学院">材料科学与工程学院</option>
                <option value="生命与环境科学学院">生命与环境科学学院</option>
                <option value="建筑与交通工程学院">建筑与交通工程学院</option>
                <option value="海洋信息工程学院">海洋信息工程学院</option>
                <option value="体育部">体育部</option>
                <option value="国际学院">国际学院</option>
            </select>
        </div>
        <div class="fitem">
            <label>专业:</label>
            <input name="major" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>手机号码:</label>
            <input name="telNo" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>性别:</label>
            <select name="sex">
                <option value="男" selected="selected">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div class="fitem">
            <label>邮箱:</label>
            <input name="email" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>审核状态:</label>
            <input name="status" class="easyui-validatebox" required="true">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStuInfo()">Save</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
</body>
</html>  