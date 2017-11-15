<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>jQuery EasyUI CRUD Demo</title>
    <link rel="stylesheet" href="./css/cn.css"/>
    <style type="text/css">
        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }
    </style>
</head>
<body>
<div class="form-list form-main-list">
    <form action="Teacher/update" id="form1" method="post" name="form1">
        <div class="form-group">
            <div class="form-item">
                <span class="form-label"><label for="MemberName">姓名</label></span>
                <input class="form-text" name="username" type="text" value="${ teacherSingle.username }"/>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="MemberNo">工号</label>
                </div>
                <input class="form-text" name="jobID" type="text" value="${ teacherSingle.jobID }"/>
            </div>
            <div class="form-item">
                <span class="form-label"><label for="Email">邮箱</label></span>
                <input class="form-text" id="Email" name="email" type="text" value="${ teacherSingle.email }"/>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="MemberPwd">密码</label>
                </div>
                <input class="form-text" id="MemberPwd" name="password" type="password"
                       value="${ teacherSingle.password }"/>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="MainId">学院</label>
                </div>
                <select class="" data-val="true" id="MainId" name="academy"
                        value="${ teacherSingle.academy }">
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
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="Specialty">职称</label>
                </div>
                <input class="form-text" id="Specialty" name="duties" type="text" value="${ teacherSingle.duties }"/>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="MainId">性别</label>
                </div>
                <select class="" data-val="true" id="Sex" name="sex" value="${ teacherSingle.sex }">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="Mobile">手机</label>
                </div>
                <input class="form-text" id="Mobile" name="telNo" type="text" value="${ teacherSingle.telNo }"/>
            </div>
            <div class="form-item">
                <div class="form-label">
                    <label for="Mobile">个人简介：</label>
                </div>
                <textarea cols="70" rows="10" name="remark" placeholder="请填写个人简介">${ teacherSingle.remark }</textarea>
            </div>
            <div class="form-item">
                <button type="submit" class="pass-button-submit" loading-msg="正在提交">修改</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>