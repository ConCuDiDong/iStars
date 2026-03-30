package store.dao.impl;

import java.util.List;
import store.entity.ChiTietSanPham;
import store.entity.HoaDonChiTietDTO;
import store.util.XJdbc;
import store.util.XQuery;

public class ChiTietSanPhamDAOImpl {

    String changeAvailable = """
                            UPDATE ChiTietSanPham
                             SET trangThai = 0
                             WHERE id = ?
                               AND (
                                 NOT EXISTS (
                                   SELECT 1 FROM Imei WHERE Imei.idCTSP = ChiTietSanPham.id
                                 )
                                 OR NOT EXISTS (
                                   SELECT 1 FROM Imei 
                                   WHERE Imei.idCTSP = ChiTietSanPham.id AND Imei.trangThai = 1
                                 )
                               )
                               AND trangThai != 0;
                             """;

    String changeAvL2 = """
                        UPDATE ChiTietSanPham
                        SET trangThai = 1
                        WHERE id = ?
                          AND EXISTS (
                              SELECT 1 FROM Imei 
                              WHERE Imei.idCTSP = ChiTietSanPham.id 
                                AND Imei.trangThai = 1
                          )
                          AND trangThai != 1;
                        """;
    String changeAvailableByGhiChu = """
    UPDATE ChiTietSanPham
    SET trangThai = 0
    WHERE ghiChu like ?
      AND (
        NOT EXISTS (
          SELECT 1 FROM Imei WHERE Imei.idCTSP = ChiTietSanPham.id
        )
        OR NOT EXISTS (
          SELECT 1 FROM Imei 
          WHERE Imei.idCTSP = ChiTietSanPham.id AND Imei.trangThai = 1
        )
      )
      AND trangThai != 0;
""";
    String changeAvL2ByGhiChu = """
    UPDATE ChiTietSanPham
    SET trangThai = 1
    WHERE ghiChu like ?
      AND EXISTS (
        SELECT 1 FROM Imei 
        WHERE Imei.idCTSP = ChiTietSanPham.id 
          AND Imei.trangThai = 1
      )
      AND trangThai != 1;
""";
    String getAll = """
SELECT 
    *
FROM ChiTietSanPham
""";
    String getOne = """
SELECT 
    *
FROM ChiTietSanPham where id = ?
""";

    String getAllByIDsp = """
                         	select * from  ChiTietSanPham WHERE idSP = ? 
                          """;
    String getOneByIDsp = """
SELECT top 1
    *
FROM ChiTietSanPham where idSP = ?
""";
    String getOneByAV = """
                        SELECT 
                            ctsp.id,
                            ctsp.idSP,
                            ctsp.idRom,
                            ctsp.idMau,
                            ctsp.gia,
                            ctsp.ghiChu,
                            ctsp.trangThai,
                            COUNT(CASE WHEN i.trangThai = 1 THEN 1 END) AS soLuong
                        FROM ChiTietSanPham ctsp
                        LEFT JOIN Imei i 
                            ON ctsp.id = i.idCTSP
                        WHERE ctsp.trangThai = 1
                        GROUP BY 
                            ctsp.id,
                            ctsp.idSP,
                            ctsp.idRom,
                            ctsp.idMau,
                            ctsp.gia,
                            ctsp.ghiChu,
                            ctsp.trangThai;
                        """;
    String create = """
                    INSERT INTO [dbo].[ChiTietSanPham]
                               (
                                [idSP]
                               ,[idRom]
                               ,[idMau]
                               ,[gia]
                               ,[ghiChu]
                               ,[trangThai]
                    ,[soLuong])
                         VALUES
                               (?, ?, ?, ?, ?,?,?)
                    """;
    String delete = """
                    DELETE FROM [dbo].[ChiTietSanPham]
                          WHERE id = ?
                    """;
    String update = """
                    UPDATE [dbo].[ChiTietSanPham]
                       SET 
                          [idSP] = ?
                          ,[idRom] = ?
                          ,[idMau] = ?
                          ,[gia] = ?
                          ,[ghiChu] = ?
                          ,[trangThai] = ?
                     WHERE id = ?
                    """;
    String updateSL = """
                    UPDATE [dbo].[ChiTietSanPham]
                       SET 
                          [soLuong] = ?
                     WHERE id = ?
                    """;
    String search = """
                    SELECT TOP (1000) ct.[id],
                           ct.[idSP],
                           ct.[idRom],
                           ct.[idMau],
                           ct.[gia],
                           ct.[ghiChu],
                           ct.[trangThai]
                    FROM [iStars].[dbo].[ChiTietSanPham] AS ct
                         JOIN SanPham AS sp ON sp.id = ct.idSP
                    WHERE sp.ma LIKE ?
                    """;

    public int updateSL(ChiTietSanPham entity) {
        Object[] value = {
            entity.getSoLuong(),
            entity.getId()
        };
        return XJdbc.executeUpdate(updateSL, value);
    }

    public List<ChiTietSanPham> findAll() {
        return XQuery.getBeanList(ChiTietSanPham.class, getAll);
    }

    public List<ChiTietSanPham> findAllByidSP(int idsp) {
        return XQuery.getBeanList(ChiTietSanPham.class, getAllByIDsp, idsp);
    }

    public ChiTietSanPham findOneByidSP(int idsp) {
        return XQuery.getSingleBean(ChiTietSanPham.class, getOneByIDsp, idsp);
    }

    public int changeAvailable(int id) {
        return XJdbc.executeUpdate(changeAvailable, id);
    }

    public int changeAvailable2(int id) {
        return XJdbc.executeUpdate(changeAvL2, id);
    }

    public int changeAvailable3(String ghiChu) {
        return XJdbc.executeUpdate(changeAvailableByGhiChu, ghiChu);
    }

    public int changeAvailable2_4(String ghiChu) {
        return XJdbc.executeUpdate(changeAvL2ByGhiChu, ghiChu);
    }

    public List<ChiTietSanPham> findOneByAV() {
        return XQuery.getBeanList(ChiTietSanPham.class, getOneByAV);
    }

    public ChiTietSanPham findOne(int id) {
        return XQuery.getSingleBean(ChiTietSanPham.class, getOne, id);
    }

    public List<ChiTietSanPham> search(String tenSP) {
        return XQuery.getBeanList(ChiTietSanPham.class, search, tenSP);
    }

    public int create(ChiTietSanPham entity) {
        Object[] value = {
            entity.getIdSP(),
            entity.getIdRom(),
            entity.getIdMau(),
            entity.getGia(),
            entity.getGhiChu(),
            entity.getTrangThai(),
            entity.getSoLuong()
        };
        return XJdbc.executeUpdate(create, value);
    }

    public int delete(int id) {
        return XJdbc.executeUpdate(delete, id);
    }

    public int update(ChiTietSanPham entity) {
        Object[] value = {
            entity.getIdSP(),
            entity.getIdRom(),
            entity.getIdMau(),
            entity.getGia(),
            entity.getGhiChu(),
            entity.getTrangThai(),
            entity.getId()
        };
        return XJdbc.executeUpdate(update, value);
    }

}
