/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.Pin;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class PinDAOimpl {
    String getAll = "select * from Pin";
        String getOne = "select * from Pin where id = ?";
    public List<Pin> findAll() {
return XQuery.getBeanList(Pin.class, getAll); 
    
    }
    
    public Pin findOne(int id){
        return XQuery.getSingleBean(Pin.class, getOne, id);
    }

  String create = """
                        INSERT INTO [dbo].[Pin]
                                   ([dungLuong])
                             VALUES
                                   (?)
                        """;
        public int create(Pin en){
        Object[] value = {
            en.getDungLuong()
        };
        return XJdbc.executeUpdate(create, value);
    }
        String delete =  """
                  DELETE FROM [dbo].[Pin]
                        WHERE id = ?
                  """;
    public int delete(int id){
        return XJdbc.executeUpdate(delete, id);
    }
}
