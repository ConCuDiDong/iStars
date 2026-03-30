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

public class ChiTietSanPham {
    private int id;
    private int idSP;
    private int idRom;
    private int idMau;
    private BigDecimal gia;
    private String ghiChu;
    private int trangThai;
    private int soLuong;

    @Override
    public String toString() {
        return this.ghiChu ;
    }
            
    
}
