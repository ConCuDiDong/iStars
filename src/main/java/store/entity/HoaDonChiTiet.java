/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.entity;

import java.math.BigDecimal;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class HoaDonChiTiet {
    private int id;
    private int idSanPham;
    private int idHoaDon;
    private BigDecimal gia;
    private int soLuong;
    private int trangThai;
}
