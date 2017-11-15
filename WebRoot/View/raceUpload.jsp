<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>竞赛发布中心</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="./css/uploadJsp.css">
    <script type="text/javascript" src="./js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="ckeditor/config.js"></script>
    <script type="text/javascript">
        $(function () {
            //处理CKEDITOR的值 处理获取图片的地址
            function CKupdate() {
                for (instance in CKEDITOR.instances)
                    CKEDITOR.instances[instance].updateElement();
            }
        });
        window.onload = function () {
            CKEDITOR.replace('information');
        };
        function cheakForm() {
            $('#information').val(CKEDITOR.instances.information.getData());//取得textarea的值
            document.getElementById('myForm').submit();
        }
    </script>
</head>

<body>
<form action="Race/uploadRace" method="post" enctype="multipart/form-data" id="myForm" style="margin-left: 130px;">
    <table class="uploadTable">
        <!--   <tr>
             <td class="l_td"><span class="star">*</span><label>赛事编号：</label></td>
             <td class="r_td"><input name="raceID" type="text"/></td>
         </tr>-->
        <tr>
            <td class="l_td"><span class="star">*</span><label>赛事名称：</label></td>
            <td class="r_td"><input name="title" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>主办单位：</label></td>
            <td class="r_td">
                <select name="sponsor">
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
            </td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>级别：</label></td>
            <td class="r_td"><input name="grade" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>比赛地点：</label></td>
            <td class="r_td"><input name="location" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>比赛时间：</label></td>
            <td class="r_td"><input name="raceDate" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>报名时间：</label></td>
            <td class="r_td"><input name="entryDate" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>比赛形式：</label></td>
            <td class="r_td"><input name="entryForm" type="text"/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>比赛状态：</label></td>
            <td class="r_td">
                <select name="status">
                    <option value="未开始" selected="selected">未开始</option>
                    <option value="正在报名">正在报名</option>
                    <option value="正在进行">正在进行</option>
                    <option value="已结束">已结束</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="l_td"><label>附件：</label></td>
            <td class="r_td"><input name="attachment" type="file"/><span style="color:red">（文件大小不能超过20M）</span></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>内容:</label></td>
            <td class="r_td"><textarea cols="100" id="information" name="information" rows="10"></textarea></td>
        </tr>
        <tr>
            <td class="l_td"></td>
            <td class="submit_btn"><input type="button" value="发布" onclick="cheakForm();"/></td>
        </tr>
    </table>
</form>
</body>
</html>
