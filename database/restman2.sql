-- =====================================================================
-- CSDL restman2 – Đề 18 RestMan (theo Hình 12: Biểu đồ thiết kế CSDL)
-- 23 bảng, khoá ngoại đặt ở phía n; ngày giờ dùng TIMESTAMP.
-- Chạy: mysql -u root -p < restman2.sql   (hoặc mở file trong MySQL Workbench rồi Execute)
-- =====================================================================
SET NAMES utf8mb4;  -- để tiếng Việt không bị lỗi phông dù terminal dùng bảng mã nào
DROP DATABASE IF EXISTS restman2;
-- utf8mb4_0900_as_ci: không phân biệt hoa/thường nhưng PHÂN BIỆT dấu tiếng Việt
-- (để "Chả cá" và "Cha ca" không bị coi là trùng tên món).
CREATE DATABASE restman2 CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci;
USE restman2;

CREATE TABLE tblRestaurant2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  phone VARCHAR(255),
  description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblUser2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  fullName VARCHAR(255),
  dob DATE,
  address VARCHAR(255),
  phone VARCHAR(255),
  email VARCHAR(255),
  role VARCHAR(255) NOT NULL,
  note VARCHAR(255) NULL
) ENGINE=InnoDB;

CREATE TABLE tblEmployee2 (
  tblUser2id INT PRIMARY KEY,
  position VARCHAR(255),
  hireDate DATE,
  tblRestaurant2id INT NOT NULL,
  FOREIGN KEY (tblUser2id) REFERENCES tblUser2(id),
  FOREIGN KEY (tblRestaurant2id) REFERENCES tblRestaurant2(id)
) ENGINE=InnoDB;

CREATE TABLE tblCustomer2 (
  tblUser2id INT PRIMARY KEY,
  customerType VARCHAR(255),
  rewardPoints INT DEFAULT 0,
  FOREIGN KEY (tblUser2id) REFERENCES tblUser2(id)
) ENGINE=InnoDB;

CREATE TABLE tblMembershipCard2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cardNumber VARCHAR(255),
  issueDate DATE,
  validUntil DATE,
  `rank` VARCHAR(255),
  tblCustomer2id INT NOT NULL UNIQUE,
  FOREIGN KEY (tblCustomer2id) REFERENCES tblCustomer2(tblUser2id)
) ENGINE=InnoDB;

CREATE TABLE tblTable2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tableName VARCHAR(255) NOT NULL,
  capacity INT NOT NULL,
  status VARCHAR(255),
  description VARCHAR(255) NULL,
  tblRestaurant2id INT NOT NULL,
  FOREIGN KEY (tblRestaurant2id) REFERENCES tblRestaurant2(id)
) ENGINE=InnoDB;

CREATE TABLE tblReservation2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  bookingTime TIMESTAMP NULL,
  guestCount INT NOT NULL,
  status VARCHAR(255),
  note VARCHAR(255) NULL,
  tblCustomer2id INT NOT NULL,
  tblEmployee2id INT NULL,
  FOREIGN KEY (tblCustomer2id) REFERENCES tblCustomer2(tblUser2id),
  FOREIGN KEY (tblEmployee2id) REFERENCES tblEmployee2(tblUser2id)
) ENGINE=InnoDB;

CREATE TABLE tblReservationTable2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblReservation2id INT NOT NULL,
  tblTable2id INT NOT NULL,
  FOREIGN KEY (tblReservation2id) REFERENCES tblReservation2(id),
  FOREIGN KEY (tblTable2id) REFERENCES tblTable2(id)
) ENGINE=InnoDB;

CREATE TABLE tblDish2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  unitPrice FLOAT NOT NULL,
  unit VARCHAR(255) NULL,
  category VARCHAR(255) NULL,
  description VARCHAR(255) NULL,
  image VARCHAR(255),
  status VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblCombo2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price FLOAT,
  description VARCHAR(255) NULL
) ENGINE=InnoDB;

CREATE TABLE tblComboDish2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblCombo2id INT NOT NULL,
  quantity INT,
  tblDish2id INT NOT NULL,
  FOREIGN KEY (tblCombo2id) REFERENCES tblCombo2(id),
  FOREIGN KEY (tblDish2id) REFERENCES tblDish2(id)
) ENGINE=InnoDB;

CREATE TABLE tblMenu2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  effectiveDate DATE,
  description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblMenuDish2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblMenu2id INT NOT NULL,
  tblDish2id INT NOT NULL,
  FOREIGN KEY (tblMenu2id) REFERENCES tblMenu2(id),
  FOREIGN KEY (tblDish2id) REFERENCES tblDish2(id)
) ENGINE=InnoDB;

