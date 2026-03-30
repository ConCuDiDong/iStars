/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.MauSac;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class MauSacDAOimpl {
    String getAll = "select * from MauSac";
        String getOne = "select * from MauSac where id = ?";

   String create = """
                   INSERT INTO [dbo].[MauSac]
                              ([maMau]
                              ,[ten])
                        VALUES
                              (?, ?)
                   """;
   public int create(MauSac en){
        Object[] value = {
            en.getMaMau(),
            en.getTen()
        };
        return XJdbc.executeUpdate(create, value);
    }
   String delete =  """
                  DELETE FROM [dbo].[MauSac]
                        WHERE id = ?
                  """;
    public int delete(int id){
        return XJdbc.executeUpdate(delete, id);
    }
    public List<MauSac> findAll() {
        return XQuery.getBeanList(MauSac.class, getAll);
    }
    
    public MauSac findOne(int id){
        return XQuery.getSingleBean(MauSac.class, getOne, id);
    }

}
