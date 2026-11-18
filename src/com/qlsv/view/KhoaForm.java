/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.qlsv.view;

import com.qlsv.dao.KhoaDAO;
import com.qlsv.model.Khoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI;

public class KhoaForm extends javax.swing.JFrame {

    private final KhoaDAO khoaDAO = new KhoaDAO();
    private DefaultTableModel model;

    public KhoaForm() {
        initComponents();

        setTitle("QUẢN LÝ KHOA");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        caiDatBang();
        caiDatMauNut();

        btnThem.addActionListener(e -> themKhoa());
        btnSua.addActionListener(e -> suaKhoa());
        btnXoa.addActionListener(e -> xoaKhoa());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblKhoa.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        taiDanhSachKhoa();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlKhoa = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblThongTinKhoa = new javax.swing.JLabel();
        lblMaKhoa = new javax.swing.JLabel();
        lblTenKhoa = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtMaKhoa = new javax.swing.JTextField();
        txtTenKhoa = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        scrKhoa = new javax.swing.JScrollPane();
        tblKhoa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(0, 102, 102));
        lblTieuDe.setText("QUẢN LÝ KHOA");

        lblThongTinKhoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblThongTinKhoa.setForeground(new java.awt.Color(102, 102, 102));
        lblThongTinKhoa.setText("THÔNG TIN KHOA");

        lblMaKhoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaKhoa.setText("Mã khoa");

        lblTenKhoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenKhoa.setText("Tên khoa");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setText("Email");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSDT.setText("Số điện thoại");

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("+ Thêm");