CREATE TABLE tblMenuCombo2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblMenu2id INT NOT NULL,
  tblCombo2id INT NOT NULL,
  FOREIGN KEY (tblMenu2id) REFERENCES tblMenu2(id),
  FOREIGN KEY (tblCombo2id) REFERENCES tblCombo2(id)
) ENGINE=InnoDB;

CREATE TABLE tblOrder2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  orderTime TIMESTAMP NULL,
  orderType VARCHAR(255),
  status VARCHAR(255),
  tblCustomer2id INT NOT NULL,
  tblEmployee2id INT NOT NULL,
  tblReservation2id INT NULL,
  tblTable2id INT NOT NULL,
  FOREIGN KEY (tblCustomer2id) REFERENCES tblCustomer2(tblUser2id),
  FOREIGN KEY (tblEmployee2id) REFERENCES tblEmployee2(tblUser2id),
  FOREIGN KEY (tblReservation2id) REFERENCES tblReservation2(id),
  FOREIGN KEY (tblTable2id) REFERENCES tblTable2(id)
) ENGINE=InnoDB;

CREATE TABLE tblOrderDetail2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblOrder2id INT NOT NULL,
  quantity INT,
  price FLOAT,
  note VARCHAR(255),
  tblDish2id INT NOT NULL,
  FOREIGN KEY (tblOrder2id) REFERENCES tblOrder2(id),
  FOREIGN KEY (tblDish2id) REFERENCES tblDish2(id)
) ENGINE=InnoDB;

CREATE TABLE tblOrderCombo2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  quantity INT,
  price FLOAT,
  note VARCHAR(255),
  tblOrder2id INT NOT NULL,
  tblCombo2id INT NOT NULL,
  FOREIGN KEY (tblOrder2id) REFERENCES tblOrder2(id),
  FOREIGN KEY (tblCombo2id) REFERENCES tblCombo2(id)
) ENGINE=InnoDB;

CREATE TABLE tblBill2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblOrder2id INT NOT NULL UNIQUE,
  paymentTime TIMESTAMP NULL,
  paymentMethod VARCHAR(255),
  discount FLOAT NULL,
  tblEmployee2id INT NOT NULL,
  tblMembershipCard2id INT NULL,
  FOREIGN KEY (tblOrder2id) REFERENCES tblOrder2(id),
  FOREIGN KEY (tblEmployee2id) REFERENCES tblEmployee2(tblUser2id),
  FOREIGN KEY (tblMembershipCard2id) REFERENCES tblMembershipCard2(id)
) ENGINE=InnoDB;

CREATE TABLE tblSupplier2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  phone VARCHAR(255),
  email VARCHAR(255),
  description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblIngredient2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  unit VARCHAR(255),
  quantityInStock FLOAT
) ENGINE=InnoDB;

CREATE TABLE tblSupplierIngredient2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  supplyPrice FLOAT,
  tblSupplier2id INT NOT NULL,
  tblIngredient2id INT NOT NULL,
  FOREIGN KEY (tblSupplier2id) REFERENCES tblSupplier2(id),
  FOREIGN KEY (tblIngredient2id) REFERENCES tblIngredient2(id)
) ENGINE=InnoDB;

CREATE TABLE tblImportReceipt2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  importDate DATE,
  status VARCHAR(255),
  note VARCHAR(255) NULL,
  tblSupplier2id INT NOT NULL,
  tblEmployee2id INT NOT NULL,
  FOREIGN KEY (tblSupplier2id) REFERENCES tblSupplier2(id),
  FOREIGN KEY (tblEmployee2id) REFERENCES tblEmployee2(tblUser2id)
) ENGINE=InnoDB;

CREATE TABLE tblImportReceiptDetail2 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblImportReceipt2id INT NOT NULL,
  quantity FLOAT,
  tblIngredient2id INT NOT NULL,
  unitPrice FLOAT,
  FOREIGN KEY (tblImportReceipt2id) REFERENCES tblImportReceipt2(id),
  FOREIGN KEY (tblIngredient2id) REFERENCES tblIngredient2(id)
) ENGINE=InnoDB;

-- =====================================================================
-- DỮ LIỆU MẪU (khớp kịch bản ở mục II.1 của báo cáo)
-- Tài khoản dùng thử (mật khẩu đều là 123456):
--   quanly     – nhân viên quản lý  (module Add dish 2)
--   nguyenvana – khách hàng Nguyễn Văn A (module Online table reservation 2)
--   banhang, kho – nhân viên bán hàng, nhân viên kho
--   tranthib   – khách hàng có sẵn hai đơn đặt bàn ngày 20/11/2026
-- =====================================================================
INSERT INTO tblRestaurant2 (id, name, address, phone, description) VALUES
 (1, 'Nhà hàng RestMan', '122 Hoàng Quốc Việt, Cầu Giấy, Hà Nội', '02437654321', 'Nhà hàng món Việt, phục vụ 10:00 - 22:00');

