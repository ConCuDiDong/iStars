/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.HoaDon;
import store.entity.KhachHang;
import store.entity.NhanVien;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class KhachHangDAOimpl {

    String selectSql = """
    SELECT id, maKH, gioiTinh as gioiTinh, diaChi, email, TrangThai, tenKH, soDT 
    FROM khachhang
    """;
    
    String findAllByStatus = """
                             select *from khachhang where trangthai = 1
                             """;

String InsertSql = "INSERT INTO [dbo].[KhachHang]\n"
        + "           ([maKH]\n"
        + "           ,[gioiTinh]\n"
        + "           ,[diaChi]\n"
        + "           ,[email]\n"
        + "           ,[TrangThai]\n"
        + "           ,[tenKH]\n"
        + "           ,[soDT])\n"
        + "     VALUES\n"
        + "           (?,?,?,?,?,?,?)";

    String UpdateSql = """
    UPDATE [dbo].[KhachHang]
       SET [maKH] = ?
          ,[gioiTinh] = ?
          ,[diaChi] = ?
          ,[email] = ?
          ,[TrangThai] = ?
          ,[tenKH] = ?
          ,[soDT] = ?
     WHERE id = ?
    """;

    String DeleteSql = "DELETE FROM [dbo].[KhachHang]   WHERE id=?";

    String CheckTrungMaKH = """
                       select maKH from khachhang where maKH = ?
                       """;

    String FindByMaKH = """
                       select * from khachhang where maKh like ?
                       """;

    String FindByEmail = """
                       select * from khachhang where email like ?
                       """;

    
    String FindOne = """
    SELECT id, maKH, gioiTinh as gioiTinh, diaChi, email, TrangThai, tenKH, soDT 
    FROM khachhang 
    WHERE id = ?
    """;

    String FindByName = """
                        select * from khachhang where tenKH like ?
                        """;
    String FindBySdt = """
                        select * from khachhang where soDT like ?
                        """;
    
  
    
    public List<KhachHang> findBySdt(String soDt) {
        return XQuery.getBeanList(KhachHang.class, FindBySdt, "%" + soDt + "%");
    }
     
    public List<KhachHang> findByName(String tenKH) {
        return XQuery.getBeanList(KhachHang.class, FindByName, "%" + tenKH + "%");
    }

    public List<KhachHang> findAll() {
        return XQuery.getBeanList(KhachHang.class, selectSql);
    }
    
     public List<KhachHang> findAllByTrangThai() {
        return XQuery.getBeanList(KhachHang.class, findAllByStatus);
    }

    public KhachHang findOne(int id) {
        return XQuery.getSingleBean(KhachHang.class, FindOne, id);
    }

    public int create(KhachHang kh) {
        Object[] row = {
            kh.getMaKH(),
            
            kh.getGioiTinh(),
            kh.getDiaChi(),
            kh.getEmail(),
            kh.getTrangThai(),
            kh.getTenKH(),
            kh.getSoDT()
        };
        return XJdbc.executeUpdate(InsertSql, row);
    }

    public int update(KhachHang kh) {
        Object[] row = {
            kh.getMaKH(),
            
            kh.getGioiTinh(),
            kh.getDiaChi(),
            kh.getEmail(),
            kh.getTrangThai(),
            
            kh.getTenKH(),
            kh.getSoDT(),
            kh.getId()    
        };

        return XJdbc.executeUpdate(UpdateSql, row);
    }

    public int delete(int id) {
        return XJdbc.executeUpdate(DeleteSql, id);
    }

    public List<KhachHang> CheckTrungMaKH(String maKH) {
        return XQuery.getBeanList(KhachHang.class, CheckTrungMaKH, maKH);
    }

    public List<KhachHang> FindByMaKH(String maKH) {
        return XQuery.getBeanList(KhachHang.class, FindByMaKH, "%" + maKH + "%");
    }

    public List<KhachHang> FindByEmail(String email) {
        return XQuery.getBeanList(KhachHang.class, FindByEmail, "%" + email + "%");
    }
}
