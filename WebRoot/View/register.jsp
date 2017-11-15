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
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquerycssmenu.js"></script>
    <script type="text/javascript" src="./js/VLoadedImg/js/jquery.VLoadedImg.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/ckplayer.js"></script>
    <script src="./js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="./js/jquery.validate.unobtrusive.min.js" type="text/javascript"></script>

</head>

<body>
<div class="top">
    <div class="center">
        <div class="left_top">
            <p>欢迎光临桂林电子科技大学科技竞赛管理信息系统</p>
        </div>
        <div class="right_top">
            <ul class="navbar-nav navbar-right">
                <li><a href="View/login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                <li><a href="View/register.jsp"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
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
                <dl class="cm">
                    <dt>桂林电子科技大学科技竞赛官网-(学生账号)注册</dt>
                    <dd>
                        <div class="alert alert-danger">
                            <p><b>说明：</b>只允许（桂林电子科技大学）学生用户注册，请认真填写正确信息，填写虚假信息无效！<a
                                    href="${pageContext.request.contextPath}/View/registerT.jsp">教师点此注册</a></p>
                        </div>
                        <div class="form-list form-main-list">

                            <form action="Student/register" id="form1" method="post" name="form1">

                                <div class="form-group">
                                    <div class="form-item">
                                        <span class="form-label"><span class="star">*</span><label
                                                for="MemberName">姓名</label></span>
                                        <input class="form-text" data-val="true" data-val-required="请输入姓名！"
                                               id="MemberName" name="stuName" placeholder="姓名" type="text" value=""/>
                                        <span class="field-validation-valid" data-valmsg-for="MemberName"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="MemberNo">学号</label>
                                        </div>
                                        <input class="form-text" data-val="true" data-val-length="学号6-20位"
                                               data-val-length-max="20" data-val-length-min="6"
                                               data-val-remote="该学号已经注册过账号！"
                                               data-val-remote-additionalfields="*.MemberNo" data-val-remote-type="post"
                                               data-val-remote-url="/Register/MemberNo" data-val-required="请输入正确的学号！"
                                               id="MemberNo" name="studentID" placeholder="学生卡学号，可用作登陆账号" type="text"
                                               value=""/>
                                        <span class="field-validation-valid" data-valmsg-for="MemberNo"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <span class="form-label"><span class="star">*</span><label
                                                for="Email">邮箱</label></span>
                                        <input class="form-text" data-val="true" data-val-regex="邮箱格式不正确！请重新输入 "
                                               data-val-regex-pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}"
                                               data-val-remote="该邮箱已经注册过账号！" data-val-remote-additionalfields="*.Email"
                                               data-val-remote-type="post" data-val-remote-url="/Register/Email"
                                               data-val-required="请输入有效的邮箱！" id="Email" name="email"
                                               placeholder="一个邮箱只能绑定一个学号" type="text" value=""/>
                                        <span class="field-validation-valid" data-valmsg-for="Email"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="MemberPwd">密码</label>
                                        </div>
                                        <input class="form-text" data-val="true" data-val-length="密码6-20位，由数字和字母组成"
                                               data-val-length-max="20" data-val-length-min="6"
                                               data-val-required="请输入密码！" id="MemberPwd" name="password"
                                               placeholder="密码6-20位，由数字和字母组成" type="password"/>
                                        <span class="field-validation-valid" data-valmsg-for="MemberPwd"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="ConfirmPwd">确认密码</label>
                                        </div>
                                        <input class="form-text" data-val="true" data-val-equalto="密码和确认密码不匹配！"
                                               data-val-equalto-other="*.MemberPwd" id="ConfirmPwd" name="ConfirmPwd"
                                               placeholder="请输入确认密码" type="password"/>
                                        <span class="field-validation-valid" data-valmsg-for="ConfirmPwd"
                                              data-valmsg-replace="true"></span>
                                    </div>

                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="MainId">学院</label>
                                        </div>
                                        <select class="" data-val="true" data-val-number="字段 学院 必须是一个数字。"
                                                data-val-range="请选择学院！" data-val-range-max="9999" data-val-range-min="1"
                                                data-val-required="请选择学院！" id="MainId" name="academy"
                                                placeholder="请选择学院">
                                            <option value="机电工程学院">机电工程学院</option>
                                            <option value="信息与通信学院">信息与通信学院</option>
                                            <option value="计算机与信息安全学院">计算机与信息安全学院</option>
                                            <option value="艺术与设计学院">艺术与设计学院</option>
                                            <option value="商学院">商学院</option>
                                            <option value="外国语学院">外国语学院</option>
                                            <option value="数学与计算科学学院">数学与计算科学学院</option>
                                            <option value="电子工程与自动化学院">电子工程与自动化学院</option>
                                            <option value="法学院">法学院</option>
                                            <option value="材料科学与工程学院">材料科学与工程学院</option>
                                            <option value="生命与环境科学学院">生命与环境科学学院</option>
                                            <option value="建筑与交通工程学院">建筑与交通工程学院</option>
                                            <option value="海洋信息工程学院">海洋信息工程学院</option>
                                            <option value="体育部">体育部</option>
                                            <option value="国际学院">国际学院</option>
                                        </select>
                                        <span class="field-validation-valid" data-valmsg-for="MainId"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="Specialty">专业</label>
                                        </div>
                                        <input class="form-text" data-val="true" data-val-length="专业4-30位"
                                               data-val-length-max="30" data-val-length-min="4"
                                               data-val-required="请输入专业！" id="Specialty" name="major"
                                               placeholder="请输入专业" type="text" value=""/>
                                        <span class="field-validation-valid" data-valmsg-for="Specialty"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="MainId">性别</label>
                                        </div>
                                        <select class="" data-val="true" data-val-number="" data-val-range="请选择性别！"
                                                data-val-range-max="9999" data-val-range-min="1"
                                                data-val-required="请选择性别！" id="Sex" name="sex" placeholder="请选择性别">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                        <span class="field-validation-valid" data-valmsg-for="Sex"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <div class="form-label">
                                            <span class="star">*</span><label for="Mobile">手机</label>
                                        </div>
                                        <input class="form-text" data-val="true" data-val-regex="手机号格式不正确！请重新输入 "
                                               data-val-regex-pattern="1[3,4,5,7,8]\d{9}" data-val-required="请输入手机！"
                                               id="Mobile" name="telNo" placeholder="请输入手机号码" type="text" value=""/>
                                        <span class="field-validation-valid" data-valmsg-for="Mobile"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="form-item">
                                        <button type="submit" class="pass-button-submit" loading-msg="正在提交">提交注册
                                        </button>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </dd>
                </dl>
                <script>
                    $(document).ready(function () {
                        $("#form1").validate({
                            submitHandler: function (form) {
                                $(form).find(":submit").attr("disabled", true).attr("value",
                                    "正在提交...");
                                form1.submit();
                            }
                        })
                    });
                    //       $('form').submit(function () {
                    //           $('.pass-button-submit').attr('disabled', true);
                    //       });
                </script>

            </div>
        </div>
        <!--底部开始-->
        <div class="bottom">
            <p>Copyright &copy;2017 桂林电子科技大学 MakeBy wdn</p>
        </div>
        <!--底部结束-->
    </div>
</body>
</html>