INSERT INTO tblUser2 (id, username, password, fullName, dob, address, phone, email, role, note) VALUES
 (1, 'quanly',     '123456', 'Trần Thị Bình',  '1990-05-12', 'Cầu Giấy, Hà Nội',  '0901111111', 'quanly@restman.vn',   'manager',        NULL),
 (2, 'banhang',    '123456', 'Lê Văn Cường',   '1998-08-20', 'Đống Đa, Hà Nội',   '0902222222', 'banhang@restman.vn',  'salestaff',      NULL),
 (3, 'kho',        '123456', 'Phạm Thị Dung',  '1995-02-03', 'Hà Đông, Hà Nội',   '0903333333', 'kho@restman.vn',      'warehousestaff', NULL),
 (4, 'nguyenvana', '123456', 'Nguyễn Văn A',   '2000-01-15', 'Thanh Xuân, Hà Nội', '0912345678', 'nguyenvana@gmail.com', 'customer',       NULL),
 (5, 'tranthib',   '123456', 'Trần Thị B',     '1999-11-30', 'Ba Đình, Hà Nội',   '0987000111', 'tranthib@gmail.com',  'customer',       NULL);

INSERT INTO tblEmployee2 (tblUser2id, position, hireDate, tblRestaurant2id) VALUES
 (1, 'Nhân viên quản lý',   '2022-01-01', 1),
 (2, 'Nhân viên bán hàng',  '2023-03-01', 1),
 (3, 'Nhân viên kho',       '2023-06-01', 1);

INSERT INTO tblCustomer2 (tblUser2id, customerType, rewardPoints) VALUES
 (4, 'Thường', 0),
 (5, 'Thành viên', 120);

INSERT INTO tblMembershipCard2 (cardNumber, issueDate, validUntil, `rank`, tblCustomer2id) VALUES
 ('TV0005', '2025-12-01', '2026-12-01', 'Bạc', 5);

INSERT INTO tblTable2 (id, tableName, capacity, status, description, tblRestaurant2id) VALUES
 (1, 'B01', 4,  'Trống', 'Tầng 1, cạnh cửa sổ', 1),
 (2, 'B02', 4,  'Trống', 'Tầng 1, cạnh cửa sổ', 1),
 (3, 'B03', 2,  'Trống', 'Tầng 1, góc trong',   1),
 (4, 'B04', 6,  'Trống', 'Tầng 1, giữa sảnh',   1),
 (5, 'B05', 6,  'Trống', 'Tầng 2',              1),
 (6, 'B06', 8,  'Trống', 'Tầng 2, ban công',    1),
 (7, 'B07', 10, 'Trống', 'Tầng 2, phòng riêng', 1);

-- Hai đơn có sẵn làm B03, B04, B06 bận lúc 19:00 ngày 20/11/2026
-- (giờ đến cách 19:00 dưới 2 giờ) -> kịch bản chỉ còn B01, B02, B05, B07 trống.
INSERT INTO tblReservation2 (id, bookingTime, guestCount, status, note, tblCustomer2id, tblEmployee2id) VALUES
 (1, '2026-11-20 18:30:00', 2,  'Đã xác nhận',  NULL,               5, 2),
 (2, '2026-11-20 20:00:00', 12, 'Chờ xác nhận', 'Liên hoan công ty', 5, NULL);

INSERT INTO tblReservationTable2 (tblReservation2id, tblTable2id) VALUES
 (1, 3),
 (2, 4),
 (2, 6);

INSERT INTO tblDish2 (name, unitPrice, unit, category, description, image, status) VALUES
 ('Gỏi cuốn tôm thịt',  60000,  'Đĩa', 'Khai vị',     'Gỏi cuốn tôm thịt chấm tương', 'goicuon.jpg', 'Đang phục vụ'),
 ('Gà nướng mật ong',   180000, 'Con', 'Món chính',   'Gà ta nướng mật ong',         'ganuong.jpg', 'Đang phục vụ'),
 ('Cá kho tộ',          120000, 'Nồi', 'Món chính',   'Cá basa kho tộ',              'cakho.jpg',   'Đang phục vụ'),
 ('Chè khúc bạch',      35000,  'Cốc', 'Tráng miệng', 'Chè khúc bạch hạnh nhân',     'chekb.jpg',   'Đang phục vụ'),
 ('Trà đá',             5000,   'Cốc', 'Đồ uống',     NULL,                          NULL,          'Đang phục vụ');
