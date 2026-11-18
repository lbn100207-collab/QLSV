/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.Diem;
import java.sql.*;
import java.util.*;

public class DiemDAO {

    private Diem map(ResultSet rs) throws SQLException {
        return new Diem(
            rs.getInt("MADIEM"),
            rs.getString("MASV"),
            rs.getString("MAMH"),
            rs.getBigDecimal("DIEMQT"),
            rs.getBigDecimal("DIEMTHI"),
            rs.getBigDecimal("DIEMTK"),
            (Integer) rs.getObject("LANTHI")
        );
    }

    public List<Diem> getAll() {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT * FROM DIEM";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Diem d) {
        String sql = "INSERT INTO DIEM (MASV,MAMH,DIEMQT,DIEMTHI,DIEMTK,LANTHI) VALUES (?,?,?,?,?,?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getMaSV());
            ps.setString(2, d.getMaMH());
            ps.setBigDecimal(3, d.getDiemQT());
            ps.setBigDecimal(4, d.getDiemThi());
            ps.setBigDecimal(5, d.getDiemTK());

            if (d.getLanThi() == null) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, d.getLanThi());
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Diem d) {
        String sql = "UPDATE DIEM SET MASV=?, MAMH=?, DIEMQT=?, DIEMTHI=?, DIEMTK=?, LANTHI=? WHERE MADIEM=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getMaSV());
            ps.setString(2, d.getMaMH());
            ps.setBigDecimal(3, d.getDiemQT());
            ps.setBigDecimal(4, d.getDiemThi());
            ps.setBigDecimal(5, d.getDiemTK());

            if (d.getLanThi() == null) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, d.getLanThi());
            }

            ps.setInt(7, d.getMaDiem());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int maDiem) {
        String sql = "DELETE FROM DIEM WHERE MADIEM=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maDiem);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Diem> search(String keyword) {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT * FROM DIEM WHERE MASV LIKE ? OR MAMH LIKE ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
