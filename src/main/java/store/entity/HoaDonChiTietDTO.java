/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.entity;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LEGION
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTietDTO {
    private int id;
    private String maHoaDon;
    private String tenSanPham;
    private BigDecimal donGia;
    private String kichThuoc;
    private String mauSac;   
    private String ghiChu;          // ghiChu
    private Date ngayThanhToan;
    private int idSanPham;
    private String noiSanXuat;
    private String dungLuong;

}
