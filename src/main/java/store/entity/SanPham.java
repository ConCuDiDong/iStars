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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SanPham {
    
    @EqualsAndHashCode.Include
    private int id;
    
    private String ma;
    private String ten;
    private int idXX;
    private int idPin;
    private BigDecimal chieuDai;
    private BigDecimal chieuRong;
    private BigDecimal doDay;
    private int soLuong;
    
    @Override
    public String toString() {
        return this.ten ;
    }
    
}
