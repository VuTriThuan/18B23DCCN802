package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.ReservationDAO2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer2;
import model.Reservation2;
import model.ReservationTable2;
import model.Table2;

/**
 * Module Online table reservation 2 (Hinh 19): ReservationServlet2 ke thua HttpServlet,
 * +doPost(request, response): void - nhan thong tin dat ban va thong tin ca nhan, kiem tra
 * hop le, dong goi Reservation2, goi ReservationDAO2.addReservation() roi chuyen tiep ket qua
 * ve ReservationInfoPage2.jsp.
 */
@WebServlet("/ReservationServlet2")
public class ReservationServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String INFO_PAGE = "/view/customer/ReservationInfoPage2.jsp";
    public static final String WAITING = "Chờ xác nhận";

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctx = request.getContextPath();
        HttpSession session = request.getSession();
        Customer2 customer = (Customer2) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect(ctx + "/view/member/LoginPage2.jsp?err=timeout");
            return;
        }
        Date bookingTime = (Date) session.getAttribute("bookingTime");
        Integer guestCount = (Integer) session.getAttribute("guestCount");
        ArrayList<Table2> selectedTables = (ArrayList<Table2>) session.getAttribute("selectedTables");
        if (bookingTime == null || guestCount == null || selectedTables == null) {
            response.sendRedirect(ctx + "/view/customer/SearchTablePage2.jsp");
            return;
        }

        // nhan va kiem tra thong tin ca nhan (ngoai le 7.1)
        String fullName = trim(request.getParameter("inoutFullName"));
        String phone = trim(request.getParameter("inoutPhone"));
        String email = trim(request.getParameter("inoutEmail"));
        String note = trim(request.getParameter("inNote"));
        boolean valid = true;
        if (fullName.isEmpty()) {
            request.setAttribute("errFullName", "Vui lòng nhập họ tên");
            valid = false;
        }
        if (!phone.matches("0\\d{9}")) {
            request.setAttribute("errPhone", "Số điện thoại phải gồm 10 chữ số, bắt đầu bằng 0");
            valid = false;
        }
        if (!email.matches("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+")) {
            request.setAttribute("errEmail", "Email không đúng định dạng");
            valid = false;
        }
        if (!valid) {
            request.setAttribute("message", "Thông tin cá nhân không hợp lệ");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
            return;
        }

        // dong goi doi tuong Reservation2
        Customer2 c = new Customer2();
        c.setId(customer.getId());
        c.setFullName(fullName);
        c.setPhone(phone);
        c.setEmail(email);
        Reservation2 r = new Reservation2();
        r.setBookingTime(bookingTime);
        r.setGuestCount(guestCount);
        r.setStatus(WAITING);
        r.setNote(note.isEmpty() ? null : note);
        r.setCustomer(c);
        ArrayList<ReservationTable2> reservationTables = new ArrayList<ReservationTable2>();
        for (Table2 t : selectedTables) {
            ReservationTable2 rt = new ReservationTable2();
            rt.setTable(t);
            reservationTables.add(rt);
        }
        r.setReservationTables(reservationTables);

        // goi ReservationDAO2 luu don dat ban
        if (!new ReservationDAO2().addReservation(r)) {
            // ngoai le 7.2: ban vua bi dat -> tim lai ban trong va quay ve danh sach ban trong
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat t = new SimpleDateFormat("HH:mm");
            response.sendRedirect(ctx + "/TableServlet2?inDate=" + d.format(bookingTime)
                    + "&inTime=" + URLEncoder.encode(t.format(bookingTime), StandardCharsets.UTF_8)
                    + "&inGuestCount=" + guestCount + "&booked=1");
            return;
        }
        // luu thanh cong: cap nhat thong tin khach trong phien, xoa du lieu tam
        customer.setFullName(fullName);
        customer.setPhone(phone);
        customer.setEmail(email);
        session.removeAttribute("listTable");
        session.removeAttribute("selectedTables");
        session.removeAttribute("bookingTime");
        session.removeAttribute("guestCount");
        request.setAttribute("success", true);
        request.setAttribute("message", String.format("Đặt bàn thành công. Mã đặt bàn: DB%04d. "
                + "Nhân viên nhà hàng sẽ liên hệ để xác nhận", r.getId()));
        request.getRequestDispatcher(INFO_PAGE).forward(request, response);
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
