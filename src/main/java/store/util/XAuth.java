/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.util;

import store.entity.NhanVien;

/**
 * s
 *
 * @author dell
 */
public class XAuth {

public static NhanVien user = NhanVien.builder()
        .maNV("user1@gmail.com")
         .matKhau("123")
          .trangThai(1)
        .chucVu(1)
          .hoTen("Nguyễn Văn Tèo")
          .build(); // biến user này sẽ được thay thế sau khi đăng nhập
    
}
