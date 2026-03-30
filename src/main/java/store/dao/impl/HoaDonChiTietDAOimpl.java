/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Lichdctses/lichdctse-default.txt to change this lichdctse
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import store.entity.HoaDonChiTiet;
import store.entity.HoaDonChiTietDTO;

import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class HoaDonChiTietDAOimpl {
    String findAllSql = "select * from Hoadonchitiet";
    String findOne = "select * from Hoadonchitiet WHERE id = ?";
    String findAllByIdHD = """
                           select * from Hoadonchitiet WHERE idHoaDon = ?
                           """;
    public BigDecimal tinhTongTienTheoHoaDon(int idHoaDon) {
    String sql = "SELECT ISNULL(SUM(gia), 0) FROM HoaDonChiTiet WHERE idHoaDon = ?";
    try (Connection conn = XJdbc.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBigDecimal(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return BigDecimal.ZERO;
}
    String insert = """
                    INSERT INTO [dbo].[HoaDonChiTiet]
                               ([idSanPham]
                               ,[idHoaDon]
                               ,[gia]
                               ,[soLuong]
                               ,[trangThai])
                               
                         VALUES (?, ?, ?, ?, ?)
                    """;
    
    String delete = """
                    DELETE FROM [dbo].[HoaDonChiTiet]
                          WHERE id = ?
                    """;
    String delete2 = """
                    DELETE FROM HoaDonChiTiet
                        WHERE id = ?
                          AND NOT EXISTS (
                              SELECT 1
                              FROM ImeiDaBan imei
                              WHERE imei.idHoaDonCT = HoaDonChiTiet.id
                          );
                    """;
  public int delete2(int idhdct){
         return XJdbc.executeUpdate(delete2, idhdct);
     }
    String update = """
                    UPDATE [dbo].[HoaDonChiTiet]
                       SET  [soLuong] = ?
                          
                     WHERE id = ?
                    """;
    String update2 = """
                    UPDATE [dbo].[HoaDonChiTiet]
                       SET  [soLuong] = ?,
                     [gia] = ?
                          
                     WHERE id = ?
                    """;
    public int update2(HoaDonChiTiet hdct){ 
        Object[] values = {
            hdct.getSoLuong(),
            hdct.getGia(),
            hdct.getId()
        };
        return XJdbc.executeUpdate(update2, values);
    }
    String findByIdHoaDonAndIdSP = """
                                   SELECT * 
                                   FROM HoaDonChiTiet 
                                   WHERE idHoaDon = ? AND idSanPham = ?
                                   """;
    public HoaDonChiTiet findByIdHoaDonAndIdSP(int idHoaDon, int idSanPham) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, findByIdHoaDonAndIdSP, idHoaDon , idSanPham);
    }
     public HoaDonChiTiet findOne(int id) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, findOne, id);
    }
    public int update(HoaDonChiTiet hdct){ 
        Object[] values = {
            hdct.getSoLuong(),
            hdct.getId()
        };
        return XJdbc.executeUpdate(update, values);
    }
    public List<HoaDonChiTiet> findAll() {
        return XQuery.getBeanList(HoaDonChiTiet.class, findAllSql);
    }
     public List<HoaDonChiTiet> findAllByidHD(int idHD) {
        return XQuery.getBeanList(HoaDonChiTiet.class, findAllByIdHD, idHD);
    }
    
     public HoaDonChiTiet findAllByidHD2(int idHD) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, findAllByIdHD, idHD);
    }
     public int delete(int id){
         return XJdbc.executeUpdate(delete, id);
     }
     public int create(HoaDonChiTiet hdct) {
        
        return XJdbc.executeInsertAndReturnId(
            insert,
            hdct.getIdSanPham(),
             hdct.getIdHoaDon(),
             hdct.getGia(),
             hdct.getSoLuong(),
             hdct.getTrangThai()
        );
    }
}
