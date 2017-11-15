<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>报名统计</title>

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
            <div class="tr">
                <form action="EntryForm/listEntryFormInfo">
                    <h5 class="ioth">
                        <span style="margin-left: 0;">赛事名称：</span><input type="text" name="title"/>
                        <span style="margin-left: 0;">赛事等级：</span><input type="text" name="grade"/>
                        <span><input type="submit" value="查询" class="btn btn-primary" style="margin-top: -5px;"/></span>
                    </h5>
                </form>
                <div class="tr">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr class="tableHead">
                            <th>赛事编号</th>
                            <th>赛事名称</th>
                            <th>赛事等级</th>
                            <th>队长学号</th>
                            <th>队长姓名</th>
                            <th>队员学号</th>
                            <th>队员姓名</th>
                            <th>指导老师</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ EntryFormInfoList }" var="List">
                            <tr>
                                <td>${ List.raceID.raceID }</td>
                                <td>${ List.raceID.title }</td>
                                <td>${ List.raceID.grade }</td>
                                <td>${ List.leaderStuID }</td>
                                <td>${ List.leaderStuName }</td>
                                <td>${ List.stuID }</td>
                                <td>${ List.stuName }</td>
                                <td>${ List.diretorTecName }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
    </div>
</div>
</body>
</html>
