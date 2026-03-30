/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.entity;

import lombok.*;
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class KhachHang {
    private int id;
    private String maKH;
    private int GioiTinh;
    private String diaChi;
    private String email;
    private int TrangThai;
    private String tenKH;
    private String soDT;

    @Override
    public String toString() {
        return this.email;
    }

}
