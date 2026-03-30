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

public class HoaDon {
    private int id;
    private int idKH;
    private int idNV;
    private String maHoaDon;
    private Date ngayTao;
    private Date ngayThanhToan;
    private int trangThai;
    private int maPGG;
    private BigDecimal tongGia;
    private BigDecimal tongGiaSauPGG;
}
