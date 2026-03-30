	DROP DATABASE iStars

CREATE DATABASE iStars
use iStars
GO

CREATE TABLE XuatXu(
	id int identity primary key,
	noiSanXuat nvarchar(50)
)
CREATE TABLE Pin(
	id int identity primary key,
	dungLuong INT NOT NULL 
)

CREATE TABLE SanPham(
	id int identity primary key,
	ma varchar(20),
	ten nvarchar(50),
	idXX int references XuatXu(id),
	idPin int references Pin(id),
	chieuDai DECIMAL(6,1), 
    chieuRong DECIMAL(6,1),
    doDay DECIMAL(6,1),
	soLuong int
)
CREATE TABLE Rom(
	id int identity primary key,
	dungLuong INT NOT NULL
)



CREATE TABLE MauSac(
	id int primary key identity,
	maMau varchar(10),
	ten nvarchar(10)		
)

CREATE TABLE ChiTietSanPham(
	id int primary key identity,
	idSP int references SanPham(id),
	idRom int references Rom(id),
	idMau int references MauSac(id),
	gia DECIMAL(20,2),	
	ghiChu nvarchar(50),
	trangThai int,
	soLuong int
)

CREATE TABLE Imei(
	id int primary key identity,
	maImei varchar(15),
	trangThai int ,
	idCTSP int references ChiTietSanPham(id)
)

CREATE TABLE KhachHang(
	id int primary key identity,
	maKH varchar(10),
	gioiTinh int,
	diaChi nvarchar(50),
	email varchar(50),
	TrangThai int,
	tenKH nvarchar(50),
	soDT nvarchar(12)
)



CREATE TABLE PhieuGiamGia(
	id int identity primary key,
	maPhieu nvarchar(10),
	ten nvarchar(50),
	loai int,
	giaTriGiam DECIMAL(20,2),
	giamToiDa DECIMAL(20,2),
	dieuKienAD DECIMAL(20,2),
	soLuong int,
	trangThai int,
	ngayTao date,
	ngayHetHan date
)

CREATE TABLE NhanVien(
	id int primary key identity,
	maNV varchar(10),
	hoTen nvarchar(50),
	gioiTinh int,
	ngaySinh varchar(50),
	sdt nvarchar(10),
	matKhau varchar(50),
	chucVu int,
	trangThai int,
	Email nvarchar(100)
)


CREATE TABLE HoaDon(
	id int primary key identity,	
	idKH int references KhachHang(id),
	idNV int references NhanVien(id),
	maHoaDon varchar(10),
	ngayTao date,
	ngayThanhToan date,
	trangThai int,
	maPGG int references PhieuGiamGia(id),
	tongGia DECIMAL(20,2),
	tongGiaSauPGG DECIMAL(20,2)
)

CREATE TABLE HoaDonChiTiet(
	id int primary key identity,
	idSanPham int references ChiTietSanPham(id),
	idHoaDon int references HoaDon(id),
	gia DECIMAL(20,2),
	soLuong int,
	trangThai int,
	
)

CREATE TABLE ImeiDaBan(
	id int primary key identity,
	idHoaDonCT int references HoaDonChiTiet(id),
	maImei varchar(15)
)


INSERT INTO XuatXu (noiSanXuat) VALUES
	(N'Trung Quốc'),
	(N'Ấn Độ'),
	(N'Việt Nam'),
	(N'Mỹ'),
	(N'Hàn Quốc');
	select * from XuatXu 	

	INSERT INTO Pin (dungLuong) VALUES
	(2716 ),
	(3110 ),
	(2815 ),
	(3240),
	(3279),
	(3349 ),
	(3561);
	select * from Pin
INSERT INTO Rom (dungLuong) VALUES
    (64),
    (128),
    (256),
    (512),
    (1024);
	select * from Rom
INSERT INTO MauSac (maMau, ten) VALUES
    ('BLK', N'Đen'),
    ('WHT', N'Trắng'),
    ('GLD', N'Vàng'),
    ('RED', N'Đỏ');

