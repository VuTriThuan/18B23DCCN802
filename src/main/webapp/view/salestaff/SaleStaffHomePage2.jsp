<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    SaleStaff2 saleStaff = (SaleStaff2) session.getAttribute("salestaff");
    if (saleStaff == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ nhân viên bán hàng</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>TRANG CHỦ NHÂN VIÊN BÁN HÀNG</h2>
    <div class="content">
        <p>Xin chào <b><%= esc(saleStaff.getFullName()) %></b>.</p>
        <p>Các chức năng của nhân viên bán hàng không thuộc hai module được cài đặt trong bài.</p>
    </div>
</div>
</body>
</html>
