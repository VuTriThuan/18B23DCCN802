# RestMan2 – Code cho Đề 18 (18B23DCCN802)

Cài đặt hai module của bài:

- **Add dish 2** (nhân viên quản lý thêm món ăn mới)
- **Online table reservation 2** (khách hàng đặt bàn trực tuyến)

Code theo mô hình MVC đã thiết kế trong báo cáo: trang JSP (view) → Servlet (controller) → DAO → lớp thực thể (model).
Tên lớp, tên phương thức và tên thuộc tính khớp với Hình 11, 12, 15, 19–23.

## 1. Cần có

| Phần mềm | Phiên bản đã chạy thử |
|---|---|
| JDK | 21 (cần tối thiểu 11) |
| Apache Tomcat | 10.1 (dùng gói `jakarta.servlet`) |
| MySQL | 8.0 |
| Maven | 3.9 (NetBeans có sẵn) |

## 2. Tạo cơ sở dữ liệu

Mở `database/restman2.sql` trong MySQL Workbench rồi bấm Execute.
Hoặc chạy lệnh sau trong terminal:

```bash
mysql -u root -p < database/restman2.sql
```

Script tạo database `restman2` gồm 23 bảng theo Hình 12, kèm dữ liệu mẫu khớp với kịch bản ở mục II.1.

## 3. Sửa thông tin kết nối

Mở `src/main/java/dao/DAO2.java` và sửa 3 hằng số cho khớp với MySQL trên máy:

```java
private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/restman2?...";
private static final String DEFAULT_USER = "root";
private static final String DEFAULT_PASSWORD = "";   // <- mật khẩu MySQL của anh
```

Giáo trình (mục 5.2) cũng hướng dẫn sửa `dbUrl`, `username`, `password` ngay trong lớp DAO như vậy.

## 4. Chạy

**Cách 1: NetBeans**

1. File → Open Project, chọn thư mục `RestMan2`. Đây là project Maven nên NetBeans tự nhận.
2. Chuột phải project → Properties → Run, chọn Server là Apache Tomcat 10.1.
3. Bấm Run. Trình duyệt mở `http://localhost:8080/RestMan2/`.

**Cách 2: dùng file war**

```bash
mvn package
```

Copy `target/RestMan2.war` vào thư mục `webapps` của Tomcat rồi chạy Tomcat.

## 5. Tài khoản dùng thử

Mật khẩu của tất cả tài khoản là `123456`.

| Tài khoản | Vai trò | Dùng để thử |
|---|---|---|
| `quanly` | Nhân viên quản lý | Module Add dish 2 |
| `nguyenvana` | Khách hàng Nguyễn Văn A | Module Online table reservation 2 |
| `tranthib` | Khách hàng đã có 2 đơn ngày 20/11/2026 | Thử ngoại lệ |
| `banhang`, `kho` | Nhân viên bán hàng, nhân viên kho | Chỉ có trang chủ |

## 6. Kịch bản chạy thử (khớp bảng kịch bản ở mục II.1)

### Add dish 2

Đăng nhập `quanly` → Quản lý thông tin món ăn → Thêm món ăn. Nhập các thông tin:

- Tên món: Rau muống xào tỏi
- Đơn giá: 40000
- Đơn vị tính: Đĩa
- Danh mục: Món chính
- Ảnh: raumuong.jpg

Bấm Thêm → hệ thống báo "Thêm món ăn thành công" → OK → quay về trang quản lý.

| Ngoại lệ | Cách thử | Kết quả |
|---|---|---|
| 5.1 | Để trống Tên món hoặc Đơn giá | "Vui lòng nhập đầy đủ thông tin bắt buộc" |
| 5.2 | Đơn giá `-40000` hoặc `bốn mươi nghìn` | "Đơn giá không hợp lệ" |
| 6.1 | Thêm lại "Rau muống xào tỏi" (kể cả viết thường) | "Món ăn đã tồn tại" |

### Online table reservation 2