INSERT INTO SanPham (ma, ten, idXX, idPin,chieuDai,chieuRong,doDay , soLuong) VALUES
     ('IPX', N'iPhone X',     1, 1, 143.6, 70.9, 7.7 , 1),
    ('IPXS', N'iPhone XS',   1, 1, 143.6, 70.9, 7.7, 1),
    ('IPXR', N'iPhone XR',   1, 2, 150.9, 75.7, 8.3, 1),
    ('IP11', N'iPhone 11',   1, 2, 150.9, 75.7, 8.3, 1),
    ('IP12', N'iPhone 12',   1, 3, 146.7, 71.5, 7.4, 1),
    ('IP13', N'iPhone 13',   1, 4, 146.7, 71.5, 7.7, 1),
    ('IP14', N'iPhone 14',   1, 5, 146.7, 71.5, 7.8, 1),
    ('IP15', N'iPhone 15',   1, 6, 147.6, 71.6, 7.8, 1),
    ('IP16', N'iPhone 16',   1, 7, 148.0, 72.0, 7.8, 1);
	select * from SanPham

INSERT INTO ChiTietSanPham (idSP, idRom, idMau, gia, ghiChu, trangThai , soLuong) VALUES
    (1, 1, 1, 10000000, N'iPhone X - Đen - 64 GB',1,10),
    (2, 1, 1, 12000000, N'iPhone XS - 64GB - Đen', 1,10),
    (3, 2, 2, 13000000, N'iPhone XR - 128GB - Trắng', 1, 10),
    (4, 2, 2, 14000000, N'iPhone 11 - 128GB - Trắng', 1, 10),
    (5, 3, 3, 15000000, N'iPhone 12 - 256GB - Vàng', 1, 10),
    (6, 3, 3, 16000000, N'iPhone 13 - 256GB - Vàng', 1, 10),
    (7, 4, 4, 17000000, N'iPhone 14 - 512GB - Đỏ', 1, 10),
    (8, 4, 4, 18000000, N'iPhone 15 - 512GB - Đỏ', 1, 10),
    (9, 5, 4, 20000000,  N'Hàng mới', 1, 10);
		select * from  ChiTietSanPham 

	INSERT INTO Imei (maImei, trangThai,idCTSP) VALUES
('200000000000000', 0, 1),
('200000000100000', 0, 1),
('200000000200000', 0, 1),
('200000000300000', 1, 1),
('200000000400000', 1, 1),
('200000000500000', 1, 1),
('200000000600000', 1, 1),
('200000000700000', 1, 1),
('200000000800000', 1, 1),
('200000000900000', 1, 1),
('200000001000000', 1, 2),
('200000001100000', 1, 2),
('200000001200000', 1, 2),
('200000001300000', 1, 2),
('200000001400000', 1, 2),
('200000001500000', 1, 2),
('200000001600000', 1, 2),
('200000001700000', 1, 2),
('200000001800000', 1, 2),
('200000001900000', 1, 2),
('200000002000000', 1, 3),
('200000002100000', 1, 3),
('200000002200000', 1, 3),
('200000002300000', 1, 3),
('200000002400000', 1, 3),
('200000002500000', 1, 3),
('200000002600000', 1, 3),
('200000002700000', 1, 3),
('200000002800000', 1, 3),
('200000002900000', 1, 3),
('200000003000000', 1, 4),
('200000003100000', 1, 4),
('200000003200000', 1, 4),
('200000003300000', 1, 4),
('200000003400000', 1, 4),
('200000003500000', 1, 4),
('200000003600000', 1, 4),
('200000003700000', 1, 4),
('200000003800000', 1, 4),
('200000003900000', 1, 4),
('200000004000000', 1, 5),
('200000004100000', 1, 5),
('200000004200000', 1, 5),
('200000004300000', 1, 5),
('200000004400000', 1, 5),
('200000004500000', 1, 5),
('200000004600000', 1, 5),
('200000004700000', 1, 5),
('200000004800000', 1, 5),
('200000004900000', 1, 5),
('200000005000000', 1, 6),
('200000005100000', 1, 6),
('200000005200000', 1, 6),
('200000005300000', 1, 6),
('200000005400000', 1, 6),
('200000005500000', 1, 6),
('200000005600000', 1, 6),
('200000005700000', 1, 6),
('200000005800000', 1, 6),
('200000005900000', 1, 6),
('200000006000000', 1, 7),
('200000006100000', 1, 7),
('200000006200000', 1, 7),
('200000006300000', 1, 7),
('200000006400000', 1, 7),
('200000006500000', 1, 7),
('200000006600000', 1, 7),
('200000006700000', 1, 7),
('200000006800000', 1, 7),
('200000006900000', 1, 7),
('200000007000000', 1, 8),
('200000007100000', 1, 8),
('200000007200000', 1, 8),
('200000007300000', 1, 8),
('200000007400000', 1, 8),
('200000007500000', 1, 8),
('200000007600000', 1, 8),
('200000007700000', 1, 8),
('200000007800000', 1, 8),
('200000007900000', 1, 8),
('200000008000000', 1, 9),
('200000008100000', 1, 9),
('200000008200000', 1, 9),
('200000008300000', 1, 9),
('200000008400000', 1, 9),
('200000008500000', 1, 9),
('200000008600000', 1, 9),
('200000008700000', 1, 9),
('200000008800000', 1, 9),
('200000008900000', 1, 9);
	select * from Imei 

