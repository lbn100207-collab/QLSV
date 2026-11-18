/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.qlsv.view;

import com.qlsv.dao.MonHocDAO;
import com.qlsv.model.MonHoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MonHocForm extends JPanel {

    private JTextField txtMaMH, txtTenMH, txtSoTinChi;
    private JTable tblMonHoc;
    private DefaultTableModel model;

    private JButton btnThem, btnSua, btnXoa, btnLamMoi;

    private MonHocDAO monHocDAO = new MonHocDAO();

    public MonHocForm() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        taoGiaoDien();
        caiDatBang();
        caiDatMauNut();

        btnThem.addActionListener(e -> themMonHoc());
        btnSua.addActionListener(e -> suaMonHoc());
        btnXoa.addActionListener(e -> xoaMonHoc());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblMonHoc.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        taiDanhSachMonHoc();
    }

    private void taoGiaoDien() {

        JLabel lblTieuDe = new JLabel(
                "QUẢN LÝ MÔN HỌC",
                SwingConstants.CENTER
        );
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTieuDe.setForeground(new Color(30, 64, 100));

        add(lblTieuDe, BorderLayout.NORTH);

        JPanel pnlChinh = new JPanel(new BorderLayout(10, 10));
        pnlChinh.setBackground(Color.WHITE);

        JPanel pnlThongTin = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin môn học"));

        txtMaMH = new JTextField();
        txtTenMH = new JTextField();
        txtSoTinChi = new JTextField();

        pnlThongTin.add(new JLabel("Mã môn học:"));
        pnlThongTin.add(txtMaMH);

        pnlThongTin.add(new JLabel("Tên môn học:"));
        pnlThongTin.add(txtTenMH);

        pnlThongTin.add(new JLabel("Số tín chỉ:"));
        pnlThongTin.add(txtSoTinChi);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        JPanel pnlNut = new JPanel(new GridLayout(1, 4, 10, 10));
        pnlNut.setBackground(Color.WHITE);

        pnlNut.add(btnThem);
        pnlNut.add(btnSua);
        pnlNut.add(btnXoa);
        pnlNut.add(btnLamMoi);

        JPanel pnlTren = new JPanel(new BorderLayout(10, 10));
        pnlTren.setBackground(Color.WHITE);
        pnlTren.add(pnlThongTin, BorderLayout.CENTER);
        pnlTren.add(pnlNut, BorderLayout.SOUTH);

        model = new DefaultTableModel(
                new String[]{"Mã môn học", "Tên môn học", "Số tín chỉ"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblMonHoc = new JTable(model);

        pnlChinh.add(pnlTren, BorderLayout.NORTH);
        pnlChinh.add(new JScrollPane(tblMonHoc), BorderLayout.CENTER);

        add(pnlChinh, BorderLayout.CENTER);
    }

    private void caiDatBang() {
        tblMonHoc.setRowHeight(28);
        tblMonHoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMonHoc.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );
    }

    private void caiDatMauNut() {
        datMauNut(btnThem, new Color(15, 118, 110));
        datMauNut(btnSua, new Color(37, 99, 235));
        datMauNut(btnXoa, new Color(220, 38, 38));
        datMauNut(btnLamMoi, new Color(217, 119, 6));
    }

    private void datMauNut(JButton nut, Color mau) {
        nut.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        nut.setBackground(mau);
        nut.setForeground(Color.WHITE);
        nut.setOpaque(true);
        nut.setContentAreaFilled(true);
        nut.setBorderPainted(false);
        nut.setFocusPainted(false);
        nut.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nut.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private MonHoc layThongTin() {

        String maMH = txtMaMH.getText().trim();
        String tenMH = txtTenMH.getText().trim();
        String soTinChi = txtSoTinChi.getText().trim();

        if (maMH.isEmpty() || tenMH.isEmpty() || soTinChi.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ thông tin!");
            return null;
        }

        try {
            int tinChi = Integer.parseInt(soTinChi);

            if (tinChi <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Số tín chỉ phải lớn hơn 0!");
                return null;
            }

            return new MonHoc(maMH, tenMH, tinChi);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Số tín chỉ phải là số nguyên!");
            return null;
        }
    }

    private void themMonHoc() {
        MonHoc mh = layThongTin();

        if (mh == null) {
            return;
        }

        try {
            if (monHocDAO.insert(mh)) {
                JOptionPane.showMessageDialog(this,
                        "Thêm môn học thành công!");
                taiDanhSachMonHoc();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể thêm môn học!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void suaMonHoc() {
        MonHoc mh = layThongTin();

        if (mh == null) {
            return;
        }

        try {
            if (monHocDAO.update(mh)) {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật môn học thành công!");
                taiDanhSachMonHoc();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể cập nhật môn học!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void xoaMonHoc() {

        String maMH = txtMaMH.getText().trim();

        if (maMH.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn môn học cần xóa!");
            return;
        }

        int chon = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa môn học này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (chon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (monHocDAO.delete(maMH)) {
                JOptionPane.showMessageDialog(this,
                        "Xóa môn học thành công!");
                taiDanhSachMonHoc();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể xóa môn học!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void taiDanhSachMonHoc() {

        model.setRowCount(0);

        try {
            List<MonHoc> danhSach = monHocDAO.getAll();

            for (MonHoc mh : danhSach) {
                model.addRow(new Object[]{
                    mh.getMaMH(),
                    mh.getTenMH(),
                    mh.getSoTinChi()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi tải danh sách môn học: " + e.getMessage());
        }
    }

    private void hienThiThongTin() {

        int row = tblMonHoc.getSelectedRow();

        if (row < 0) {
            return;
        }

        txtMaMH.setText(model.getValueAt(row, 0).toString());
        txtTenMH.setText(model.getValueAt(row, 1).toString());
        txtSoTinChi.setText(model.getValueAt(row, 2).toString());

        txtMaMH.setEditable(false);
    }

    private void lamMoi() {
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtSoTinChi.setText("");

        txtMaMH.setEditable(true);
        tblMonHoc.clearSelection();
        txtMaMH.requestFocus();
    }

    public JPanel getPanel() {
        return this;
    }
}

