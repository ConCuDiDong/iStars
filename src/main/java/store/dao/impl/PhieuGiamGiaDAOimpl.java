    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.PhieuGiamGia;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class PhieuGiamGiaDAOimpl {
    String selectSql = "select * from Phieugiamgia";
    String findAll2 ="""
                    select * from PhieuGiamGia where trangThai = 1
                    """;
    public List<PhieuGiamGia> findAll2() {
        return XQuery.getBeanList(PhieuGiamGia.class, findAll2); 
    }
   String selectOneSql = "select * from Phieugiamgia where id = ?";
    public List<PhieuGiamGia> findAll() {
        return XQuery.getBeanList(PhieuGiamGia.class, selectSql);    }
    
public PhieuGiamGia findOne(int id) {
        return XQuery.getSingleBean(PhieuGiamGia.class, selectOneSql, id);    }

        String findName = """
                          select * from phieugiamgia where ten = ?;
                          """;
    
        public List<PhieuGiamGia> findName(String ten){
            return XQuery.getBeanList(PhieuGiamGia.class, findName, ten);
        }
        String createSql = """
                           INSERT INTO [dbo].[PhieuGiamGia]
                                      ([maPhieu]
                                      ,[ten]
                                      ,[loai]
                                      ,[giaTriGiam]
                                      ,[giamToiDa]
                                      ,[dieuKienAD]
                                      ,[soLuong]
                                      ,[trangThai]
                                      ,[ngayTao]
                                      ,[ngayHetHan])
                                VALUES
                                    (?,?,?,?,?,?,?,?,?,?)
                           """;
        
        String UpdateSql = """
                           UPDATE [dbo].[PhieuGiamGia]
                              SET [maPhieu] = ?
                                 ,[ten] = ?
                                 ,[loai] = ?
                                 ,[giaTriGiam] = ?
                                 ,[giamToiDa] = ?
                                 ,[dieuKienAD] = ?
                                 ,[soLuong] = ?
                                 ,[trangThai] = ?
                                 ,[ngayTao] = ?
                                 ,[ngayHetHan] = ?
                            WHERE	id =?
                              """;
        
        String DeleteSql ="""
                          delete from PhieuGiamGia where id =?
                          
                          """;
        
        String CheckTrung ="""
                           select * from phieugiamgia where maphieu =?
                           """;
        
        String FindById ="""
                         select* from phieugiamgia where id =?
                         """;
        String loaiVoucher ="""
                            select distinct *  from PhieuGiamGia
                            """;
        String truId1 = """
                        SELECT *
                        FROM PhieuGiamGia
                        WHERE id != 1;
                        """;
        String dkAD = """
                      SELECT pgg.*
                      FROM (
                          SELECT 
                              hd.id,
                              ISNULL(SUM(hdct.gia), 0) AS tongGia
                          FROM HoaDon hd
                          LEFT JOIN HoaDonChiTiet hdct 
                              ON hd.id = hdct.idHoaDon
                          WHERE hd.trangThai = 0
                          GROUP BY hd.id
                      ) AS HD_Tong
                      JOIN PhieuGiamGia pgg 
                          ON HD_Tong.tongGia >= pgg.dieuKienAD
                          AND CAST(GETDATE() AS date) BETWEEN pgg.ngayTao AND pgg.ngayHetHan;
                      """;
        
        String findByTrangThai="""
                                select * from phieugiamgia where trangThai = ?
                                """;
        
        public List<PhieuGiamGia> FindByTrangThai(int trangThai){
            return XQuery.getBeanList(PhieuGiamGia.class, findByTrangThai, trangThai);
        }
        public List<PhieuGiamGia> dkAD(){
            return XQuery.getBeanList(PhieuGiamGia.class, dkAD);
        }
        public List<PhieuGiamGia> loaiVoucher(){
            return XQuery.getBeanList(PhieuGiamGia.class, loaiVoucher);
        }
        public List<PhieuGiamGia> loaiBoId1(){
            return XQuery.getBeanList(PhieuGiamGia.class, truId1);
        }
        public int create(PhieuGiamGia entity){
            Object[] values ={
                entity.getMaPhieu(),
                entity.getTen(),
                entity.getLoai(),
                entity.getGiaTriGiam(),
                entity.getGiamToiDa(),
                entity.getDieuKienAD(),
                entity.getSoLuong(),
                entity.getTrangThai(),
                entity.getNgayTao(),
                entity.getNgayHetHan()
                
            };
            return XJdbc.executeUpdate(createSql, values);
        }
       
        public int update(PhieuGiamGia entity){
            Object[] values={
                entity.getMaPhieu(),
                entity.getTen(),
                entity.getLoai(),
                entity.getGiaTriGiam(),
                entity.getGiamToiDa(),
                entity.getDieuKienAD(),
                entity.getSoLuong(),
                entity.getTrangThai(),
                entity.getNgayTao(),
                entity.getNgayHetHan(),
                entity.getId()
            };
            return XJdbc.executeUpdate(UpdateSql, values);
        }
        
        public PhieuGiamGia checkTrung(String maPhieu){
            return XQuery.getSingleBean(PhieuGiamGia.class, CheckTrung, maPhieu);
        }
        
        public PhieuGiamGia findById(int id){
            return XQuery.getSingleBean(PhieuGiamGia.class, FindById, id);
        }
        
        String updateTrangThaiAuto = """
    UPDATE PhieuGiamGia
    SET TrangThai = CASE 
        WHEN CAST(GETDATE() AS DATE) BETWEEN ngayTao AND ngayHetHan THEN 1
        WHEN CAST(GETDATE() AS DATE) > ngayHetHan THEN 0
        WHEN CAST(GETDATE() AS DATE) < ngayTao THEN 2
    END
""";

public int updateTrangThaiAuto() {
    return XJdbc.executeUpdate(updateTrangThaiAuto);
}
    ;}
