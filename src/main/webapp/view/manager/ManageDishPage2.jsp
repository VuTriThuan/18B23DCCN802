<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    // ManageDishPage2.jsp (Hinh 15): -subAddDish, -subEditDish, -subDeleteDish, -subBack : link
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
<title>Quản lý thông tin món ăn</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>QUẢN LÝ THÔNG TIN MÓN ĂN</h2>
    <div class="content">
        <button class="btn" name="subAddDish"
            onclick="openPage('<%= ctx %>/view/manager/AddDishPage2.jsp')">Thêm món ăn</button>
        <button class="btn" name="subEditDish"
            onclick="alert('Chức năng sửa món ăn không thuộc module Add dish 2')">Sửa món ăn</button>
        <button class="btn" name="subDeleteDish"
            onclick="alert('Chức năng xoá món ăn không thuộc module Add dish 2')">Xoá món ăn</button>
        <button class="btn" name="subBack"
            onclick="openPage('<%= ctx %>/view/manager/ManagerHomePage2.jsp')">Quay lại</button>
    </div>
</div>
</body>
</html>