INSERT INTO KhachHang (maKH, gioiTinh, diaChi, email, TrangThai, tenKH, soDT) VALUES
	('KH000', 2, NULL, NULL, 1, N'Khách lẻ', NULL),
    ('KH001', 1, N'Hà Nội', 'kh1@email.com',1, 'hilo', '0974241326'),
    ('KH002', 0, N'Hồ Chí Minh', 'kh2@email.com',1, 'hilo', '0974241325'),
    ('KH003', 1, N'Đà Nẵng', 'kh3@email.com',1, 'hilo', '0974241327')
	select * from KhachHang
		
INSERT INTO NhanVien (maNV, hoTen, gioiTinh, ngaySinh, sdt, matKhau, chucVu, trangThai, Email) VALUES
    ('NV001', N'Nguyễn Văn A', 1, '1990-01-01', '0900000001', '123', 1, 1, 'cc.com'),
    ('NV002', N'Trần Thị B', 0, '1992-02-02', '0900000002', '123', 2, 1,'vv.com'),
	('c', N'Chơn Quang Su', 1, '1992-02-02', '0900000002', 'c', 1, 1,'sonchuquang5@gmail.com');
	select * from NhanVien
	INSERT INTO PhieuGiamGia (maPhieu, ten, loai, giaTriGiam, giamToiDa, dieuKienAD,soLuong, trangThai, ngayTao, ngayHetHan) 
VALUES 
    ('PGG00', N'Không dùng phiếu giảm giá', 2, NULL, 1000000,300,7, 1, '2025-06-01', '2025-12-31'),
	('PGG02', N'Ngay hoi vui', 1, 500000, 1000000,3000, 7,1, '2024-06-01', '2024-12-31'),
	('PGG03', N'Giáng sinh', 1, 500000, 1000000, 40000,7, 1, '2024-06-01', '2024-12-31');
	select * from PhieuGiamGia where trangThai = 1
	
INSERT INTO HoaDon (idKH, idNV, maHoaDon, ngayTao, ngayThanhToan, trangThai, maPGG, tongGia, tongGiaSauPGG) VALUES
(1, 2, 'HD003', '2024-09-02', '2024-07-02', 0, 1, 15000000,15000000),
    (2, 2, 'HD002', '2024-06-02', '2024-07-02', 1, 1, 15000000,15000000),
	(3, 2, 'HD001', '2024-06-02', '2024-06-02', 1, 1, 15000000,15000000);	
		select * from HoaDon 
		select * from HoaDonChiTiet  
INSERT INTO HoaDonChiTiet (idSanPham, idHoaDon, gia,soLuong, trangThai) VALUES
    (1, 1, 10000000, 1,1),
    (2, 1, 12000000, 1,1),
    (3, 1, 13000000, 1,1);
	select * from HoaDonChiTiet  
	
INSERT INTO ImeiDaBan (idHoaDonCT, maImei) VALUES
(1, '200000000000000'), 
(2, '200000000100000'), 
(3, '200000000200000');																																																																																					
	
	
	