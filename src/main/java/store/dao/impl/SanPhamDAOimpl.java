/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.SanPham;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class SanPhamDAOimpl {

    String getAll = """
                    SELECT 
                        sp.id,
                        sp.ma,
                        sp.ten,
                        sp.idXX,
                        sp.idPin,
                        sp.chieuDai,
                        sp.chieuRong,
                        sp.doDay,
                        COUNT(ctsp.id) AS soLuong
                    FROM SanPham sp
                    LEFT JOIN ChiTietSanPham ctsp 
                        ON sp.id = ctsp.idSP
                    GROUP BY 
                        sp.id,
                        sp.ma,
                        sp.ten,
                        sp.idXX,
                        sp.idPin,
                        sp.chieuDai,
                        sp.chieuRong,
                        sp.doDay;
                    """;
    String getOne = "select * from SanPham where id = ?";
    String create = """
    INSERT INTO [dbo].[SanPham]
           ([ma]
           ,[ten]
           ,[idXX]
           ,[idPin]
           
           ,[chieuDai]
           ,[chieuRong]
           ,[doDay]
                    ,[soLuong])
     VALUES (?, ?, ?, ?, ?, ?, ?, ?)
""";
    String delete = """
                       DELETE FROM [dbo].[SanPham]
                             WHERE id = ?
                       """;
    String update = """
    UPDATE [dbo].[SanPham]
       SET [ma] = ?
          ,[ten] = ?
          ,[idXX] = ?
          ,[idPin] = ?
          ,[chieuDai] = ?
          ,[chieuRong] = ?
          ,[doDay] = ?

     WHERE id = ?
""";
    String updateSOLUONGSP = """
    UPDATE [dbo].[SanPham]
       SET [soLuong] = ?
     WHERE id = ?
""";
    public int updateSLsp(SanPham entity) {
        Object[] values = {
            entity.getSoLuong(),
            entity.getId()
        };
        return XJdbc.executeUpdate(updateSOLUONGSP, values);

    }
    String search = "select * from SanPham where ma like ?";

    public List<SanPham> findAll() {
        return XQuery.getBeanList(SanPham.class, getAll);
    }

    public SanPham findOne(int id) {
        return XQuery.getSingleBean(SanPham.class, getOne, id);
    }

    public List<SanPham> search(String ma) {
        return XQuery.getBeanList(SanPham.class, search, ma);
    }

    public int create(SanPham entity) {
        Object[] values = {
            entity.getMa(),
            entity.getTen(),
            entity.getIdXX(),
            entity.getIdPin(),
            entity.getChieuDai(),
            entity.getChieuRong(),
            entity.getDoDay(),
            entity.getSoLuong()
        };
        return XJdbc.executeUpdate(create, values);

    }

    public int delete(int id) {
        return XJdbc.executeUpdate(delete, id);
    }

    public int update(SanPham entity) {
        Object[] values = {
            entity.getMa(),
            entity.getTen(),
            entity.getIdXX(),
            entity.getIdPin(),
            entity.getChieuDai(),
            entity.getChieuRong(),
            entity.getDoDay(),
            entity.getId()
        };
        return XJdbc.executeUpdate(update, values);

    }

}
