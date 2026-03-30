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

public class Rom {

    private int id;
    private int dungLuong;

    @Override
    public String toString() {
        if (dungLuong >= 1024) {       // >= 1 TB
            return (dungLuong / 1024) + " TB";
        } else {                        // < 1 TB
            return dungLuong + " GB";
        }
    }

}
