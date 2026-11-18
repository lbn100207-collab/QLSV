/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.Lop;
import java.sql.*;
import java.util.*;

public class LopDAO {

    public List<Lop> getAll() {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM LOP";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Lop(
                    rs.getString("MALOP"),
                    rs.getString("TENLOP"),
                    rs.getString("MAKHOA"),
                    rs.getString("MANGANH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Lop l) {
        String sql = "INSERT INTO LOP (MALOP,TENLOP,MAKHOA,MANGANH) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, l.getMaLop());
            ps.setString(2, l.getTenLop());
            ps.setString(3, l.getMaKhoa());
            ps.setString(4, l.getMaNganh());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Lop l) {
        String sql = "UPDATE LOP SET TENLOP=?, MAKHOA=?, MANGANH=? WHERE MALOP=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, l.getTenLop());
            ps.setString(2, l.getMaKhoa());
            ps.setString(3, l.getMaNganh());
            ps.setString(4, l.getMaLop());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maLop) {
        String sql = "DELETE FROM LOP WHERE MALOP=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maLop);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Lop> search(String keyword) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM LOP WHERE MALOP LIKE ? OR TENLOP LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Lop(
                        rs.getString("MALOP"),
                        rs.getString("TENLOP"),
                        rs.getString("MAKHOA"),
                        rs.getString("MANGANH")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

