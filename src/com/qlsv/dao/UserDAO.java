/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.dao;

import com.qlsv.database.DBConnection;
import com.qlsv.model.User;
import java.sql.*;

public class UserDAO {

    public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD_HASH=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("USERID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD_HASH")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

