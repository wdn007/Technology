<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="background:none;">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>科技竞赛信息管理系统</title>
    <link rel="stylesheet" href="./css/zebra_dialog.css" type="text/css">
    <link rel="stylesheet" href="./css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/cn.css"/>
    <link rel="stylesheet" href="./css/jquery.slideBox.css"/>
    <link type="text/css" rel="stylesheet" href="./css/style.css">
</head>

<body style="background:none;">
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

            <div class="container" style="margin-bottom: 30px;">
                <div class="row">
                    <div class="col-md-4">
                        <div class="til_"><span>新闻中心</span>
                            <a href="${pageContext.request.contextPath}/News/showNewsList">More</a></div>
                        <div class="contentBox newsBox">
                            <ul>
                                <c:forEach items="${ newsinfoList }" var="newsList">
                                    <c:url value="News/newsDetail" var="link">
                                        <c:param name="newsID" value="${ newsList.newsID }"></c:param>
                                    </c:url>
                                    <li class="new_info">
                                        <a href="${ link }" rel="external nofollow" title="${ newsList.title }"
                                           target="_blank"><i style="color:#000;margin-right:3px;"
                                                              class="glyphicon glyphicon-play info_icon"></i>${ newsList.title }
                                        </a>
                                        <span><fmt:formatDate value="${ newsList.newsTime }"
                                                              pattern="yyyy-MM-dd"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="demo3" class="slideBox">
                            <ul class="items">
                                <li><a href="#" title="我校2017年“互联网+”..."><img src="./images/1.jpg"></a></li>
                                <li><a href="#" title="大学生创新创业大赛..."><img src="./images/2.png"></a></li>
                                <li><a href="#" title="大学生创新创业大赛..."><img src="./images/3.jpg"></a></li>
                                <li><a href="#" title="大学生创新创业大赛..."><img src="./images/4.jpg"></a></li>
                                <li><a href="#" title="大学生创新创业大赛..."><img src="./images/5.jpg"></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="til_"><span>赛事公告</span>
                            <a href="${pageContext.request.contextPath}/Race/showRaceList">More</a></div>
                        <div class="contentBox raceBox">
                            <ul>
                                <c:forEach items="${ raceinfoList }" var="raceList">
                                    <c:url value="Race/raceDetail" var="link">
                                        <c:param name="raceID" value="${ raceList.raceID }"></c:param>
                                    </c:url>
                                    <li>
                                        <a href="${ link }" rel="external nofollow" title="${ raceList.title }"
                                           target="_blank"><i
                                                class="glyphicon glyphicon-play info_icon"></i>${ raceList.title }</a>
                                        <!--   <span class="academy_name">${ raceList.sponsor }</span>-->
                                        <span><fmt:formatDate value="${ raceList.uploadTime }"
                                                              pattern="yyyy-MM-dd"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="til_"><span>友情链接</span></div>
                        <div class="contentBox linkTo">
                            <ul>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a href="http://www.gliet.edu.cn/extGuetWeb" target="_blank">桂林电子科技大学主页</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a href="http://bkjw.guet.edu.cn/student/public/login.asp"
                                       target="_blank">桂电学生选课系统</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a href="http://xsgzc.guet.edu.cn/" target="_blank">桂林电子科技大学学生工作处</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a href="http://lib.guet.edu.cn/" target="_blank">桂林电子科技大学图书馆</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="til_"><span>学院风采</span><a>More</a></div>
                        <div class="contentBox academy_news">
                            <ul class="container">
                                <li class="row">
                                    <div class="col-md-2">
                                        <img src="./images/prise.jpg"/>
                                    </div>
                                    <div class="col-md-5">
                                        <a><h1>我校学子勇夺2016年全国高校商业精英挑战赛大陆区总决赛一等奖</h1></a>
                                        <a>
                                            <p>
                                                2016年11月19号全国高校商业精英挑战赛在陕西咸阳成功开赛。
                                                我校在全国162所院校中脱颖而出，由我校ERP沙盘协会选派的代表队在经济管理学院刘秀清、
                                                孙晓园两位老师的指导下挺进大陆区总决赛并获得一等奖。
                                            </p>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="til_"><span>学院赛事</span></div>
                        <div class="contentBox academy_competition">
                            <ul>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>机电工程学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>信息与通信学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>计算机与信息安全学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>艺术与设计学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>商学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>外国语学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>数学与计算科学学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>电子工程与自动化学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>法学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>材料科学与工程学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>生命与环境科学学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>材料科学与工程学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>建筑与交通工程学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>海洋信息工程学院</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>体育部</a>
                                </li>
                                <li><i class="glyphicon glyphicon-send info_icon"></i>
                                    <a>国际学院</a>
                                </li>
                            </ul>
                        </div>
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

<script type="text/javascript" src="./js/jquery.min.js"></script>
<!--<script type="text/javascript" src="./js/zebra_dialog.js"></script>
  <script type="text/javascript" src="./js/bootstrap.js" ></script>
<script type="text/javascript" src="./js/bootstrap.min.js" ></script>
<script type="text/javascript" src="./js/npm.js" ></script>-->
<script type="text/javascript" src="./js/jquery.slideBox.min.js"></script>
<script>
    jQuery(function ($) {
        $('#demo3').slideBox({
            duration: 0.3,//滚动持续时间，单位：秒
            easing: 'linear',//swing,linear//滚动特效
            delay: 5,//滚动延迟时间，单位：秒
            hideClickBar: false,//不自动隐藏点选按键
            clickBarRadius: 10
        });
    });
</script>

</body>
</html>
