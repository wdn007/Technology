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

    <title>科技竞赛信息管理系统</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="./css/zebra_dialog.css" type="text/css">
    <link rel="stylesheet" href="./css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/cn.css"/>
    <link type="text/css" rel="stylesheet" href="./css/style.css">

</head>

<body>
<div class="top">
    <div class="center">
        <div class="left_top"><p>欢迎光临桂林电子科技大学科技竞赛管理信息系统</p></div>
        <div class="right_top">
            <ul class="navbar-nav navbar-right">
                <c:if test="${ username == null }">
                    <li><a href="View/login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                    <li><a href="View/register.jsp"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                </c:if>
                <c:if test="${ username != null }">
                    <li>欢迎您，<a href="#">${ username }</a>！</li>
                    <li><a href="Student/logout"><span class="glyphicon glyphicon-log-in"></span> 退出</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="index">
    <div class="center">
        <div class="login_t">
            <p class="lgtil">
                <img src="./images/title.png">
            </p>
        </div>
        <div class="login-m">
            <div class="indexNav navbar" style="border-radius: 0;">
                <ul class="navUl nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/Show/indexShow.do">网站首页</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/Race/showRaceList">赛事公告</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/News/showNewsList">新闻中心</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="academyStyle.html">学院风采</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/View/prizeSearch.jsp">获奖查询</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/Upload/showDownloadList">下载中心</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/View/about.jsp">关于我们</a>
                    </li>
                </ul>
            </div>
            <div class="main">
                <div class="m col-md-10">
                    <div class="tom">
                        <div class="totitle"><a
                                href="${pageContext.request.contextPath}/News/showNewsList">新闻中心<span></span></a></div>
                        <div class="toinfo">当前位置：<a href="${pageContext.request.contextPath}">网站首页</a> &gt;
                            <a href="${pageContext.request.contextPath}/News/showNewsList">新闻中心</a></div>
                    </div>
                    <div class="blk4 bf"></div>
                    <ul class="list">
                        <c:forEach items="${NewsInfoList}" var="myList">
                            <c:url value="News/newsDetail" var="link">
                                <c:param name="newsID" value="${ myList.newsID }"></c:param>
                            </c:url>
                            <li><span class="stitle"><a href="${ link }" rel="external nofollow" target="_blank"
                                                        title="${ myList.title }"
                                                        class="red0">${ myList.title }</a></span><span
                                    class="stype">${ myList.academy }</span><span class="sdate"><fmt:formatDate
                                    value="${ myList.newsTime }" pattern="yyyy-MM-dd"/></span></li>
                        </c:forEach>
                    </ul>
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
        <!--底部开始-->
        <div class="bottom">
            <p>Copyright &copy;2017 桂林电子科技大学 MakeBy wdn</p>
        </div>
        <!--底部结束-->
    </div>
</div>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript">
    function gotoPage(pageNum) {
        var myForm = document.createElement("form");
        myForm.method = "get";
        myForm.action = "News/showNewsList";
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

</body>
</html>
