package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import model.Customer2;
import model.Reservation2;
import model.ReservationTable2;

/**
 * Module Online table reservation 2 (Hinh 19):
 * +ReservationDAO2(), +addReservation(r: Reservation2): boolean
 */
public class ReservationDAO2 extends DAO2 {

    public ReservationDAO2() {
        super();
    }

    /**
     * Luu don dat ban trong mot transaction:
     * 1. khoa va kiem tra cac ban da chon van con trong,
     * 2. cap nhat thong tin ca nhan cua khach hang,
     * 3. them don dat ban (trang thai "Cho xac nhan") va cac dong ReservationTable2.
     * @return true neu luu thanh cong; false neu co ban vua bi dat hoac loi CSDL
     */
    public boolean addReservation(Reservation2 r) {
        if (r == null || r.getCustomer() == null || r.getReservationTables() == null
                || r.getReservationTables().isEmpty()) return false;
        String sqlLock = "SELECT id FROM tblTable2 WHERE id = ? FOR UPDATE";
        String sqlBusy = "SELECT COUNT(*) FROM tblReservationTable2 rt, tblReservation2 r"
                + " WHERE rt.tblReservation2id = r.id AND rt.tblTable2id = ? AND r.status <> ?"
                + " AND ABS(TIMESTAMPDIFF(MINUTE, r.bookingTime, ?)) < ?";
        String sqlCustomer = "UPDATE tblUser2 SET fullName = ?, phone = ?, email = ? WHERE id = ?";
        String sqlReservation = "INSERT INTO tblReservation2(bookingTime, guestCount, status, note,"
                + " tblCustomer2id, tblEmployee2id) VALUES(?,?,?,?,?,NULL)";
        String sqlTable = "INSERT INTO tblReservationTable2(tblReservation2id, tblTable2id) VALUES(?,?)";
        Timestamp time = new Timestamp(r.getBookingTime().getTime());
        boolean kq = false;
        synchronized (DAO2.class) {
            try {
                con.setAutoCommit(false);
                // 1. kiem tra cac ban van con trong
                for (ReservationTable2 rt : r.getReservationTables()) {
                    int tableId = rt.getTable().getId();
                    PreparedStatement psLock = con.prepareStatement(sqlLock);
                    psLock.setInt(1, tableId);
                    psLock.executeQuery();
                    PreparedStatement psBusy = con.prepareStatement(sqlBusy);
                    psBusy.setInt(1, tableId);
                    psBusy.setString(2, TableDAO2.CANCELLED);
                    psBusy.setTimestamp(3, time);
                    psBusy.setInt(4, TableDAO2.HOLD_MINUTES);
                    ResultSet rs = psBusy.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        con.rollback();
                        return false;
                    }
                }
                // 2. cap nhat thong tin ca nhan
                Customer2 c = r.getCustomer();
                PreparedStatement psCustomer = con.prepareStatement(sqlCustomer);
                psCustomer.setString(1, c.getFullName());
                psCustomer.setString(2, c.getPhone());
                psCustomer.setString(3, c.getEmail());
                psCustomer.setInt(4, c.getId());
                psCustomer.executeUpdate();
                // 3. them don dat ban
                PreparedStatement psReservation = con.prepareStatement(sqlReservation,
                        Statement.RETURN_GENERATED_KEYS);
                psReservation.setTimestamp(1, time);
                psReservation.setInt(2, r.getGuestCount());
                psReservation.setString(3, r.getStatus());
                psReservation.setString(4, r.getNote());
                psReservation.setInt(5, c.getId());
                psReservation.executeUpdate();
                ResultSet keys = psReservation.getGeneratedKeys();
                if (keys.next()) r.setId(keys.getInt(1));
                //    va cac ban da chon
                for (ReservationTable2 rt : r.getReservationTables()) {
                    PreparedStatement psTable = con.prepareStatement(sqlTable,
                            Statement.RETURN_GENERATED_KEYS);
                    psTable.setInt(1, r.getId());
                    psTable.setInt(2, rt.getTable().getId());
                    psTable.executeUpdate();
                    ResultSet k = psTable.getGeneratedKeys();
                    if (k.next()) rt.setId(k.getInt(1));
                }
                con.commit();
                kq = true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    con.rollback();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                kq = false;
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return kq;
    }
}
