/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.model;

public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private String email;
    private String sdt;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, String email, String sdt) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.email = email;
        this.sdt = sdt;
    }

    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }

    public String getTenKhoa() { return tenKhoa; }
    public void setTenKhoa(String tenKhoa) { this.tenKhoa = tenKhoa; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}
