package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import dao.DishDAO2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Dish2;

/**
 * Module Add dish 2 (Hinh 15): DishServlet2 ke thua HttpServlet,
 * +doPost(request, response): void - nhan du lieu form them mon an, kiem tra hop le,
 * dong goi Dish2, goi DishDAO2.addDish() roi chuyen tiep ket qua ve AddDishPage2.jsp.
 */
@WebServlet("/DishServlet2")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class DishServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PAGE = "/view/manager/AddDishPage2.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("manager") == null) {
            response.sendRedirect(request.getContextPath() + "/view/member/LoginPage2.jsp?err=timeout");
            return;
        }
        // nhan du lieu tu form
        String name = trim(request.getParameter("inName"));
        String unitPrice = trim(request.getParameter("inUnitPrice"));
        Part image = request.getPart("inImage");
        String imageName = (image != null && image.getSize() > 0)
                ? Paths.get(image.getSubmittedFileName()).getFileName().toString() : null;

        // dong goi doi tuong Dish2
        Dish2 dish = new Dish2();
        dish.setName(name);
        dish.setUnit(trim(request.getParameter("inUnit")));
        dish.setCategory(trim(request.getParameter("inCategory")));
        dish.setDescription(trim(request.getParameter("inDescription")));
        dish.setStatus(trim(request.getParameter("inStatus")));
        dish.setImage(imageName);
        request.setAttribute("dish", dish);
        request.setAttribute("unitPriceText", unitPrice);

        // kiem tra hop le (ngoai le 5.1, 5.2 cua kich ban)
        if (name.isEmpty() || unitPrice.isEmpty()) {
            forward(request, response, "Vui lòng nhập đầy đủ thông tin bắt buộc", false);
            return;
        }
        float price;
        try {
            price = Float.parseFloat(unitPrice);
        } catch (NumberFormatException e) {
            price = -1;
        }
        if (price <= 0) {
            forward(request, response, "Đơn giá không hợp lệ", false);
            return;
        }
        dish.setUnitPrice(price);

        // goi DishDAO2 them mon an (ngoai le 6.1: trung ten)
        if (!new DishDAO2().addDish(dish)) {
            forward(request, response, "Món ăn đã tồn tại", false);
            return;
        }
        saveImage(image, imageName);
        forward(request, response, "Thêm món ăn thành công", true);
    }

    private void saveImage(Part image, String imageName) {
        if (imageName == null) return;
        try {
            String folder = getServletContext().getRealPath("/images");
            if (folder != null) {
                new File(folder).mkdirs();
                image.write(folder + File.separator + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response,
            String message, boolean success) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.setAttribute("success", success);
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
