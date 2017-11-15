<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Cache-Control" content="o-transform">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <link rel="stylesheet" href="./css/sccl.css">
    <link rel="stylesheet" type="text/css" href="./skin/molv/skin.css" id="layout-skin"/>
    <title>管理中心</title>
</head>

<body>
<div class="layout-admin">
    <header class="layout-header">
        <span class="header-logo">科技竞赛管理系统</span>
        <a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
        <ul class="header-bar">
            <li class="header-bar-nav">
                <a href="View/admin.jsp">${ username }<i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
                <ul class="header-dropdown-menu">
                    <li><a href="Student/logout">退出</a></li>
                </ul>
            </li>
            <li class="header-bar-nav">
                <a href="javascript:;" title="换肤"><i class="icon-font">&#xe608;</i></a>
                <ul class="header-dropdown-menu right dropdown-skin">
                    <li><a href="javascript:;" data-val="qingxin" title="清新">清新</a></li>
                    <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                    <li><a href="javascript:;" data-val="molv" title="墨绿">墨绿</a></li>

                </ul>
            </li>
        </ul>
    </header>
    <aside class="layout-side">
        <ul class="side-menu">

        </ul>
    </aside>

    <div class="layout-side-arrow">
        <div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div>
    </div>

    <section class="layout-main">
        <div class="layout-main-tab">
            <button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
            <nav class="tab-nav">
                <div class="tab-nav-content">
                    <a href="${pageContext.request.contextPath}/Show/indexShow.do" class="content-tab active"
                       data-id="Show/indexShow.do">首页</a>
                </div>
            </nav>
            <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
        </div>
        <div class="layout-main-body">
            <iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="Show/indexShow.do" frameborder="0"
                    data-id="Show/indexShow.do" seamless></iframe>
        </div>
    </section>
    <div class="layout-footer">Copyright &copy;2017 MakeBy wdn</div>
</div>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/sccl.js"></script>
<script type="text/javascript" src="./js/sccl-util.js"></script>
<script type="text/javascript" src="./js/custom.js"></script>
</body>
</html>
