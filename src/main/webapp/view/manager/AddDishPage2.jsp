<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="model.*"%>
<%
    // AddDishPage2.jsp (Hinh 15): -inName, -inUnitPrice, -inUnit : text; -inCategory : select;
    // -inDescription : textarea; -inImage : file; -inStatus : select; -subAdd : submit;
    // -subBack : link; -outMessage : text (thong bao ket qua do DishServlet2 tra ve)
    Manager2 manager = (Manager2) session.getAttribute("manager");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
        return;
    }
    Boolean success = (Boolean) request.getAttribute("success");
    String outMessage = (String) request.getAttribute("message");
    Dish2 dish = (Dish2) request.getAttribute("dish");
    if (dish == null) dish = new Dish2();
    String unitPriceText = (String) request.getAttribute("unitPriceText");
    String[] categories = {"Khai vị", "Món chính", "Tráng miệng", "Đồ uống"};
    String[] statuses = {"Đang phục vụ", "Tạm ngừng"};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm món ăn</title>
</head>
<body>
<%@include file="/header.jsp" %>
<% if (success != null && success) { %>
<script>
    alert('<%= js(outMessage) %>');
    openPage('<%= ctx %>/view/manager/ManageDishPage2.jsp');
</script>
<% return; } %>
<div class="box">
    <h2>THÊM MÓN ĂN</h2>
    <div class="content">
        <form name="addDish" action="<%= ctx %>/DishServlet2" method="post" enctype="multipart/form-data">
            <table class="form">
                <tr><td>Tên món:</td>
                    <td><input type="text" name="inName" value="<%= esc(dish.getName()) %>"></td></tr>
                <tr><td>Đơn giá:</td>
                    <td><input type="text" name="inUnitPrice" value="<%= esc(unitPriceText) %>"></td></tr>
                <tr><td>Đơn vị tính:</td>
                    <td><input type="text" name="inUnit" value="<%= esc(dish.getUnit()) %>"></td></tr>
                <tr><td>Danh mục:</td>
                    <td><select name="inCategory">
<%  for (String c : categories) { %>
                        <option<%= c.equals(dish.getCategory()) ? " selected" : "" %>><%= c %></option>
<%  } %>
                    </select></td></tr>
                <tr><td>Mô tả:</td>
                    <td><textarea name="inDescription" rows="3"><%= esc(dish.getDescription()) %></textarea></td></tr>
                <tr><td>Ảnh:</td>
                    <td><input type="file" name="inImage" accept="image/*"></td></tr>
                <tr><td>Trạng thái:</td>
                    <td><select name="inStatus">
<%  for (String s : statuses) { %>
                        <option<%= s.equals(dish.getStatus()) ? " selected" : "" %>><%= s %></option>
<%  } %>
                    </select></td></tr>
            </table>
            <p class="msg"><%= esc(outMessage) %></p>
            <input type="submit" class="btn" name="subAdd" value="Thêm">
            <button type="button" class="btn" name="subBack"
                onclick="openPage('<%= ctx %>/view/manager/ManageDishPage2.jsp')">Quay lại</button>
        </form>
    </div>
</div>
</body>
</html>
