/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qlsv.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Dia chi SQL Server
    private static final String URL =
            "jdbc:sqlserver://localhost;"
            + "instanceName=SQLEXPRESS;"
            + "databaseName=QLSinhVien;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    // Tai khoan SQL Server
    private static final String USER = "sa";

    // Mat khau SQL Server cua ban
    private static final String PASSWORD = "10022007";

    // Phuong thuc ket noi Database
    public static Connection getConnection() {

        try {

            Connection conn = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            System.out.println("Ket noi SQL Server thanh cong!");

            return conn;

        } catch (SQLException e) {

            System.out.println("Ket noi SQL Server that bai!");
            e.printStackTrace();

            return null;
        }
    }
}

