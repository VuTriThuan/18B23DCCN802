package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.TableDAO2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Table2;

/**
 * Module Online table reservation 2 (Hinh 19): TableServlet2 ke thua HttpServlet,
 * +doGet(request, response): void - nhan ngay, gio, so nguoi; kiem tra hop le;
 * goi TableDAO2.getEmptyTables(); chuyen tiep danh sach ban trong sang EmptyTablePage2.jsp
 * (hoac quay lai SearchTablePage2.jsp kem thong bao loi).
 */
@WebServlet("/TableServlet2")
public class TableServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String SEARCH_PAGE = "/view/customer/SearchTablePage2.jsp";
    private static final String EMPTY_PAGE = "/view/customer/EmptyTablePage2.jsp";
    /** Chi cho dat ban trong vong 10 ngay ke tu hien tai. */
    private static final long MAX_DAYS = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") == null) {
            response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
            return;
        }
        String inDate = request.getParameter("inDate");
        String inTime = request.getParameter("inTime");
        String inGuestCount = request.getParameter("inGuestCount");

        // ngoai le 3.1: thoi gian khong hop le
        Date bookingTime = parse(inDate, inTime);
        Date now = new Date();
        if (bookingTime == null || bookingTime.before(now)
                || bookingTime.getTime() - now.getTime() > MAX_DAYS * 24 * 60 * 60 * 1000L) {
            back(request, response, "Thời gian đặt bàn không hợp lệ, vui lòng chọn lại");
            return;
        }
        // ngoai le 3.2: so nguoi khong hop le
        int guestCount;
        try {
            guestCount = Integer.parseInt(inGuestCount.trim());
        } catch (Exception e) {
            guestCount = 0;
        }
        if (guestCount <= 0) {
            back(request, response, "Số người không hợp lệ");
            return;
        }

        // goi TableDAO2 tim ban trong
        ArrayList<Table2> listTable = new TableDAO2().getEmptyTables(bookingTime);
        if (listTable == null) {
            back(request, response, "Lỗi hệ thống, vui lòng thử lại");
            return;
        }
        // ngoai le 4.1: khong con ban trong
        if (listTable.isEmpty()) {
            back(request, response, "Không có bàn trống trong thời gian này");
            return;
        }
        session.setAttribute("listTable", listTable);
        session.setAttribute("bookingTime", bookingTime);
        session.setAttribute("guestCount", guestCount);
        session.removeAttribute("selectedTables");
        if ("1".equals(request.getParameter("booked"))) {
            request.setAttribute("message", "Bàn bạn chọn vừa được khách hàng khác đặt, vui lòng chọn lại");
        }
        request.getRequestDispatcher(EMPTY_PAGE).forward(request, response);
    }

    private void back(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
    }

    private static Date parse(String date, String time) {
        if (date == null || time == null) return null;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            f.setLenient(false);
            return f.parse(date.trim() + " " + time.trim());
        } catch (Exception e) {
            return null;
        }
    }
}
