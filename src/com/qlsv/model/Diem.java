/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.model;

import java.math.BigDecimal;

public class Diem {
    private int maDiem;
    private String maSV;
    private String maMH;
    private BigDecimal diemQT;
    private BigDecimal diemThi;
    private BigDecimal diemTK;
    private Integer lanThi;

    public Diem() {
    }

    public Diem(int maDiem, String maSV, String maMH,
                BigDecimal diemQT, BigDecimal diemThi,
                BigDecimal diemTK, Integer lanThi) {
        this.maDiem = maDiem;
        this.maSV = maSV;
        this.maMH = maMH;
        this.diemQT = diemQT;
        this.diemThi = diemThi;
        this.diemTK = diemTK;
        this.lanThi = lanThi;
    }

    public int getMaDiem() { return maDiem; }
    public void setMaDiem(int maDiem) { this.maDiem = maDiem; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getMaMH() { return maMH; }
    public void setMaMH(String maMH) { this.maMH = maMH; }

    public BigDecimal getDiemQT() { return diemQT; }
    public void setDiemQT(BigDecimal diemQT) { this.diemQT = diemQT; }

    public BigDecimal getDiemThi() { return diemThi; }
    public void setDiemThi(BigDecimal diemThi) { this.diemThi = diemThi; }

    public BigDecimal getDiemTK() { return diemTK; }
    public void setDiemTK(BigDecimal diemTK) { this.diemTK = diemTK; }

    public Integer getLanThi() { return lanThi; }
    public void setLanThi(Integer lanThi) { this.lanThi = lanThi; }
}

