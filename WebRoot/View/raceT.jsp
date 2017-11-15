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
    <script type="text/javascript" src="./js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
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
        function editStuInfo() {
            var row = $('#dg').datagrid('getSelected');
            var title = '竞赛信息修改';
            if (row) {
                url = 'Race/findRaceInfo?raceID=' + row.raceID;
                window.parent.addNewTab(url, title);
            }
        }

        function editPrize() {
            var row = $('#dg').datagrid('getSelected');
            var title = '获奖信息录入';
            if (row) {
                url = 'Race/prizeUpload?raceID=' + row.raceID;
                window.parent.addNewTab(url, title);
            }
        }

        function removeStuInfo() {
            var row = $('#dg').datagrid('getSelected');
            //alert(row.id);
            if (row) {
                $.messager.confirm('Confirm', '你确定要删除这条记录吗?', function (r) {
                    if (r) {
                        $.post('Race/removeRaceInfo', {raceID: row.raceID}, function (result) {
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
                queryUploadTimeS: $('#uploadTimeS').val(),
                queryUploadTimeE: $('#uploadTimeE').val(),
                queryGrade: $('#grade').val(),
                querySponsor: $('#sponsor').val()
            });
        }
        function doClear() {
            $('#uploadTimeS').datebox('setValue', '');
            $('#uploadTimeE').datebox('setValue', '');
            $('#grade').val("");
            $('#sponsor').val("");
            doSearch();
        }
    </script>
</head>
<body>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip"></div>
</div>
<table id="dg" title="Management" class="easyui-datagrid" style="width:950px;height:450px"
       url="Race/listRaceInfo"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="false" singleSelect="true">
    <thead>
    <tr>
        <th field="raceID" width="70px">赛事编号</th>
        <th field="title" width="240px">赛事名称</th>
        <th field="sponsor" width="90px">主办单位</th>
        <th field="grade" width="50px">级别</th>
        <th field="location" width="80px">比赛地点</th>
        <th field="raceDate" width="100px">比赛时间</th>
        <th field="entryDate" width="90px">报名时间</th>
        <th field="entryForm" width="70px">比赛形式</th>
        <!--  <th field="information" width="50px">信息详情</th>   -->
        <th field="uploadTime" width="140px">发布时间</th>
        <!--  <th field="uploadUsername" width="70px">发布者</th>   -->
        <th field="status" width="50px">状态</th>
        <th field="applyStatus" width="50px">审核状态</th>
        <th field="accessory" width="400px">附件</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editStuInfo()">修改内容</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="removeStuInfo()">删除</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPrize()">发布获奖</a>
    <div>
        <span>起止时间:</span>
        <input id="uploadTimeS" type="text" class="easyui-datebox" editable="false"
               style="width:100px;height:30px;line-height:26px;border:1px solid #ccc">
        <span>-</span>
        <input id="uploadTimeE" type="text" class="easyui-datebox" editable="false"
               style="width:100px;height:30px;line-height:26px;border:1px solid #ccc">
        <span>（为空则查询指定日期）</span>
        <span>级别:</span>
        <input id="grade" style="width:70px;line-height:26px;border:1px solid #ccc">
        <span>学院:</span>
        <select id="sponsor" style="line-height:26px;border:1px solid #ccc;height:30px;">
            <option value=""></option>
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
        <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doClear()">清除</a>
    </div>
</div>
</body>
</html>  