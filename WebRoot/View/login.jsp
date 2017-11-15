<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>科技竞赛信息管理系统</title>
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

            <div class="container" style="margin-bottom: 30px;">
                <div class="row ml">
                    <div class="alignCenter">
                        <form action="Student/login" id="form1" method="post" name="form1">
                            <dl class="login" id="loginArea">
                                <dt>帐号登录
                                    <a href="">忘记密码？</a>
                                </dt>
                                <dd class="ddbg">

                                    <div class="uid">
                                        <input type="text" name="ID" id="uguid" placeholder="学号/工号" value="">
                                        <label><input type="radio" name="identity" id="utype1" value="1"
                                                      checked="checked">学生</label>
                                    </div>
                                    <div class="pwd">
                                        <input type="password" name="password" id="ugpwd" placeholder="请输入密码">
                                        <label><input type="radio" name="identity" id="utype2" value="2">教师</label>
                                    </div>
                                    <label class="reuid" for="rem"><input type="checkbox" name="rem" id="rem" value="1">记住账号</label>
                                    <div class="err" id="loginErr">

                                    </div>
                                    <div class="btn">
                                        <button type="submit" class="loginBtn" id="loginBtn">登录</button>
                                        <a href="View/register.jsp"><img src="./images/btn_reg.jpg"></a>
                                    </div>
                                </dd>
                            </dl>
                        </form>
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
</body>
</html>
