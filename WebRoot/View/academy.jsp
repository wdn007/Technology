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
            $('#dlg').dialog('open').dialog('setTitle', '添加学院信息');
            $('select[name=academy]').attr('disabled', false);
            $('#fm').form('clear');
            url = 'Academy/addAcademyInfo';
        }
        function editStuInfo() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑学院信息');
                $('select[name=academy]').attr('disabled', true);
                $('#fm').form('load', row);
                url = 'Academy/updateAcademyInfo';
            }
        }
        function saveStuInfo() {
            $('select[name=academy]').attr('disabled', false);
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
                        $('select[name=academy]').attr('disabled', false);
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
            //alert(row.id);
            if (row) {
                $.messager.confirm('Confirm', '你确定要删除用户吗?', function (r) {
                    if (r) {
                        $.post('Academy/removeAcademyInfo', {academy: row.academy}, function (result) {
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
                queryID: $('#academy').val()
            });
        }
        function doClear() {
            $('#academy').val("");
            doSearch();
        }
    </script>
</head>
<body>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip"></div>
</div>

<table id="dg" title="Management" class="easyui-datagrid" style="width:500px;height:450px"
       url="Academy/listAcademyInfo"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="academy" width="50">学院</th>
        <th field="principalID" width="70">负责人工号</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newStuInfo()">New
        Element</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editStuInfo()">Edit
        Element</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="removeStuInfo()">Remove Element</a>
    <div>
        <span>学院:</span>
        <input id="academy" style="line-height:26px;border:1px solid #ccc">
        <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Academy's Information</div>
    <form id="fm" method="post" novalidate>
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
            <label>负责人工号:</label>
            <input name="principalID" class="easyui-validatebox" required="true">
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