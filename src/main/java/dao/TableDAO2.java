package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.Table2;

/**
 * Module Online table reservation 2 (Hinh 19):
 * +TableDAO2(), +getEmptyTables(bookingTime: Date): Table2[]
 */
public class TableDAO2 extends DAO2 {

    /** Moi luot dat giu ban trong 2 gio ke tu gio den. */
    public static final int HOLD_MINUTES = 120;
    /** Trang thai don da huy thi khong giu ban. */
    public static final String CANCELLED = "Đã huỷ";

    public TableDAO2() {
        super();
    }

    /**
     * Lay danh sach ban con trong: ban khong co don dat ban nao (chua huy)
     * co gio den cach gio khach chon duoi 2 gio.
     * @param bookingTime ngay gio den khach chon
     * @return danh sach ban trong (co the rong); null neu loi CSDL
     */
    public ArrayList<Table2> getEmptyTables(Date bookingTime) {
        ArrayList<Table2> kq = new ArrayList<Table2>();
        String sql = "SELECT * FROM tblTable2 t WHERE t.id NOT IN ("
                + " SELECT rt.tblTable2id FROM tblReservationTable2 rt, tblReservation2 r"
                + " WHERE rt.tblReservation2id = r.id AND r.status <> ?"
                + " AND ABS(TIMESTAMPDIFF(MINUTE, r.bookingTime, ?)) < ?)"
                + " ORDER BY t.tableName";
        synchronized (DAO2.class) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, CANCELLED);
                ps.setTimestamp(2, new Timestamp(bookingTime.getTime()));
                ps.setInt(3, HOLD_MINUTES);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Table2 t = new Table2();
                    t.setId(rs.getInt("id"));
                    t.setTableName(rs.getString("tableName"));
                    t.setCapacity(rs.getInt("capacity"));
                    t.setStatus(rs.getString("status"));
                    t.setDescription(rs.getString("description"));
                    kq.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return kq;
    }
}
