/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.model;

import java.sql.Date;

public class SinhVien {
    private String maSV;
    private String hoSV;
    private String tenSV;
    private String phai;
    private Date ngaySinh;
    private String maLop;

    public SinhVien() {
    }

    public SinhVien(String maSV, String hoSV, String tenSV,
                    String phai, Date ngaySinh, String maLop) {
        this.maSV = maSV;
        this.hoSV = hoSV;
        this.tenSV = tenSV;
        this.phai = phai;
        this.ngaySinh = ngaySinh;
        this.maLop = maLop;
    }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getHoSV() { return hoSV; }
    public void setHoSV(String hoSV) { this.hoSV = hoSV; }

    public String getTenSV() { return tenSV; }
    public void setTenSV(String tenSV) { this.tenSV = tenSV; }

    public String getPhai() { return phai; }
    public void setPhai(String phai) { this.phai = phai; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }
}

