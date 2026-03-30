/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao.impl;

import java.util.List;
import store.entity.XuatXu;
import store.util.XJdbc;
import store.util.XQuery;

/**
 *
 * @author LEGION
 */
public class XuatXuDAOimpl {
    String getAll = "select * from XuatXu";
        String getOne = "select * from XuatXu where id = ?";
   
    String create = """
                    INSERT INTO [dbo].[XuatXu]
                               ([noiSanXuat])
                         VALUES
                               (?)
                    """;
    String delete =  """
                  DELETE FROM [dbo].[XuatXu]
                        WHERE id = ?
                  """;
    public int delete(int id){
        return XJdbc.executeUpdate(delete, id);
    }
    public int create(XuatXu en){
        Object[] value = {
            en.getNoiSanXuat()
        };
        return XJdbc.executeUpdate(create, value);
    }
    public List<XuatXu> findAll() {
        return XQuery.getBeanList(XuatXu.class, getAll);
    }
    
    public XuatXu findOne(int id){
        return XQuery.getSingleBean(XuatXu.class, getOne, id);
    }

}
