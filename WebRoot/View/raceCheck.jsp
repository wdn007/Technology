<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>竞赛审核中心</title>

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
            CKEDITOR.replace("information");
        };
        function cheakForm() {
            $("#information").val(CKEDITOR.instances.information.getData());//取得textarea的值
            var applyStatus = $("select[name='applyStatus']").val();
            if (applyStatus == '1') {
                if (confirm("提交之后无法再次修改竞赛级别，确认提交？"))
                    document.getElementById('myForm').submit();
                else
                    $("input[name='grade']").focus();
            } else {
                document.getElementById('myForm').submit();
            }
        }
        $(function () {
            if (${raceInfo.accessory == null || raceInfo.accessory == ""}) {
                $('#attach').hide();
            }
        });
    </script>
</head>

<body>
<form action="Race/saveRaceCheck" method="post" enctype="multipart/form-data" id="myForm">
    <input type="hidden" name="accessory" value="${raceInfo.accessory}"/>
    <input type="hidden" name="uploadUsername" value="${raceInfo.uploadUsername}"/>
    <table class="uploadTable">
        <tr>
            <td class="l_td"><label>赛事编号：</label></td>
            <td class="r_td"><input name="raceID" type="text" value="${raceInfo.raceID}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>赛事名称：</label></td>
            <td class="r_td"><input name="title" type="text" value="${raceInfo.title}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>主办单位：</label></td>
            <td class="r_td"><input name="sponsor" type="text" value="${raceInfo.sponsor}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>级别：</label></td>
            <td class="r_td"><input name="grade" type="text" value="${raceInfo.grade}"/></td>
        </tr>
        <tr>
            <td class="l_td"><label>比赛地点：</label></td>
            <td class="r_td"><input name="location" type="text" value="${raceInfo.location}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>比赛时间：</label></td>
            <td class="r_td"><input name="raceDate" type="text" value="${raceInfo.raceDate}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>报名时间：</label></td>
            <td class="r_td"><input name="entryDate" type="text" value="${raceInfo.entryDate}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>比赛形式：</label></td>
            <td class="r_td"><input name="entryForm" type="text" value="${raceInfo.entryForm}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><label>比赛状态：</label></td>
            <td class="r_td"><input name="status" type="text" value="${raceInfo.status}" readonly/></td>
        </tr>
        <tr>
            <td class="l_td"><span class="star">*</span><label>审核状态：</label></td>
            <td class="r_td">
                <select name="applyStatus">
                    <option value="">请选择审核状态</option>
                    <option value="1">审核通过</option>
                    <option value="0">审核失败</option>
                </select>
            </td>
        </tr>
        <tr id="attach">
            <td class="l_td"><label>附件：</label></td>
            <td class="r_td">
                <a href="javascript:void(0);" onclick="doDownload('${raceInfo.accessory}')"
                   title="${fn:substring(raceInfo.accessory, fn:indexOf(raceInfo.accessory, '_') + 1, fn:length(raceInfo.accessory))}">
                    ${fn:substring(raceInfo.accessory, fn:indexOf(raceInfo.accessory, '_') + 1, fn:length(raceInfo.accessory))}</a>
            </td>
        </tr>
        <tr>
            <td class="l_td"><label>内容:</label></td>
            <td class="r_td"><textarea cols="100" id="information" name="information"
                                       rows="10" readonly>${raceInfo.information}</textarea></td>
        </tr>
        <tr>
            <td class="l_td"></td>
            <td class="submit_btn"><input type="button" value="提交审核" onclick="cheakForm();"/></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    function doDownload(data) {
        var myForm = document.createElement("form");
        myForm.method = "post";
        myForm.action = "Upload/download";
        var myInput = document.createElement("input");
        myInput.setAttribute("name", "dloadfilename");
        myInput.setAttribute("value", data);
        myForm.appendChild(myInput);
        document.body.appendChild(myForm);
        myForm.submit();
        document.body.removeChild(myForm);
    }
</script>
</body>
</html>
