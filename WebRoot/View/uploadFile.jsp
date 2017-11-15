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

    <title>科技竞赛信息管理系统</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="./css/uploadJsp.css">
</head>

<body>
<form action="Upload/uploadFile" method="post" enctype="multipart/form-data" id="myForm" style="margin-left: 130px;">
    <h1 style="font-size: 18px;">提示：上传的文件可到 "<a style="text-decoration: none;" target="_blank"
                                                href="${pageContext.request.contextPath}/Upload/showDownloadList">下载中心</a>"
        进行查看和下载</h1>
    <table class="uploadTable">
        <tr>
            <td class="l_td"><label>文件：</label></td>
            <td class="r_td"><input name="attachment" type="file"/><span style="color:red">（文件大小不能超过20M）</span></td>
        </tr>
        <tr>
            <td class="l_td"></td>
            <td class="submit_btn"><input type="submit" value="上传"/></td>
        </tr>
    </table>
</form>
</body>
</html>
