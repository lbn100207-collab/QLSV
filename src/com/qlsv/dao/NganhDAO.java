/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.Nganh;
import java.sql.*;
import java.util.*;

public class NganhDAO {

    public List<Nganh> getAll() {
        List<Nganh> list = new ArrayList<>();
        String sql = "SELECT * FROM NGANH";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Nganh(
                    rs.getString("MANGANH"),
                    rs.getString("TENNGANH"),
                    rs.getString("MAKHOA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Nganh n) {
        String sql = "INSERT INTO NGANH (MANGANH,TENNGANH,MAKHOA) VALUES (?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, n.getMaNganh());
            ps.setString(2, n.getTenNganh());
            ps.setString(3, n.getMaKhoa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Nganh n) {
        String sql = "UPDATE NGANH SET TENNGANH=?, MAKHOA=? WHERE MANGANH=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, n.getTenNganh());
            ps.setString(2, n.getMaKhoa());
            ps.setString(3, n.getMaNganh());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maNganh) {
        String sql = "DELETE FROM NGANH WHERE MANGANH=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maNganh);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Nganh> search(String keyword) {
        List<Nganh> list = new ArrayList<>();
        String sql = "SELECT * FROM NGANH WHERE MANGANH LIKE ? OR TENNGANH LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Nganh(
                        rs.getString("MANGANH"),
                        rs.getString("TENNGANH"),
                        rs.getString("MAKHOA")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
