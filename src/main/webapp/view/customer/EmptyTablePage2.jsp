<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.*,java.text.SimpleDateFormat,model.*"%>
<%
    // EmptyTablePage2.jsp (Hinh 19): -inoutTableList : Table (co o tich chon),
    // -listTable : Table2[], -subNext : submit, -subBack : link
    Customer2 customer = (Customer2) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
    @SuppressWarnings("unchecked")
    ArrayList<Table2> listTable = (ArrayList<Table2>) session.getAttribute("listTable");
    Date bookingTime = (Date) session.getAttribute("bookingTime");
    Integer guestCount = (Integer) session.getAttribute("guestCount");
    if (listTable == null || bookingTime == null || guestCount == null) {
        response.sendRedirect(request.getContextPath() + "/view/customer/SearchTablePage2.jsp");
        return;
    }
    String outMessage = (String) request.getAttribute("message");
    Set<String> checked = new HashSet<String>();

    // khach click "Tiep tuc": trang tu kiem tra tong suc chua (ngoai le 5.1)
    if (request.getParameter("subNext") != null) {
        String[] ids = request.getParameterValues("tableId");
        ArrayList<Table2> selectedTables = new ArrayList<Table2>();
        int totalCapacity = 0;
        if (ids != null) {
            for (String id : ids) {
                for (Table2 t : listTable) {
                    if (String.valueOf(t.getId()).equals(id)) {
                        selectedTables.add(t);
                        totalCapacity += t.getCapacity();
                        checked.add(id);
                    }
                }
            }
        }
        if (selectedTables.isEmpty() || totalCapacity < guestCount) {
            outMessage = "Vui lòng chọn đủ bàn cho " + guestCount + " người";
        } else {
            // goi trang ReservationInfoPage2.jsp, truyen cac ban da chon
            session.setAttribute("selectedTables", selectedTables);
            response.sendRedirect(request.getContextPath() + "/view/customer/ReservationInfoPage2.jsp");
            return;
        }
    }
    SimpleDateFormat f = new SimpleDateFormat("HH:mm 'ngày' dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bàn trống</title>
</head>
<body>
<%@include file="/header.jsp" %>
<div class="box">
    <h2>DANH SÁCH BÀN TRỐNG</h2>
    <div class="content">
        <p>Thời gian: <b><%= f.format(bookingTime) %></b> &nbsp;&nbsp; Số người: <b><%= guestCount %></b></p>
        <form name="emptyTable" action="<%= ctx %>/view/customer/EmptyTablePage2.jsp" method="post">
            <table class="list">
                <tr><th>TT</th><th>Tên bàn</th><th>Sức chứa</th><th>Mô tả</th><th>Chọn</th></tr>
<%  for (int i = 0; i < listTable.size(); i++) {
        Table2 t = listTable.get(i);
        String id = String.valueOf(t.getId()); %>
                <tr>
                    <td><%= i + 1 %></td>
                    <td><%= esc(t.getTableName()) %></td>
                    <td><%= t.getCapacity() %></td>
                    <td><%= esc(t.getDescription()) %></td>
                    <td><input type="checkbox" name="tableId" value="<%= id %>"<%= checked.contains(id) ? " checked" : "" %>></td>
                </tr>
<%  } %>
            </table>
            <p class="msg"><%= esc(outMessage) %></p>
            <input type="submit" class="btn" name="subNext" value="Tiếp tục">
            <button type="button" class="btn" name="subBack"
                onclick="openPage('<%= ctx %>/view/customer/SearchTablePage2.jsp')">Quay lại</button>
        </form>
    </div>
</div>
</body>
</html>
