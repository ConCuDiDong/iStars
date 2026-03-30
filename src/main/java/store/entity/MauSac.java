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

public class MauSac {
    private int id;
    private String maMau;
    private String ten;

    @Override
    public String toString() {
        return this.ten;
    }
    
}
