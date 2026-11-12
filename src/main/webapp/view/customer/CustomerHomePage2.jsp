<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    // CustomerHomePage2.jsp (Hinh 19): -subReserveTable : link, -customer : Customer2
    Customer2 customer = (Customer2) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ khách hàng</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>TRANG CHỦ KHÁCH HÀNG</h2>
    <div class="content">
        <p>Xin chào <b><%= esc(customer.getFullName()) %></b></p>
        <button class="btn" name="subReserveTable"
            onclick="openPage('<%= ctx %>/view/customer/SearchTablePage2.jsp')">Đặt bàn</button>
    </div>
</div>
</body>
</html>
