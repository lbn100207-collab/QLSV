/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.MonHoc;
import java.sql.*;
import java.util.*;

public class MonHocDAO {

    public List<MonHoc> getAll() {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM MONHOC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MonHoc(
                    rs.getString("MAMH"),
                    rs.getString("TENMH"),
                    rs.getInt("SOTINCHI")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(MonHoc m) {
        String sql = "INSERT INTO MONHOC (MAMH,TENMH,SOTINCHI) VALUES (?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getMaMH());
            ps.setString(2, m.getTenMH());
            ps.setInt(3, m.getSoTinChi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(MonHoc m) {
        String sql = "UPDATE MONHOC SET TENMH=?, SOTINCHI=? WHERE MAMH=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getTenMH());
            ps.setInt(2, m.getSoTinChi());
            ps.setString(3, m.getMaMH());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maMH) {
        String sql = "DELETE FROM MONHOC WHERE MAMH=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maMH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MonHoc> search(String keyword) {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM MONHOC WHERE MAMH LIKE ? OR TENMH LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new MonHoc(
                        rs.getString("MAMH"),
                        rs.getString("TENMH"),
                        rs.getInt("SOTINCHI")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

