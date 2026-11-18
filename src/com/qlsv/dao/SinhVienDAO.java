/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.SinhVien;
import java.sql.*;
import java.util.*;

public class SinhVienDAO {

    private SinhVien map(ResultSet rs) throws SQLException {
        return new SinhVien(
            rs.getString("MASV"),
            rs.getString("HOSV"),
            rs.getString("TENSV"),
            rs.getString("PHAI"),
            rs.getDate("NS"),
            rs.getString("MALOP")
        );
    }

    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(SinhVien s) {
        String sql = "INSERT INTO SINHVIEN (MASV,HOSV,TENSV,PHAI,NS,MALOP) VALUES (?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getMaSV());
            ps.setString(2, s.getHoSV());
            ps.setString(3, s.getTenSV());
            ps.setString(4, s.getPhai());
            ps.setDate(5, s.getNgaySinh());
            ps.setString(6, s.getMaLop());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(SinhVien s) {
        String sql = "UPDATE SINHVIEN SET HOSV=?,TENSV=?,PHAI=?,NS=?,MALOP=? WHERE MASV=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getHoSV());
            ps.setString(2, s.getTenSV());
            ps.setString(3, s.getPhai());
            ps.setDate(4, s.getNgaySinh());
            ps.setString(5, s.getMaLop());
            ps.setString(6, s.getMaSV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maSV) {
        String sql = "DELETE FROM SINHVIEN WHERE MASV=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maSV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SinhVien> search(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN WHERE MASV LIKE ? OR HOSV LIKE ? OR TENSV LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

