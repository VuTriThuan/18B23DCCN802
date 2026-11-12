<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>ĐĂNG NHẬP</h2>
    <div class="content">
<%
    String err = request.getParameter("err");
    if ("timeout".equals(err)) {
%>      <p class="msg">Hết phiên làm việc. Làm ơn đăng nhập lại!</p>
<%  } else if ("fail".equals(err)) { %>
        <p class="msg">Sai tên đăng nhập/mật khẩu!</p>
<%  } %>
        <form name="login" action="<%= ctx %>/LoginServlet2" method="post">
            <table class="form">
                <tr><td>Tên đăng nhập:</td><td><input type="text" name="username" required></td></tr>
                <tr><td>Mật khẩu:</td><td><input type="password" name="password" required></td></tr>
            </table>
            <input type="submit" class="btn" value="Đăng nhập">
        </form>
    </div>
</div>
</body>
</html>
