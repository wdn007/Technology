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
                        <div class="totitle"><a href="${pageContext.request.contextPath}/Race/showRaceList">赛事公告</a>
                        </div>
                        <div class="toinfo">当前位置：<a href="${pageContext.request.contextPath}">网站首页</a> &gt;
                            <a href="${pageContext.request.contextPath}/Race/showRaceList">赛事公告</a></div>
                    </div>
                    <div class="blk4 bf"></div>
                    <div class="minfo">
                        <h1 class="ititle">${ raceinfo.title } </h1>
                        <h5 class="ioth">
                            <span>发布日期：</span><fmt:formatDate value="${ raceinfo.uploadTime }"
                                                              pattern="yyyy-MM-dd HH:mm:ss"/>
                            <span>发布者：</span>${ raceinfo.uploadUsername }
                            <span>访问量：</span>${ raceinfo.visitCount }
                        </h5>
                        <div class="tr"><h3 class="ht">赛事名称：</h3><span class="info">${ raceinfo.title } </span></div>
                        <div class="tr"><h3 class="ht">主办单位：</h3><span class="info">${ raceinfo.sponsor }</span></div>
                        <div class="tr"><h3 class="ht">赛事等级：</h3><span class="info">${ raceinfo.grade }</span></div>
                        <div class="tr"><h3 class="ht">比赛地点：</h3><span class="info">${ raceinfo.location }</span></div>
                        <div class="tr"><h3 class="ht"><label for="MatchDates">比赛日期</label>：</h3><span
                                class="info">${ raceinfo.raceDate }</span></div>
                        <div class="tr"><h3 class="ht">报名日期：</h3><span class="info">${ raceinfo.entryDate } </span>
                        </div>
                        <div class="tr"><h3 class="ht">赛事内容：</h3></div>
                        <div class="tr content">${ raceinfo.information }</div>
                        <div class="tr"><h3 class="ht"><label for="MatchForm">参赛形式</label>：</h3><span
                                class="info">${ raceinfo.entryForm }</span></div>
                        <c:if test="${ raceinfo.accessory != null && raceinfo.accessory ne '' }">
                            <div class="tr"><h3 class="ht"><label for="Attachment">附件下载</label>：</h3></div>
                            <div class="tr content">
                                <a class="ke-insertfile" href="javascript:void(0);"
                                   onclick="doDownload('${ raceinfo.accessory }')">
                                        ${fn:substring(raceinfo.accessory, fn:indexOf(raceinfo.accessory, '_') + 1, fn:length(raceinfo.accessory))}
                                </a>
                            </div>
                        </c:if>
                        <div class="tr tbtn">
                            <c:if test="${ raceinfo.status eq '正在报名' }">
                                <c:url value="EntryForm/raceDetail" var="link">
                                    <c:param name="raceID" value="${ raceinfo.raceID }"></c:param>
                                </c:url>
                                <div class="tr tbtn"><a href="${ link }" rel="external nofollow" target="_blank"
                                                        class="btn">报名参赛</a></div>
                            </c:if>
                            <c:if test="${ raceinfo.status ne '正在报名' }">
                                <div class="tr tbtn"><a class="btn btn-wait">${ raceinfo.status }</a></div>
                            </c:if>
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
<script type="text/javascript">
    function doDownload(data) {
        var myForm = document.createElement("form");
        myForm.method = "post";
        myForm.action = "Race/download";
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
