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
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="./css/zebra_dialog.css" type="text/css">
    <link rel="stylesheet" href="./css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" type="text/css" href="./css/uploadJsp.css">
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
                        <a href="rewardSearch.html">获奖查询</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/Upload/showDownloadList">下载中心</a>
                    </li>
                    <li><span class="ver-line"> | </span></li>
                    <li class="">
                        <a href="about.html">关于我们</a>
                    </li>
                </ul>
            </div>

            <div class="main">
                <div class="m col-md-10">

                    <div class="tom">
                        <div class="totitle"><a
                                href="${pageContext.request.contextPath}/Race/showRaceList">报名中心<span></span></a></div>
                        <div class="toinfo">当前位置：<a href="${pageContext.request.contextPath}">网站首页</a> &gt;
                            <a href="${pageContext.request.contextPath}/Race/showRaceList">报名中心</a></div>
                    </div>
                    <div class="blk4 bf"></div>
                    <div class="minfo">
                        <form action="EntryForm/addEntryForm" method="post" id="myForm"
                              style="margin-left: 225px;margin-top: 20px;">
                            <table class="uploadTable" id="stuInfo">
                                <tr>
                                    <td class="l_td"><span class="star">*</span><label>赛事编号：</label></td>
                                    <td class="r_td"><input name="raceID" type="text" value="${ raceinfo.raceID }"
                                                            readonly/></td>
                                </tr>
                                <tr>
                                    <td class="l_td"><span class="star">*</span><label>赛事名称：</label></td>
                                    <td class="r_td"><input name="title" type="text" value="${ raceinfo.title }"
                                                            readonly/></td>
                                </tr>
                                <tr>
                                    <td class="l_td"><span class="star">*</span><label>参赛学生队长学号：</label></td>
                                    <td class="r_td"><input name="leaderStuID" type="text"/></td>
                                </tr>
                                <tr>
                                    <td class="l_td"><span class="star">*</span><label>参赛学生队长姓名：</label></td>
                                    <td class="r_td"><input name="leaderStuName" type="text"/></td>
                                </tr>
                                <tr>
                                    <td class="l_td"><label>参赛学生队员学号</label></td>
                                    <td class="r_td"><label>参赛学生队员姓名</label></td>
                                </tr>
                                <tr>
                                    <td class="l_td"><input name="stuID" type="text"/></td>
                                    <td class="r_td"><input name="stuName" type="text"/></td>
                                </tr>
                            </table>
                            <table class="uploadTable" style="margin-left: 240px;">
                                <tr>
                                    <td class="r_td"><input class="addBtn" id="addStudent" type="button"
                                                            onclick="addS()"/></td>
                                </tr>
                            </table>
                            <table class="uploadTable" id="teacherInfo" style="margin-left: 81px;">
                                <tr>
                                    <td class="l_td"><span class="star">*</span><label
                                            style="width:130px">参赛指导老师姓名：</label></td>
                                    <td class="r_td"><input name="diretorTecName" type="text"/></td>
                                </tr>
                            </table>
                            <table class="uploadTable" style="margin-left: 240px;">
                                <tr>
                                    <td class="r_td"><input class="addBtn" id="addTeacher" type="button"
                                                            onclick="addT()"/></td>
                                </tr>
                            </table>
                            <table class="uploadTable">
                                <tr>
                                    <td class="l_td">&nbsp;</td>
                                    <td class="submit_btn" style="padding-left: 226px;"><input type="submit"
                                                                                               value="报名"/></td>
                                </tr>
                            </table>
                        </form>
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
<script type="text/javascript">
    var i = 1;
    var j = 1;
    function addS() {
        var tb = document.createElement("tbody");
        tb.setAttribute("id", "tb_S_" + i);
        var tr = document.createElement("tr");
        var tdL = document.createElement("td");
        tdL.setAttribute("class", "l_td");
        var inputID = document.createElement("input");
        inputID.setAttribute("name", "stuID");
        inputID.setAttribute("type", "text");
        tdL.appendChild(inputID);
        var tdR = document.createElement("td");
        tdR.setAttribute("class", "r_td");
        var inputName = document.createElement("input");
        inputName.setAttribute("name", "stuName");
        inputName.setAttribute("type", "text");
        tdR.appendChild(inputName);
        var tdB = document.createElement("td");
        tdB.setAttribute("class", "r_td");
        tdB.setAttribute("style", "padding-top: 20px;");
        var inputB = document.createElement("input");
        inputB.setAttribute("class", "deleteBtn");
        inputB.setAttribute("type", "button");
        inputB.setAttribute("onclick", "delS(" + i + ")");
        tdB.appendChild(inputB);
        tr.appendChild(tdL);
        tr.appendChild(tdR);
        tr.appendChild(tdB);
        tb.appendChild(tr);
        document.getElementById("stuInfo").appendChild(tb);
        i += 1;
    }
    function delS(o) {
        document.getElementById("stuInfo").removeChild(document.getElementById("tb_S_" + o));
    }
</script>
<script type="text/javascript">
    function addT() {
        var tb = document.createElement("tbody");
        tb.setAttribute("id", "tb_T_" + j);
        var tr = document.createElement("tr");
        var tdL = document.createElement("td");
        tdL.setAttribute("class", "l_td");
        var tdR = document.createElement("td");
        tdR.setAttribute("class", "r_td");
        var inputDTN = document.createElement("input");
        inputDTN.setAttribute("name", "diretorTecName");
        inputDTN.setAttribute("type", "text");
        tdR.appendChild(inputDTN);
        var tdB = document.createElement("td");
        tdB.setAttribute("class", "r_td");
        tdB.setAttribute("style", "padding-top: 20px;");
        var inputB = document.createElement("input");
        inputB.setAttribute("class", "deleteBtn");
        inputB.setAttribute("type", "button");
        inputB.setAttribute("onclick", "delT(" + j + ")");
        tdB.appendChild(inputB);
        tr.appendChild(tdL);
        tr.appendChild(tdR);
        tr.appendChild(tdB);
        tb.appendChild(tr);
        document.getElementById("teacherInfo").appendChild(tb);
        j += 1;
    };
    function delT(o) {
        document.getElementById("teacherInfo").removeChild(document.getElementById("tb_T_" + o));
    }
</script>
</body>
</html>
