/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.entity;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PhieuGiamGia {
    private int id;
    private String maPhieu;
    private String ten;
    private int loai;
    private BigDecimal giaTriGiam;
    private BigDecimal giamToiDa;
    private BigDecimal dieuKienAD;
    private int soLuong ;
    private int trangThai;
    private Date ngayTao;
    private Date ngayHetHan;

    @Override
    public String toString() {
        if (this.giaTriGiam == null) {
        return this.ten;
    }
        String loaiStr = (this.getLoai() == 1) ? "%" : "đ";
        return this.ten + " Giảm: " + this.giaTriGiam +" "+ loaiStr   ;
    }
    
    
}
