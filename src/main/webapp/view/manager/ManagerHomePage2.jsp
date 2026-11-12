<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    // ManagerHomePage2.jsp (Hinh 15): -subManageDish : link
    Manager2 manager = (Manager2) session.getAttribute("manager");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ nhân viên quản lý</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>TRANG CHỦ NHÂN VIÊN QUẢN LÝ</h2>
    <div class="content">
        <p>Xin chào <b><%= esc(manager.getFullName()) %></b> (<%= esc(manager.getPosition()) %>)</p>
        <button class="btn" name="subManageDish"
            onclick="openPage('<%= ctx %>/view/manager/ManageDishPage2.jsp')">Quản lý thông tin món ăn</button>
    </div>
</div>
</body>
</html>
