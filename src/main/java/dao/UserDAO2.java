package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer2;
import model.Employee2;
import model.Manager2;
import model.SaleStaff2;
import model.User2;
import model.WarehouseStaff2;

/**
 * Ho tro UC Log in 2 (dieu kien truoc cua hai module): kiem tra dang nhap
 * va tra ve doi tuong dung lop con theo vai tro.
 */
public class UserDAO2 extends DAO2 {

    public UserDAO2() {
        super();
    }

    /**
     * Kiem tra dang nhap.
     * @return Manager2/SaleStaff2/WarehouseStaff2/Customer2 neu dung, null neu sai
     */
    public User2 checkLogin(String username, String password) {
        if (username == null || password == null) return null;
        String sql = "SELECT u.*, e.position, e.hireDate, c.customerType, c.rewardPoints"
                + " FROM tblUser2 u"
                + " LEFT JOIN tblEmployee2 e ON e.tblUser2id = u.id"
                + " LEFT JOIN tblCustomer2 c ON c.tblUser2id = u.id"
                + " WHERE u.username = ? AND u.password = ?";
        synchronized (DAO2.class) {
            return query(sql, username, password);
        }
    }

    private User2 query(String sql, String username, String password) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                User2 u;
                if ("customer".equalsIgnoreCase(role)) {
                    Customer2 c = new Customer2();
                    c.setCustomerType(rs.getString("customerType"));
                    c.setRewardPoints(rs.getInt("rewardPoints"));
                    u = c;
                } else {
                    Employee2 e;
                    if ("manager".equalsIgnoreCase(role)) e = new Manager2();
                    else if ("salestaff".equalsIgnoreCase(role)) e = new SaleStaff2();
                    else if ("warehousestaff".equalsIgnoreCase(role)) e = new WarehouseStaff2();
                    else return null;
                    e.setPosition(rs.getString("position"));
                    e.setHireDate(rs.getDate("hireDate"));
                    u = e;
                }
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullName(rs.getString("fullName"));
                u.setDob(rs.getDate("dob"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setRole(role);
                u.setNote(rs.getString("note"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
