/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.ImeiDaBan;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class ImeiDaBanDAOimpl  {
    String selectSql = "select * from imeiDaBan";
    
    String findOne ="select maImei from imeiDaBan where idHoaDonCt = ?";
    
    String insert = """
                    INSERT INTO [dbo].[ImeiDaBan]
                               ([idHoaDonCT]
                               ,[maImei])
                         VALUES
                               (?, ?)
                    """;
    
    String delete = """
                    DELETE FROM [dbo].[ImeiDaBan]
                          WHERE idHoaDonCT = ?
                    """;
      String delete2 = """
                    DELETE FROM [dbo].[ImeiDaBan]
                          WHERE maImei = ?
                    """;
      
      public List<ImeiDaBan> findByHdct(int idHoaDonCT) {
    String sql = "SELECT * FROM ImeiDaBan WHERE idHoaDonCT = ?";
    return XQuery.getBeanList(ImeiDaBan.class, sql, idHoaDonCT);
}
      
    public List<ImeiDaBan> findAll() {
        return XQuery.getBeanList(ImeiDaBan.class, selectSql);
    }

    public List<ImeiDaBan> findOne(int idHoaDonCT){
        return XQuery.getBeanList(ImeiDaBan.class, findOne, idHoaDonCT);
    }
    
    public ImeiDaBan findOne2(int idHoaDonCT){
        return XQuery.getSingleBean(ImeiDaBan.class, findOne, idHoaDonCT);
    }
    
    
    public int create(ImeiDaBan en){
        Object[] value = {
        en.getIdHoaDonCT(),
            en.getMaImei()
    };
        return XJdbc.executeUpdate(insert, value);
    }
    public int delete(int idHDCT){
        return XJdbc.executeUpdate(delete, idHDCT);
    }
    public int delete2(String maImei){
        return XJdbc.executeUpdate(delete2, maImei);
    }
}
