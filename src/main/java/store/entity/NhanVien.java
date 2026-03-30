/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class NhanVien {
    private int id;
    private String maNV;
    private String hoTen;
    private int GioiTinh;
    private String ngaySinh;
    private String sdt;
    private String matKhau;
    private int chucVu;
    private int trangThai;
    private String email;
}
