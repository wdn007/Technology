<%@ page language="java" contentType="text/html; charset=UTF-8"
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

    <title>科技竞赛信息管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="./css/zebra_dialog.css" type="text/css">
    <link rel="stylesheet" href="./css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/cn.css"/>
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
                        <div class="totitle"><a href="${pageContext.request.contextPath}/View/prizeSearch.jsp">获奖查询</a>
                        </div>
                        <div class="toinfo">当前位置：<a href="${pageContext.request.contextPath}">网站首页</a> &gt;
                            <a href="${pageContext.request.contextPath}/View/prizeSearch.jsp">获奖查询</a></div>
                    </div>
                    <div class="blk4 bf"></div>
                    <div class="minfo">
                        <form action="Prize/prizeSearchInfo" method="post">
                            <h5 class="ioth">
                                <span style="margin-left: 0;">学号：</span><input style="width:130px;" type="text" name="userID"/>
                                <span style="margin-left: 0;">姓名：</span><input style="width:130px;" type="text" name="username"/>
                                <span style="margin-left: 0;">赛事名称：</span><input style="width:130px;" type="text" name="title"/>
                                <span style="margin-left: 0;">获奖类型：</span><input style="width:130px;" type="text" name="prizeType"/>
                                <span><input type="submit" value="查询" class="btn btn-primary"
                                             style="width:50px;margin-top: -5px;"/></span>
                            </h5>
                        </form>
                        <div class="tr">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr class="tableHead">
                                    <th>赛事名称</th>
                                    <th>获奖者学号</th>
                                    <th>获奖者姓名</th>
                                    <th>获奖类型</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${ prizeinfo }" var="List">
                                    <tr>
                                        <td>${ List.title }</td>
                                        <td>${ List.userID }</td>
                                        <td>${ List.username }</td>
                                        <td>${ List.prizeType }</td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>

                        </div>

                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <!--底部开始-->
            <div class="bottom">
                <p>Copyright &copy;2017 桂林电子科技大学 MakeBy wdn</p>
            </div>
            <!--底部结束-->
        </div>
    </div>
</div>
<script type="text/javascript" src="./js/jquery.min.js"></script>
</body>
</html>