1. Đăng nhập `nguyenvana` → Đặt bàn.
2. Chọn ngày 20/11/2026, giờ 19:00, số người 8 → Tìm kiếm. Danh sách bàn trống ra B01, B02, B05, B07.
3. Tích B01 và B02 → Tiếp tục.
4. Sửa Số điện thoại thành 0987654321, Ghi chú "Tổ chức sinh nhật" → Xác nhận.
5. Hệ thống báo "Đặt bàn thành công. Mã đặt bàn: DB0003…" → OK → về trang chủ khách hàng.

| Ngoại lệ | Cách thử | Kết quả |
|---|---|---|
| 3.1 | Chọn ngày đã qua hoặc quá 10 ngày tới | "Thời gian đặt bàn không hợp lệ, vui lòng chọn lại" |
| 3.2 | Số người `0` hoặc chữ | "Số người không hợp lệ" |
| 4.1 | Khung giờ mọi bàn đều bận | "Không có bàn trống trong thời gian này" |
| 5.1 | Không chọn bàn, hoặc chỉ chọn B01 cho 8 người | "Vui lòng chọn đủ bàn cho 8 người" |
| 7.1 | Để trống họ tên, SĐT sai, email sai | Báo lỗi ngay dưới ô tương ứng |
| 7.2 | Bàn vừa bị khách khác đặt trước khi bấm Xác nhận | Quay lại danh sách bàn trống (đã bỏ bàn đó) kèm thông báo |

Quy tắc bàn trống (theo mục I.2.c của báo cáo): mỗi lượt đặt giữ bàn 2 giờ. Một bàn được coi là trống nếu không có đơn chưa huỷ nào có giờ đến cách giờ khách chọn dưới 2 giờ.

## 7. Cấu trúc thư mục (khớp Hình 24)

```
src/main/java/model/        30 lớp thực thể (Hình 11)
src/main/java/dao/          DAO2, DishDAO2, TableDAO2, ReservationDAO2 (+ UserDAO2 cho đăng nhập)
src/main/java/controller/   DishServlet2, TableServlet2, ReservationServlet2 (+ LoginServlet2)
src/main/webapp/view/member/    LoginPage2.jsp
src/main/webapp/view/manager/   ManagerHomePage2.jsp, ManageDishPage2.jsp, AddDishPage2.jsp
src/main/webapp/view/customer/  CustomerHomePage2.jsp, SearchTablePage2.jsp,
                                EmptyTablePage2.jsp, ReservationInfoPage2.jsp
src/main/webapp/view/salestaff/, view/warehouse/   trang chủ của hai vai trò còn lại
database/restman2.sql       tạo CSDL + dữ liệu mẫu (Hình 12)
```

## 8. Ghi chú để trả lời khi cô hỏi

- **Kiểu mảng:** biểu đồ ghi kiểu mảng (`Table2[]`) thì code dùng `ArrayList<Table2>`, giống code mẫu của giáo trình (`getKihocdangki(): Kihoc[]` → `ArrayList<Kihoc>`).
- **Gói jakarta:** Tomcat 10 trở lên dùng gói `jakarta.servlet` thay cho `javax.servlet`. Lớp cha vẫn là `HttpServlet` như Hình 15 và Hình 19.
- **Đăng nhập:** `LoginServlet2` và `UserDAO2` chỉ để đăng nhập, là điều kiện trước của cả hai module (UC Log in 2). Chúng không nằm trong biểu đồ lớp của hai module.
- **Ngoại lệ 7.2:** `ReservationServlet2` chuyển hướng qua `TableServlet2` để tìm lại bàn trống rồi hiển thị `EmptyTablePage2.jsp`, nên danh sách không còn bàn vừa bị đặt.
- **Transaction:** `ReservationDAO2.addReservation()` chạy trong một transaction (`setAutoCommit(false)`, `commit`, `rollback`), giống hàm `luuDKcuaSV()` trong giáo trình. Hàm khoá các bàn đã chọn bằng `SELECT … FOR UPDATE` trước khi kiểm tra, để hai khách không đặt trùng một bàn.
