<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.*,java.text.SimpleDateFormat,model.*"%>
<%
    // ReservationInfoPage2.jsp (Hinh 19): -outBookingTime, -outGuestCount : text; -outTableList : Table;
    // -selectedTables : Table2[]; -customer : Customer2; -inoutFullName, -inoutPhone, -inoutEmail : text;
    // -inNote : textarea; -subConfirm : submit; -subCancel : link; -outMessage : text
    Customer2 customer = (Customer2) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
    Boolean success = (Boolean) request.getAttribute("success");
    String outMessage = (String) request.getAttribute("message");
    Date bookingTime = (Date) session.getAttribute("bookingTime");
    Integer guestCount = (Integer) session.getAttribute("guestCount");
    @SuppressWarnings("unchecked")
    ArrayList<Table2> selectedTables = (ArrayList<Table2>) session.getAttribute("selectedTables");
    if ((success == null || !success) && (bookingTime == null || guestCount == null || selectedTables == null)) {
        response.sendRedirect(request.getContextPath() + "/view/customer/SearchTablePage2.jsp");
        return;
    }
    // thong tin ca nhan: dien san tu tai khoan, hoac giu lai gia tri khach vua nhap
    String fullName = request.getParameter("inoutFullName") != null
            ? request.getParameter("inoutFullName") : customer.getFullName();
    String phone = request.getParameter("inoutPhone") != null
            ? request.getParameter("inoutPhone") : customer.getPhone();
    String email = request.getParameter("inoutEmail") != null
            ? request.getParameter("inoutEmail") : customer.getEmail();
    String note = request.getParameter("inNote");
    SimpleDateFormat f = new SimpleDateFormat("HH:mm 'ngày' dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin đặt bàn</title>
</head>
<body>
<%@include file="/header.jsp" %>
<% if (success != null && success) { %>
<script>
    alert('<%= js(outMessage) %>');
    openPage('<%= ctx %>/view/customer/CustomerHomePage2.jsp');
</script>
<% return; } %>
<div class="box">
    <h2>THÔNG TIN ĐẶT BÀN</h2>
    <div class="content">
        <form name="reservationInfo" action="<%= ctx %>/ReservationServlet2" method="post">
            <table class="form">
                <tr><td>Thời gian:</td><td><b><%= f.format(bookingTime) %></b></td></tr>
                <tr><td>Số người:</td><td><b><%= guestCount %></b></td></tr>
                <tr><td>Bàn đã chọn:</td><td>
                    <table class="list">
                        <tr><th>TT</th><th>Tên bàn</th><th>Sức chứa</th></tr>
<%  for (int i = 0; i < selectedTables.size(); i++) { Table2 t = selectedTables.get(i); %>
                        <tr><td><%= i + 1 %></td><td><%= esc(t.getTableName()) %></td><td><%= t.getCapacity() %></td></tr>
<%  } %>
                    </table>
                </td></tr>
                <tr><td>Họ tên:</td>
                    <td><input type="text" name="inoutFullName" value="<%= esc(fullName) %>">
                        <div class="err"><%= esc((String) request.getAttribute("errFullName")) %></div></td></tr>
                <tr><td>Số điện thoại:</td>
                    <td><input type="text" name="inoutPhone" value="<%= esc(phone) %>">
                        <div class="err"><%= esc((String) request.getAttribute("errPhone")) %></div></td></tr>
                <tr><td>Email:</td>
                    <td><input type="text" name="inoutEmail" value="<%= esc(email) %>">
                        <div class="err"><%= esc((String) request.getAttribute("errEmail")) %></div></td></tr>
                <tr><td>Ghi chú:</td>
                    <td><textarea name="inNote" rows="3"><%= esc(note) %></textarea></td></tr>
            </table>
            <p class="msg"><%= esc(outMessage) %></p>
            <input type="submit" class="btn" name="subConfirm" value="Xác nhận">
            <button type="button" class="btn" name="subCancel"
                onclick="openPage('<%= ctx %>/view/customer/SearchTablePage2.jsp')">Huỷ</button>
        </form>
    </div>
</div>
</body>
</html>
