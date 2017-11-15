<%@ page language="java" contentType="text/html; charset=UTF-8"
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

    <title>获奖信息发布</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="./css/uploadJsp.css">
    <link rel="stylesheet" href="./css/cn.css"/>

</head>

<body>
<div class="main">
    <div class="m col-md-10">
        <div class="blk4 bf"></div>
        <div class="minfo">
            <form action="Prize/addPrizeForm" method="post" id="myForm" style="margin-left: 70px;margin-top: 20px;">
                <table class="uploadTable" id="stuInfo">
                    <tr>
                        <td class="l_td"><span class="star">*</span><label>赛事编号：</label></td>
                        <td class="r_td"><input name="raceID" type="text" value="${ raceInfo.raceID }" readonly/></td>
                    </tr>
                    <tr>
                        <td class="l_td"><span class="star">*</span><label>赛事名称：</label></td>
                        <td class="r_td"><input name="title" type="text" value="${ raceInfo.title }" readonly/></td>
                    </tr>
                    <tr>
                        <td class="l_td"><span class="star">*</span><label>获奖类型：</label></td>
                        <td class="r_td">
                            <select id="sponsor" name="prizeType">
                                <option value="1">一等奖</option>
                                <option value="2">二等奖</option>
                                <option value="3">三等奖</option>
                                <option value="4">优胜奖</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_td"><label>获奖学生学号</label></td>
                        <td class="r_td"><label>获奖学生姓名</label></td>
                    </tr>
                    <tr>
                        <td class="l_td"><input name="userID" type="text"/></td>
                        <td class="r_td"><input name="username" type="text"/></td>
                    </tr>
                </table>
                <table class="uploadTable" style="margin-left: 240px;">
                    <tr>
                        <td class="r_td"><input class="addBtn" id="addStudent" type="button" onclick="addS()"/></td>
                    </tr>
                </table>
                <table class="uploadTable">
                    <tr>
                        <td class="l_td">&nbsp;</td>
                        <td class="submit_btn" style="padding-left: 226px;"><input type="submit" value="发布"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var i = 1;
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
</body>
</html>
