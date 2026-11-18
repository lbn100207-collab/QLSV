/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.qlsv.view;

import com.qlsv.dao.NganhDAO;
import com.qlsv.dao.KhoaDAO;
import com.qlsv.model.Nganh;
import com.qlsv.model.Khoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NganhForm extends JPanel {

    private final NganhDAO nganhDAO = new NganhDAO();
    private final KhoaDAO khoaDAO = new KhoaDAO();

    private JTextField txtMaNganh;
    private JTextField txtTenNganh;

    private JComboBox<Khoa> cboKhoa;

    private JTable tblNganh;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    public NganhForm() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        taoGiaoDien();
        caiDatBang();
        caiDatMauNut();

        btnThem.addActionListener(e -> themNganh());
        btnSua.addActionListener(e -> suaNganh());
        btnXoa.addActionListener(e -> xoaNganh());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblNganh.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        taiDanhSachKhoa();
        taiDanhSachNganh();
    }

    private void taoGiaoDien() {

        JLabel lblTieuDe = new JLabel("QUẢN LÝ NGÀNH");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTieuDe.setForeground(new Color(15, 118, 110));

        JLabel lblMa = new JLabel("Mã ngành:");
        JLabel lblTen = new JLabel("Tên ngành:");
        JLabel lblKhoa = new JLabel("Khoa:");

        txtMaNganh = new JTextField();
        txtTenNganh = new JTextField();

        cboKhoa = new JComboBox<>();

        JPanel pnlThongTin = new JPanel(new GridLayout(3, 2, 10, 10));
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(
            BorderFactory.createTitledBorder("THÔNG TIN NGÀNH")
        );

        pnlThongTin.add(lblMa);
        pnlThongTin.add(txtMaNganh);

        pnlThongTin.add(lblTen);
        pnlThongTin.add(txtTenNganh);

        pnlThongTin.add(lblKhoa);
        pnlThongTin.add(cboKhoa);

        btnThem = new JButton("+ Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        JPanel pnlNut = new JPanel();
        pnlNut.setBackground(Color.WHITE);

        pnlNut.add(btnThem);
        pnlNut.add(btnSua);
        pnlNut.add(btnXoa);
        pnlNut.add(btnLamMoi);

        JPanel pnlTren = new JPanel(new BorderLayout(10, 10));
        pnlTren.setBackground(Color.WHITE);

        pnlTren.add(lblTieuDe, BorderLayout.NORTH);
        pnlTren.add(pnlThongTin, BorderLayout.CENTER);
        pnlTren.add(pnlNut, BorderLayout.SOUTH);

        model = new DefaultTableModel(
            new Object[]{
                "Mã ngành", "Tên ngành", "Mã khoa"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblNganh = new JTable(model);
        tblNganh.setRowHeight(28);

        JScrollPane scrNganh = new JScrollPane(tblNganh);
        scrNganh.setBorder(
            BorderFactory.createTitledBorder("DANH SÁCH NGÀNH")
        );

        add(pnlTren, BorderLayout.NORTH);
        add(scrNganh, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void caiDatBang() {

        tblNganh.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
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
        nut.setCursor(new java.awt.Cursor(
            java.awt.Cursor.HAND_CURSOR
        ));
    }

    private void taiDanhSachKhoa() {

        cboKhoa.removeAllItems();

        List<Khoa> danhSach = khoaDAO.getAll();

        for (Khoa khoa : danhSach) {
            cboKhoa.addItem(khoa);
        }

        cboKhoa.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
            ) {

                super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof Khoa) {
                    Khoa khoa = (Khoa) value;
                    setText(khoa.getMaKhoa() + " - " + khoa.getTenKhoa());
                }

                return this;
            }
        });
    }

    private void taiDanhSachNganh() {

        model.setRowCount(0);

        List<Nganh> danhSach = nganhDAO.getAll();

        for (Nganh nganh : danhSach) {
            model.addRow(new Object[]{
                nganh.getMaNganh(),
                nganh.getTenNganh(),
                nganh.getMaKhoa()
            });
        }
    }

    private boolean kiemTraDuLieu() {

        if (txtMaNganh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã ngành!");
            txtMaNganh.requestFocus();
            return false;
        }

        if (txtTenNganh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên ngành!");
            txtTenNganh.requestFocus();
            return false;
        }

        if (cboKhoa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoa!");
            return false;
        }

        return true;
    }

    private Nganh layThongTin() {

        Khoa khoa = (Khoa) cboKhoa.getSelectedItem();

        return new Nganh(
            txtMaNganh.getText().trim(),
            txtTenNganh.getText().trim(),
            khoa.getMaKhoa()
        );
    }

    private void themNganh() {

        if (!kiemTraDuLieu()) {
            return;
        }

        if (nganhDAO.insert(layThongTin())) {

            JOptionPane.showMessageDialog(this, "Thêm ngành thành công!");
            lamMoi();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Thêm ngành thất bại! Mã ngành có thể đã tồn tại.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void suaNganh() {

        if (tblNganh.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngành cần sửa!");
            return;
        }

        if (!kiemTraDuLieu()) {
            return;
        }

        if (nganhDAO.update(layThongTin())) {

            JOptionPane.showMessageDialog(this, "Cập nhật ngành thành công!");
            lamMoi();

        } else {

            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    private void xoaNganh() {

        int row = tblNganh.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngành cần xóa!");
            return;
        }

        String maNganh = model.getValueAt(row, 0).toString();

        int chon = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn xóa ngành " + maNganh + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (chon == JOptionPane.YES_OPTION) {

            if (nganhDAO.delete(maNganh)) {

                JOptionPane.showMessageDialog(this, "Xóa ngành thành công!");
                lamMoi();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Không thể xóa! Ngành có thể đang được sử dụng trong bảng LOP.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void hienThiThongTin() {

        int row = tblNganh.getSelectedRow();

        if (row < 0) {
            return;
        }

        txtMaNganh.setText(model.getValueAt(row, 0).toString());
        txtTenNganh.setText(model.getValueAt(row, 1).toString());

        String maKhoa = model.getValueAt(row, 2).toString();

        for (int i = 0; i < cboKhoa.getItemCount(); i++) {

            Khoa khoa = cboKhoa.getItemAt(i);

            if (khoa.getMaKhoa().equals(maKhoa)) {
                cboKhoa.setSelectedIndex(i);
                break;
            }
        }

        txtMaNganh.setEditable(false);
    }

    private void lamMoi() {

        txtMaNganh.setText("");
        txtTenNganh.setText("");

        txtMaNganh.setEditable(true);

        tblNganh.clearSelection();

        taiDanhSachKhoa();
        taiDanhSachNganh();

        txtMaNganh.requestFocusInWindow();
    }

    public JPanel getPanel() {
        return this;
    }
}
