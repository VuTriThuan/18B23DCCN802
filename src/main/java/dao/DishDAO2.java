package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Dish2;

/**
 * Module Add dish 2 (Hinh 15): +DishDAO2(), +addDish(dish: Dish2): boolean
 */
public class DishDAO2 extends DAO2 {

    public DishDAO2() {
        super();
    }

    /**
     * Kiem tra trung ten roi them mon an moi vao CSDL.
     * @param dish mon an da duoc DishServlet2 dong goi
     * @return true neu them thanh cong; false neu ten mon da ton tai hoac loi CSDL
     */
    public boolean addDish(Dish2 dish) {
        if (dish == null || dish.getName() == null) return false;
        String sqlCheck = "SELECT id FROM tblDish2 WHERE TRIM(name) = TRIM(?)";
        String sqlAdd = "INSERT INTO tblDish2(name, unitPrice, unit, category, description, image, status)"
                + " VALUES(?,?,?,?,?,?,?)";
        synchronized (DAO2.class) {
            try {
                // kiem tra trung ten
                PreparedStatement psCheck = con.prepareStatement(sqlCheck);
                psCheck.setString(1, dish.getName());
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) return false;

                // them mon moi
                PreparedStatement ps = con.prepareStatement(sqlAdd, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, dish.getName().trim());
                ps.setFloat(2, dish.getUnitPrice());
                ps.setString(3, dish.getUnit());
                ps.setString(4, dish.getCategory());
                ps.setString(5, dish.getDescription());
                ps.setString(6, dish.getImage());
                ps.setString(7, dish.getStatus());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) dish.setId(keys.getInt(1));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
