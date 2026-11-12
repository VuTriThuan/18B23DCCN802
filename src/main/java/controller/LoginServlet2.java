package controller;

import java.io.IOException;

import dao.UserDAO2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User2;

/**
 * Ho tro UC Log in 2 / Log out 2 (dieu kien truoc cua hai module).
 * doPost: dang nhap; doGet: dang xuat.
 */
@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctx = request.getContextPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User2 user = new UserDAO2().checkLogin(username, password);
        if (user == null) {
            response.sendRedirect(ctx + "/view/member/LoginPage2.jsp?err=fail");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute(user.getRole(), user);
        switch (user.getRole()) {
            case "manager":
                response.sendRedirect(ctx + "/view/manager/ManagerHomePage2.jsp");
                break;
            case "customer":
                response.sendRedirect(ctx + "/view/customer/CustomerHomePage2.jsp");
                break;
            case "salestaff":
                response.sendRedirect(ctx + "/view/salestaff/SaleStaffHomePage2.jsp");
                break;
            default:
                response.sendRedirect(ctx + "/view/warehouse/WarehouseHomePage2.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp");
    }
}
