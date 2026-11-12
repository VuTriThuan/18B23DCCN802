<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    // SearchTablePage2.jsp (Hinh 19): -inDate : date, -inTime : select, -inGuestCount : text,
    // -subSearch : submit, -subBack : link, -outMessage : text
    Customer2 customer = (Customer2) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
    String outMessage = (String) request.getAttribute("message");
    String inDate = request.getParameter("inDate");
    String inTime = request.getParameter("inTime");
    String inGuestCount = request.getParameter("inGuestCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tìm bàn trống</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>TÌM BÀN TRỐNG</h2>
    <div class="content">
        <form name="searchTable" action="<%= ctx %>/TableServlet2" method="get">
            <table class="form">
                <tr><td>Ngày đến:</td>
                    <td><input type="date" name="inDate" value="<%= esc(inDate) %>"></td></tr>
                <tr><td>Giờ đến:</td>
                    <td><select name="inTime">
<%  for (int h = 10; h <= 21; h++) {
        for (int m = 0; m < 60; m += 30) {
            String t = String.format("%02d:%02d", h, m); %>
                        <option<%= t.equals(inTime) ? " selected" : "" %>><%= t %></option>
<%      }
    } %>
                    </select></td></tr>
                <tr><td>Số người:</td>
                    <td><input type="text" name="inGuestCount" value="<%= esc(inGuestCount) %>"></td></tr>
            </table>
            <p class="msg"><%= esc(outMessage) %></p>
            <input type="submit" class="btn" name="subSearch" value="Tìm kiếm">
            <button type="button" class="btn" name="subBack"
                onclick="openPage('<%= ctx %>/view/customer/CustomerHomePage2.jsp')">Quay lại</button>
        </form>
    </div>
</div>
</body>
</html>