        btnSua.setBackground(new java.awt.Color(0, 153, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");

        btnXoa.setBackground(new java.awt.Color(255, 102, 102));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");

        btnLamMoi.setBackground(new java.awt.Color(255, 153, 0));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");

        tblKhoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khoa", "Tên khoa", "Email", "Số điện thoại"
            }
        ));
        tblKhoa.setToolTipText("");
        scrKhoa.setViewportView(tblKhoa);

        javax.swing.GroupLayout pnlKhoaLayout = new javax.swing.GroupLayout(pnlKhoa);
        pnlKhoa.setLayout(pnlKhoaLayout);
        pnlKhoaLayout.setHorizontalGroup(
            pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(pnlKhoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhoaLayout.createSequentialGroup()
                        .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThongTinKhoa))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoaLayout.createSequentialGroup()
                        .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhoa)
                            .addGroup(pnlKhoaLayout.createSequentialGroup()
                                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlKhoaLayout.createSequentialGroup()
                                .addGap(0, 24, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua))
                            .addComponent(txtEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSDT)
                            .addComponent(lblTenKhoa)
                            .addComponent(txtTenKhoa, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoaLayout.createSequentialGroup()
                                .addComponent(btnXoa)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi)
                                .addGap(16, 16, 16)))))
                .addContainerGap())
        );
        pnlKhoaLayout.setVerticalGroup(
            pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhoaLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTinKhoa)
                .addGap(18, 18, 18)
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKhoa)
                    .addComponent(lblTenKhoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblSDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(scrKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean kiemTraDuLieu() {
        if (txtMaKhoa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khoa!");
            txtMaKhoa.requestFocus();
            return false;
        }

        if (txtTenKhoa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khoa!");
            txtTenKhoa.requestFocus();
            return false;
        }
        return true;
    }

    private Khoa layThongTin() {
        return new Khoa(
            txtMaKhoa.getText().trim(),
            txtTenKhoa.getText().trim(),
            txtEmail.getText().trim(),
            txtSDT.getText().trim()
        );
    }
    private void themKhoa() {
        if (!kiemTraDuLieu()) {
            return;
        }

        if (khoaDAO.insert(layThongTin())) {
            JOptionPane.showMessageDialog(this, "Thêm khoa thành công!");
            lamMoi();
        } else {
            JOptionPane.showMessageDialog(this,
                "Thêm khoa thất bại! Mã khoa có thể đã tồn tại.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaKhoa() {
        int row = tblKhoa.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoa cần sửa!");
            return;
        }

        if (!kiemTraDuLieu()) {
            return;
        }

        if (khoaDAO.update(layThongTin())) {
            JOptionPane.showMessageDialog(this, "Cập nhật khoa thành công!");
            lamMoi();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void xoaKhoa() {
        int row = tblKhoa.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoa cần xóa!");
            return;
        }

        String maKhoa = model.getValueAt(row, 0).toString();
        int luaChon = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa khoa " + maKhoa + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (luaChon == JOptionPane.YES_OPTION) {
            if (khoaDAO.delete(maKhoa)) {
                JOptionPane.showMessageDialog(this, "Xóa khoa thành công!");
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Xóa thất bại! Khoa có thể đang được sử dụng trong NGANH hoặc LOP.",
                    "Không thể xóa", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void hienThiThongTin() {
        int row = tblKhoa.getSelectedRow();
        if (row < 0) {
            return;
        }

        txtMaKhoa.setText(String.valueOf(model.getValueAt(row, 0)));
        txtTenKhoa.setText(String.valueOf(model.getValueAt(row, 1)));
        txtEmail.setText(model.getValueAt(row, 2) == null
            ? "" : model.getValueAt(row, 2).toString());
        txtSDT.setText(model.getValueAt(row, 3) == null
            ? "" : model.getValueAt(row, 3).toString());

        txtMaKhoa.setEditable(false);
    }
    private void lamMoi() {
        txtMaKhoa.setText("");
        txtTenKhoa.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtMaKhoa.setEditable(true);
        tblKhoa.clearSelection();
        taiDanhSachKhoa();
        txtMaKhoa.requestFocusInWindow();
    }
    private void caiDatMauNut() {

    datMauNut(btnThem, new java.awt.Color(15, 118, 110));
    datMauNut(btnSua, new java.awt.Color(37, 99, 235));
    datMauNut(btnXoa, new java.awt.Color(220, 38, 38));
    datMauNut(btnLamMoi, new java.awt.Color(217, 119, 6));
}

private void datMauNut(
        javax.swing.JButton nut,
        java.awt.Color mau) {

    nut.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    nut.setBackground(mau);
    nut.setForeground(java.awt.Color.WHITE);
    nut.setOpaque(true);
    nut.setContentAreaFilled(true);
    nut.setBorderPainted(false);
    nut.setFocusPainted(false);
    nut.setFont(new java.awt.Font(
        "Segoe UI", java.awt.Font.BOLD, 13
    ));
    nut.setCursor(new java.awt.Cursor(
        java.awt.Cursor.HAND_CURSOR
    ));
}
    // MainForm dung ham nay de hien thi panel Khoa trong vung noi dung.
    public javax.swing.JPanel getPanel() {
        return pnlKhoa;
    }
    
        public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new KhoaForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMaKhoa;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKhoa;
    private javax.swing.JLabel lblThongTinKhoa;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlKhoa;
    private javax.swing.JScrollPane scrKhoa;
    private javax.swing.JTable tblKhoa;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKhoa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhoa;
    // End of variables declaration//GEN-END:variables

    private void caiDatBang() {
    model = new DefaultTableModel(
        new Object[]{
            "Mã khoa",
            "Tên khoa",
            "Email",
            "Số điện thoại"
        },
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    tblKhoa.setModel(model);
}

    private void taiDanhSachKhoa() {
    model.setRowCount(0);

    List<Khoa> danhSach = khoaDAO.getAll();

    if (danhSach == null) {
        return;
    }

    for (Khoa khoa : danhSach) {
        model.addRow(new Object[]{
            khoa.getMaKhoa(),
            khoa.getTenKhoa(),
            khoa.getEmail(),
            khoa.getSdt()
        });
    }
 }
}