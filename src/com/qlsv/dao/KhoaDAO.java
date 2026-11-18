/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.Khoa;
import java.sql.*;
import java.util.*;

public class KhoaDAO {

    public List<Khoa> getAll() {
        List<Khoa> list = new ArrayList<>();
        String sql = "SELECT * FROM KHOA";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Khoa(
                    rs.getString("MAKHOA"),
                    rs.getString("TENKHOA"),
                    rs.getString("EMAIL"),
                    rs.getString("SDT")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Khoa k) {
        String sql = "INSERT INTO KHOA VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, k.getMaKhoa());
            ps.setString(2, k.getTenKhoa());
            ps.setString(3, k.getEmail());
            ps.setString(4, k.getSdt());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Khoa k) {
        String sql = "UPDATE KHOA SET TENKHOA=?, EMAIL=?, SDT=? WHERE MAKHOA=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, k.getTenKhoa());
            ps.setString(2, k.getEmail());
            ps.setString(3, k.getSdt());
            ps.setString(4, k.getMaKhoa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maKhoa) {
        String sql = "DELETE FROM KHOA WHERE MAKHOA=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maKhoa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Khoa> search(String keyword) {
        List<Khoa> list = new ArrayList<>();
        String sql = "SELECT * FROM KHOA WHERE MAKHOA LIKE ? OR TENKHOA LIKE ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Khoa(
                        rs.getString("MAKHOA"),
                        rs.getString("TENKHOA"),
                        rs.getString("EMAIL"),
                        rs.getString("SDT")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
