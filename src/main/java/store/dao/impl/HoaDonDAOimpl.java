/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import store.entity.HoaDon;
import store.entity.KhachHang;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class HoaDonDAOimpl {
    String findAllSql = "SELECT * FROM HoaDon ORDER BY NgayThanhToan DESC";
    
    String findOne = "Select * from HoaDon where id = ?";
    
    String findByMa = "Select * from HoaDon where maHoaDon = ? ORDER BY NgayThanhToan DESC";
            
    String findOneByAvailaible = "Select * from HoaDon where trangThai = 0";
    
    String findSdt = "select * from hoadon where idkh = ? ORDER BY NgayThanhToan DESC";
    
 
    
    String FindByIdKH = """
                        select * from hoadon where idkh= ? ORDER BY NgayThanhToan DESC
                        """;
    
    String findName1 ="""
                     select kh.id,kh.maKH,kh.gioiTinh,kh.diachi,kh.email,kh.trangthai,kh.tenkh,kh.sodt from khachhang kh join hoadon on kh.id = hoadon.idkh where idkh = ?
                     """;
    public KhachHang findName1(int idKH){
        return XQuery.getSingleBean(KhachHang.class, findName1, idKH);
    }
    public List<HoaDon> findByIDKH(int idKH){
        return XQuery.getBeanList(HoaDon.class, FindByIdKH,idKH);
    }

    String insert = """
                    INSERT INTO [dbo].[HoaDon]
                               (
                                [idKH],
                                [idNV],
                                [maHoaDon],
                                [ngayTao],
                                [trangThai], 
                                [maPGG],               
                                [tongGia],
                                [tongGiaSauPGG]  )

                         VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
    
    String tongTien = """
                       SELECT 
                          hd.id,
                          hd.idKH,
                          hd.idNV,
                          hd.maHoaDon,
                          hd.ngayTao,
                          hd.ngayThanhToan,
                          hd.trangThai,
                          hd.maPGG,
                          ISNULL(SUM(hdct.gia), 0) AS tongGia,
                          ISNULL(SUM(hdct.gia), 0) AS tongGiaSauPGG
                      FROM HoaDon hd
                      LEFT JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon
                      WHERE hd.trangThai = 0
                      GROUP BY 
                          hd.id,
                          hd.idKH,
                          hd.idNV,
                          hd.maHoaDon,
                          hd.ngayTao,
                          hd.ngayThanhToan,
                          hd.trangThai,
                          hd.maPGG;
                  
                      """;
    String thanhToan ="""
                      UPDATE HoaDon
                      SET 
                          ngayThanhToan = ?, 
                          trangThai = 1, 
                          tongGia = ?,
                          tongGiaSauPGG = ?,
                      maPGG = ?
                      WHERE id = ?
                      """;
    String update ="""
                      UPDATE HoaDon
                        SET 
                              maPGG = ?
                        WHERE id = ?
                      """;
    String updateIdKH = """
                        UPDATE HoaDon
                        SET 
                              idKH = ?
                        WHERE id = ?
                        """;
    
    String findBySdt ="""
                      
                      """;
    
    public List<HoaDon> findBySdt(String soDt){
        return XQuery.getBeanList(HoaDon.class, findBySdt, soDt);
    }
        
    public int update(HoaDon en){
        Object[] values = {
            en.getMaPGG(),
            en.getId()
        };
        return XJdbc.executeUpdate(update, values);
    }
    public int updateIdKH(HoaDon en){
        Object[] values = {
            en.getIdKH(),
            en.getId()
        };
        return XJdbc.executeUpdate(updateIdKH, values);
    }
    String findByNameKH = """
                          select hd.id, hd.idKH, hd.idNV,hd.maHoaDon,ngayTao,ngayThanhToan,hd.trangThai,hd.maPGG,hd.tongGia,hd.tongGiaSauPGG from HoaDon hd join KhachHang on KhachHang.id = hd.idKH where KhachHang.tenKH like ? ORDER BY NgayThanhToan DESC
                        """;
    String findByMaNV = """
                        select * from hoadon join NhanVien on HoaDon.idNV = NhanVien.id where maNV like ? ORDER BY NgayThanhToan DESC
                        """;
    String findByDateRangeSql = "SELECT * FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ? ORDER BY NgayThanhToan DESC" ;
    
    String findingByDateRangeSql = "SELECT * FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ? and id=? ORDER BY NgayThanhToan DESC";
    
    String checkToDelete = """
                           select * from hoadon where idKH = ?
                           """;
    String findByNameNV = """
                          select hoadon.id, idKH, idNV,maHoaDon,ngayTao, ngaythanhtoan,hoadon.trangThai,maPGG,tongGia,tenKH, soDT from hoadon join nhanvien on hoadon.idnv = nhanvien.id where NhanVien.hoTen COLLATE Latin1_General_CI_AI like ? ORDER BY NgayThanhToan DESC
                          """;
    
  
    String findByMaHD = """
                        select * from hoadon where maHoaDon like ? ORDER BY NgayThanhToan DESC
                        """;
    
    String findByTrangThai = """
                             select * from hoadon where trangthai = ?
                             """;
    
      String findSdt2 = """
                      select hoadon.id, idKH, idNV,maHoaDon,ngayTao, ngaythanhtoan,hoadon.trangThai,maPGG,tongGia,tenKH,TongGiaSauPGG from hoadon join khachhang on hoadon.idkh = khachhang.id where soDt like ? ORDER BY NgayThanhToan DESC
                     
                     """;

    public List<HoaDon> findSdt(String soDt) {
        return XQuery.getBeanList(HoaDon.class, findSdt2, "%" + soDt + "%");
    }
    
    public List<HoaDon> findByTrangThai(int trangThai){
        return XQuery.getBeanList(HoaDon.class, findByTrangThai, trangThai);
    }
    public List<HoaDon> findByMaHD(String maHD){
        return XQuery.getBeanList(HoaDon.class, findByMaHD, "%"+maHD+"%");
    }
    
    public List<HoaDon> findByNameNV(String hoTen){
        return XQuery.getBeanList(HoaDon.class, findByNameNV, "%" + hoTen+ "%");
    }
    
    public List<HoaDon> findByDateRange(java.util.Date begin, java.util.Date end) {
        return XQuery.getBeanList(HoaDon.class, findByDateRangeSql, new Date(begin.getTime()), new Date(end.getTime()));
    }
    
    public List<HoaDon> findingByDateRange(java.util.Date begin, java.util.Date end,int id) {
        return XQuery.getBeanList(HoaDon.class, findByDateRangeSql, new Date(begin.getTime()), new Date(end.getTime()),id);
    }
    public List<HoaDon> findByNameKH (String tenKH){
        return XQuery.getBeanList(HoaDon.class, findByNameKH,  "%" +tenKH+ "%");
    }
    
    public List<HoaDon> findByMaNV (String maNV){
        return XQuery.getBeanList(HoaDon.class, findByMaNV,  "%" +maNV+ "%");
    }
    public List<HoaDon> findAll() {
        return XQuery.getBeanList(HoaDon.class, findAllSql);
    }
    public HoaDon findOne(int id) {
        return XQuery.getSingleBean(HoaDon.class, findOne, id);
    }
    public List<HoaDon> findOneByAvailaible() {
        return XQuery.getBeanList(HoaDon.class, findOneByAvailaible);
    }
    public List<HoaDon> tongTien() {
        return XQuery.getBeanList(HoaDon.class, tongTien);
    }
    public int thanhToan(HoaDon en){
        Object[] values = {
           
            en.getNgayThanhToan(),
            en.getTongGia(),
            en.getTongGiaSauPGG(),
            en.getMaPGG(),
            en.getId()
        };
        return XJdbc.executeUpdate(thanhToan, values);
    }
    public HoaDon findByHoaDon(String maHD) {
        return XQuery.getSingleBean(HoaDon.class, findByMa, maHD);
    }
    
    public int create(HoaDon entity){
        Object[] values = {
            
            entity.getIdKH(),
            entity.getIdNV(),
            entity.getMaHoaDon(),
            entity.getNgayTao(),
            entity.getTrangThai(),
            entity.getMaPGG(),
            entity.getTongGia(),
            entity.getTongGiaSauPGG()
        };
        return XJdbc.executeUpdate(insert, values);
    }
    
    public HoaDon checkToDelete(int idKH){
        return XQuery.getSingleBean(HoaDon.class, checkToDelete, idKH);
    }
    
    String findByNameKHAndDateRangeSql = """
    SELECT * FROM HoaDon join khachhang on khachhang.id = hoadon.idkh
    WHERE tenKH COLLATE Latin1_General_CI_AI LIKE ? 
    AND NgayThanhToan BETWEEN ? AND ?
                                         ORDER BY NgayThanhToan DESC
    """;

public List<HoaDon> findByNameKHAndDateRange(String tenKH, java.util.Date begin, java.util.Date end) {
    return XQuery.getBeanList(HoaDon.class, findByNameKHAndDateRangeSql, 
        "%" + tenKH + "%", 
        new Date(begin.getTime()), 
        new Date(end.getTime())
    );
}


String findByMaNVAndDateRangeSql = """
    SELECT HoaDon.* FROM HoaDon 
    JOIN NhanVien ON HoaDon.idNV = NhanVien.id 
    WHERE NhanVien.maNV LIKE ? 
    AND HoaDon.NgayThanhToan BETWEEN ? AND ?
                                   ORDER BY NgayThanhToan DESC
    """;

public List<HoaDon> findByMaNVAndDateRange(String maNV, java.util.Date begin, java.util.Date end) {
    return XQuery.getBeanList(HoaDon.class, findByMaNVAndDateRangeSql, 
        "%" + maNV + "%", 
        new Date(begin.getTime()), 
        new Date(end.getTime())
    );
}

String findByMaHDAndDateRangeSql = """
    SELECT * FROM HoaDon 
    WHERE maHoaDon LIKE ? 
    AND NgayThanhToan BETWEEN ? AND ?
                                   ORDER BY NgayThanhToan DESC
    """;

public List<HoaDon> findByMaHDAndDateRange(String maHD, java.util.Date begin, java.util.Date end) {
    return XQuery.getBeanList(HoaDon.class, findByMaHDAndDateRangeSql, 
        "%" + maHD + "%", 
        new Date(begin.getTime()), 
        new Date(end.getTime())
    );
}
    
}
