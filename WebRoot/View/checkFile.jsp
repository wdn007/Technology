<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>文件审核</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="./css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="./css/uploadJsp.css">
    <link rel="stylesheet" href="./css/cn.css"/>
</head>
<body>
<div class="main">
    <div class="m col-md-10">
        <div class="blk4 bf"></div>
        <div class="minfo">
            <form action="Prize/addPrizeForm" method="post" id="myForm">
                <div class="tr">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr class="tableHead">
                            <th>文件编号</th>
                            <th>文件名称</th>
                            <th>上传时间</th>
                            <th>审核状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${UploadInfoList}" var="List">
                            <tr>
                                <td>${List.id}</td>
                                <td>${fn:substring(List.path, fn:indexOf(List.path, '_') + 1, fn:length(List.path))} </td>
                                <td><fmt:formatDate value="${ List.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>${List.status}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="doCheck('${List.id}')">审核</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
            <div class="page">
                <div class="info">
                    <c:choose>
                        <c:when test="${recordCount != 0}">
                            共查询到<span>${recordCount}</span>条记录&nbsp;&nbsp;
                        </c:when>
                        <c:otherwise>
                            未查询到记录&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                    当前页<span>${currentPage}</span>/<span>${pageCount}</span>
                </div>
                <div class="pages">
                    <c:choose>
                        <c:when test="${currentPage != 1}">
                            <a href="javascript:void(0)" onclick="gotoPage(1)">首页</a>&nbsp;
                            <a href="javascript:void(0)" onclick="gotoPage(${currentPage-1})">上页</a>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <span>首页&nbsp;</span>
                            <span>上页&nbsp;</span>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${currentPage != pageCount}">
                            <a href="javascript:void(0)" onclick="gotoPage(${currentPage+1})">下页&nbsp;</a>
                            <a href="javascript:void(0)" onclick="gotoPage(${pageCount})">末页</a>
                        </c:when>
                        <c:otherwise>
                            <span>下页&nbsp;</span>
                            <span>末页</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript">
    function gotoPage(pageNum) {
        var myForm = document.createElement("form");
        myForm.method = "get";
        myForm.action = "Upload/UncheckFileList";
        var myInput = document.createElement("input");
        myInput.setAttribute("name", "currentPage");
        myInput.setAttribute("value", pageNum);
        myForm.appendChild(myInput);
        document.body.appendChild(myForm);
        myForm.submit();
    }
</script>
<script type="text/javascript">
    $("#_pn").val("${page.currentPage}");
</script>
<script type="text/javascript">
    function doCheck(data) {
        var myForm = document.createElement("form");
        myForm.method = "post";
        myForm.action = "Upload/checkFileInfo";
        var myInput = document.createElement("input");
        myInput.setAttribute("name", "FileID");
        myInput.setAttribute("value", data);
        myForm.appendChild(myInput);
        document.body.appendChild(myForm);
        myForm.submit();
        document.body.removeChild(myForm);
    }
</script>
</body>
</html>
