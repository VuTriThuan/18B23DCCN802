<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    WarehouseStaff2 warehouseStaff = (WarehouseStaff2) session.getAttribute("warehousestaff");
    if (warehouseStaff == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ nhân viên kho</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>TRANG CHỦ NHÂN VIÊN KHO</h2>
    <div class="content">
        <p>Xin chào <b><%= esc(warehouseStaff.getFullName()) %></b>.</p>
        <p>Các chức năng của nhân viên kho không thuộc hai module được cài đặt trong bài.</p>
    </div>
</div>
</body>
</html>
