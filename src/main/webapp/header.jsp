<%@ page pageEncoding="UTF-8" import="model.User2"%>
<%!
    /** Chuyen ky tu dac biet truoc khi in gia tri nguoi dung nhap ra trang. */
    String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
    /** Chuyen chuoi de dat trong alert() cua JavaScript. */
    String js(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("'", "\\'");
    }
%>
<%
    String ctx = request.getContextPath();
    User2 hdrUser = (User2) session.getAttribute("user");
%>
<style>
    body { font-family: Arial, Helvetica, sans-serif; background: #f2f2f2; margin: 0; }
    .topbar { background: #333; color: #fff; padding: 8px 16px; font-size: 14px; }
    .topbar a { color: #ffff66; margin-left: 12px; }
    .box { width: 600px; margin: 32px auto; background: #fff; border: 2px solid #000; }
    .box h2 { margin: 0; background: #ffff66; border-bottom: 2px solid #000; text-align: center;
              padding: 8px; font-size: 20px; font-weight: normal; }
    .content { padding: 16px 32px; }
    table.form td { padding: 6px; vertical-align: top; }
    input[type=text], input[type=password], input[type=date], select, textarea { width: 280px; padding: 4px; }
    .btn { display: block; width: 300px; margin: 10px auto; padding: 6px; background: #ffff66;
           border: 1px solid #000; font-size: 15px; cursor: pointer; text-align: center; color: #000; }
    table.list { border-collapse: collapse; width: 100%; margin: 8px 0; }
    table.list th { background: #ffff66; }
    table.list td, table.list th { border: 1px solid #000; padding: 6px; text-align: center; }
    .msg { color: #c00; text-align: center; }
    .err { color: #c00; font-size: 13px; }
</style>
<script>
    function openPage(page) { window.location.href = page; }
</script>
<div class="topbar">RestMan2
<% if (hdrUser != null) { %>
    &nbsp;|&nbsp; <%= esc(hdrUser.getFullName()) %>
    <a href="<%= ctx %>/LoginServlet2">Đăng xuất</a>
<% } %>
</div>
