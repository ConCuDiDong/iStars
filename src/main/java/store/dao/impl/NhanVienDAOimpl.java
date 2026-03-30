/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.NhanVien;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class NhanVienDAOimpl {
    String getAll = "select * from nhanvien";
    String getOne = "select * from nhanvien where id = ?";
    String findByUserName = "select * from nhanvien where maNV = ?";
    String addUser = """
                        INSERT INTO [dbo].[NhanVien]
                                   ([maNV]
                                   ,[hoTen]
                                   ,[gioiTinh]
                                   ,[ngaySinh]
                                   ,[sdt]
                                   ,[matKhau]
                                   ,[chucVu]
                                   ,[trangThai]
                                   ,[Email])
                             VALUES
                                   (?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;
    
    
    String delUser = """
                     DELETE FROM [dbo].[NhanVien]
                           WHERE maNV = ?
                     """;
    
    String updateSql = """
                       UPDATE [dbo].[NhanVien]
                          SET [maNV] = ?
                             ,[hoTen] = ?
                             ,[gioiTinh] = ?
                             ,[ngaySinh] = ?
                             ,[sdt] = ?
                             ,[matKhau] = ?
                             ,[chucVu] = ?
                             ,[trangThai] = ?
                             ,[Email] = ?
                        WHERE id = ?
                       """;
    String search = """
                    select * from nhanvien where maNV = ?
                    """;
    
    String findList = """
                      SELECT * FROM nhanvien
                          WHERE maNV LIKE '%' + ? + '%'
                      """;
    
    String findName = """
                      SELECT * FROM nhanvien
                          WHERE hoTen LIKE '%' + ? + '%'
                      """;
    
    String findEmail = """
                      SELECT * FROM nhanvien
                          WHERE Email LIKE '%' + ? + '%'
                      """;
    
    String findSDT = """
                      SELECT * FROM nhanvien
                          WHERE sdt LIKE '%' + ? + '%'
                      """;
    
    public List<NhanVien> findAll() {
        return XQuery.getBeanList(NhanVien.class, getAll);
    }
    
    public NhanVien findByMa(String ma){
        return XQuery.getSingleBean(NhanVien.class, findByUserName, ma);
    }
    
    public NhanVien findOne(int id){
        return XQuery.getSingleBean(NhanVien.class, getOne, id);
    }
    public NhanVien search(String ma){
        return XQuery.getSingleBean(NhanVien.class, search , ma);
    }
    
    public List<NhanVien> findList(String ma) {
        return XQuery.getBeanList(NhanVien.class, findList, ma);
    }
    
    public List<NhanVien> findByName(String ma) {
        return XQuery.getBeanList(NhanVien.class, findName, ma);
    }
    
    public List<NhanVien> findBySDT(String ma) {
        return XQuery.getBeanList(NhanVien.class, findSDT, ma);
    }
    
    public List<NhanVien> findByEmail(String ma) {
        return XQuery.getBeanList(NhanVien.class, findEmail, ma);
    }
    
    public int create(NhanVien entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getHoTen(),
            entity.getGioiTinh(),
            entity.getNgaySinh(),
            entity.getSdt(),
            entity.getMatKhau(),
            entity.getChucVu(),
            entity.getTrangThai(),
            entity.getEmail()
        };
        return XJdbc.executeUpdate(addUser, values);
    }
    
    public int delete(String ma) {
        return XJdbc.executeUpdate(delUser, ma);
    }
    
    public int update(NhanVien entity) {
        Object[] values = {
            entity.getMaNV(),
            entity.getHoTen(),
            entity.getGioiTinh(),
            entity.getNgaySinh(),
            entity.getSdt(),
            entity.getMatKhau(),
            entity.getChucVu(),
            entity.getTrangThai(),
            entity.getEmail(),
            entity.getId()
        };
        return XJdbc.executeUpdate(updateSql, values);
    }

     public int updateMatKhau(String maNV, String matKhauMoi) {
        String sql = "UPDATE NhanVien SET matKhau = ? WHERE maNV = ?";
        return XJdbc.executeUpdate(sql, matKhauMoi, maNV);
    }
}
