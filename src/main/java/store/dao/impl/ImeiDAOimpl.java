/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import store.entity.Imei;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class ImeiDAOimpl {
    public int countImeiActiveByCTSP(int idCTSP) {
    String sql = "SELECT COUNT(*) FROM Imei WHERE idCTSP = ? AND trangThai = 1";
    try (
        Connection con = XJdbc.openConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setInt(1, idCTSP);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    String getAll = "select * from Imei";
        String getOne = "select * from Imei where id = ?";
         String getBTT = "select * from Imei where trangThai = 1 and idCTSP = ?";
         String getBTT2 = "select * from Imei where trangThai = 1 ";
         String getByIdctsp = "select * from Imei where idCTSP = ? order by trangThai desc";
         String grtIdbyHDCT = "select * from Imei where idHoaDonCt = ?";
   String insert = """
                   INSERT INTO [dbo].[Imei]
                              ([maImei]
                              ,[trangThai]
                              ,[idCTSP])
                        VALUES
                              (?,?,?)
                   """;
   String update = """
                   UPDATE [dbo].[Imei]
                      SET [maImei] = ?
                         ,[trangThai] = ?
                         ,[idCTSP] =  ?
                    WHERE id = ?
                   """;
   String available = """
                      UPDATE Imei
                      SET trangThai = 0
                      WHERE id = ?
                      """;
   String available1 = """
                      UPDATE Imei
                      SET trangThai = 1
                      WHERE maImei = ?
                      """;
   String available2 = """
                      UPDATE im
                      SET im.trangThai = 1
                      FROM Imei AS im
                      JOIN ImeiDaBan AS imdb ON im.maImei = imdb.maImei
                      WHERE imdb.idHoaDonCT = ?
                      """;
 
   String delete = """
                   DELETE FROM [dbo].[Imei]
                         WHERE id = ?
                   """;
    String search = """
                    select * from Imei where idCTSP = ?
                    """;     
    String soLuongImeiTheoCTSP = """
                                 SELECT COUNT(*) AS soLuong
                                 FROM Imei
                                 WHERE idCTSP = ? and trangThai = 1
                                 """;  
    String xoaToanBo = """
                       DELETE FROM Imei
                       WHERE idCTSP = ?
                       """;
    public int deleteAllImeiFromIdCTSP(int idCTSP){
        return XJdbc.executeUpdate(xoaToanBo, idCTSP);
    }
    public Imei soLuongImeiTheoCTSP(int idCTSP) {
        return XQuery.getSingleBean(Imei.class, soLuongImeiTheoCTSP, idCTSP);
    }
    public List<Imei> findAll() {
        return XQuery.getBeanList(Imei.class, getAll);
    }
    public List<Imei> search(int idCTSP) {
        return XQuery.getBeanList(Imei.class, search, idCTSP);
    }
    public List<Imei> FindByIDHDCT(int id) {
        return XQuery.getBeanList(Imei.class, grtIdbyHDCT, id);
    }
    public List<Imei> FindByTT(int idCTSP) {
        return XQuery.getBeanList(Imei.class, getBTT, idCTSP);
    }
    public List<Imei> FindALLByCTSP(int idCTSP) {
        return XQuery.getBeanList(Imei.class, getByIdctsp, idCTSP);
    }
    public List<Imei> FindByTT2() {
        return XQuery.getBeanList(Imei.class, getBTT2);
    }
    public Imei findOne(int id){
        return XQuery.getSingleBean(Imei.class, getOne, id);
    }
    public int create(Imei entity){
        Object[] value = {
            entity.getMaImei(),
            entity.getTrangThai(),
            entity.getIdCTSP()
        };
        return XJdbc.executeUpdate(insert, value);
    }
    public int delete(int id){
        return XJdbc.executeUpdate(delete, id);
    }
    public int avilable(int id){
        return XJdbc.executeUpdate(available, id);
    }
    public int avilable1(String maIemi){
        return XJdbc.executeUpdate(available1, maIemi);
    }
    
      public int avilable2(int idHDCT){
        return XJdbc.executeUpdate(available2, idHDCT);
      }
    public int update (Imei entity){
        Object[] value = {
            entity.getMaImei(),
            entity.getTrangThai(),
            entity.getIdCTSP(),
            entity.getId()
        };
        return XJdbc.executeUpdate(update, value);
    }
    //    private int id;
//    private String maImei;
//    private int trangThai;
//    private int idCTSP;
    
   public String getNextImei() {
    String sql = "SELECT MAX(maImei) FROM Imei";
    try {
        String lastImei = XJdbc.getValue(sql);
        if (lastImei != null) {
            BigInteger imeiNum = new BigInteger(lastImei);
            imeiNum = imeiNum.add(BigInteger.ONE);
            return imeiNum.toString();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "100000000000000"; // imei mặc định nếu chưa có
}
   


}
