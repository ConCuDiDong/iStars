/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.Rom;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class RomDAOimpl{

    String getAll = "select * from Rom";
        String getOne = "select * from Rom where id = ?";
        String create = """
                        INSERT INTO [dbo].[Rom]
                                   ([dungLuong])
                             VALUES
                                   (?)
                        """;
        public int create(Rom en){
        Object[] value = {
            en.getDungLuong()
        };
        return XJdbc.executeUpdate(create, value);
    }
        String delete =  """
                  DELETE FROM [dbo].[Rom]
                        WHERE id = ?
                  """;
    public int delete(int id){
        return XJdbc.executeUpdate(delete, id);
    }
    public List<Rom> findAll() {
        return XQuery.getBeanList(Rom.class, getAll);
    }
    
    public Rom findOne(int id){
        return XQuery.getSingleBean(Rom.class, getOne, id);
    }

  
}
