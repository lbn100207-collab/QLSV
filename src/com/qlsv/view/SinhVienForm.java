/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.qlsv.view;

import com.qlsv.dao.SinhVienDAO;
import com.qlsv.dao.LopDAO;

import com.qlsv.model.SinhVien;
import com.qlsv.model.Lop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SinhVienForm extends JPanel {

    private final SinhVienDAO sinhVienDAO = new SinhVienDAO();
    private final LopDAO lopDAO = new LopDAO();

    private JTextField txtMaSV;
    private JTextField txtHoSV;
    private JTextField txtTenSV;
    private JTextField txtNgaySinh;

    private JComboBox<String> cboPhai;
    private JComboBox<Lop> cboLop;

    private JTable tblSinhVien;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    public SinhVienForm() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        taoGiaoDien();
        caiDatComboBox();
        caiDatMauNut();

        btnThem.addActionListener(e -> themSinhVien());
        btnSua.addActionListener(e -> suaSinhVien());
        btnXoa.addActionListener(e -> xoaSinhVien());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblSinhVien.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        taiDanhSachLop();
        taiDanhSachSinhVien();
    }

    private void taoGiaoDien() {

        JLabel lblTieuDe = new JLabel("QUẢN LÝ SINH VIÊN");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTieuDe.setForeground(new Color(15, 118, 110));

        txtMaSV = new JTextField();
        txtHoSV = new JTextField();
        txtTenSV = new JTextField();
        txtNgaySinh = new JTextField();

        cboPhai = new JComboBox<>(
            new String[]{"Nam", "Nữ"}
        );

        cboLop = new JComboBox<>();

        JPanel pnlThongTin = new JPanel(new GridLayout(3, 4, 10, 10));
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(
            BorderFactory.createTitledBorder("THÔNG TIN SINH VIÊN")
        );

        pnlThongTin.add(new JLabel("Mã sinh viên:"));
        pnlThongTin.add(txtMaSV);

        pnlThongTin.add(new JLabel("Họ sinh viên:"));
        pnlThongTin.add(txtHoSV);

        pnlThongTin.add(new JLabel("Tên sinh viên:"));
        pnlThongTin.add(txtTenSV);

        pnlThongTin.add(new JLabel("Giới tính:"));
        pnlThongTin.add(cboPhai);

        pnlThongTin.add(new JLabel("Ngày sinh (yyyy-MM-dd):"));
        pnlThongTin.add(txtNgaySinh);

        pnlThongTin.add(new JLabel("Lớp:"));
        pnlThongTin.add(cboLop);

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
                "Mã SV",
                "Họ SV",
                "Tên SV",
                "Phái",
                "Ngày sinh",
                "Mã lớp"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSinhVien = new JTable(model);
        tblSinhVien.setRowHeight(28);
        tblSinhVien.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrSinhVien = new JScrollPane(tblSinhVien);
        scrSinhVien.setBorder(
            BorderFactory.createTitledBorder("DANH SÁCH SINH VIÊN")
        );

        add(pnlTren, BorderLayout.NORTH);
        add(scrSinhVien, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void caiDatComboBox() {

        cboLop.setRenderer(new DefaultListCellRenderer() {

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

                if (value instanceof Lop) {
                    Lop lop = (Lop) value;

                    setText(
                        lop.getMaLop() + " - " + lop.getTenLop()
                    );
                }

                return this;
            }
        });
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

    private void taiDanhSachLop() {

        cboLop.removeAllItems();

        List<Lop> danhSach = lopDAO.getAll();

        for (Lop lop : danhSach) {
            cboLop.addItem(lop);
        }
    }

    private void taiDanhSachSinhVien() {

        model.setRowCount(0);

        List<SinhVien> danhSach = sinhVienDAO.getAll();

        for (SinhVien sv : danhSach) {

            model.addRow(new Object[]{
                sv.getMaSV(),
                sv.getHoSV(),
                sv.getTenSV(),
                sv.getPhai(),
                sv.getNgaySinh(),
                sv.getMaLop()
            });
        }
    }

    private boolean kiemTraDuLieu() {

        if (txtMaSV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên!");
            txtMaSV.requestFocus();
            return false;
        }

        if (txtHoSV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ sinh viên!");
            txtHoSV.requestFocus();
            return false;
        }

        if (txtTenSV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sinh viên!");
            txtTenSV.requestFocus();
            return false;
        }

        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh!");
            txtNgaySinh.requestFocus();
            return false;
        }

        try {
            Date.valueOf(txtNgaySinh.getText().trim());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                this,
                "Ngày sinh không hợp lệ!\n"
                + "Vui lòng nhập theo dạng yyyy-MM-dd.\n"
                + "Ví dụ: 2005-08-15"
            );
            txtNgaySinh.requestFocus();
            return false;
        }

        if (cboLop.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp!");
            return false;
        }

        return true;
    }

    private SinhVien layThongTin() {

        Lop lop = (Lop) cboLop.getSelectedItem();

        return new SinhVien(
            txtMaSV.getText().trim(),
            txtHoSV.getText().trim(),
            txtTenSV.getText().trim(),
            cboPhai.getSelectedItem().toString(),
            Date.valueOf(txtNgaySinh.getText().trim()),
            lop.getMaLop()
        );
    }

    private void themSinhVien() {

        if (!kiemTraDuLieu()) {
            return;
        }

        if (sinhVienDAO.insert(layThongTin())) {

            JOptionPane.showMessageDialog(
                this, "Thêm sinh viên thành công!"
            );

            lamMoi();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Thêm sinh viên thất bại! Mã sinh viên có thể đã tồn tại.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void suaSinhVien() {

        if (tblSinhVien.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(
                this, "Vui lòng chọn sinh viên cần sửa!"
            );
            return;
        }

        if (!kiemTraDuLieu()) {
            return;
        }

        if (sinhVienDAO.update(layThongTin())) {

            JOptionPane.showMessageDialog(
                this, "Cập nhật sinh viên thành công!"
            );

            lamMoi();

        } else {

            JOptionPane.showMessageDialog(
                this, "Cập nhật sinh viên thất bại!"
            );
        }
    }

    private void xoaSinhVien() {

        int row = tblSinhVien.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                this, "Vui lòng chọn sinh viên cần xóa!"
            );
            return;
        }

        String maSV = model.getValueAt(row, 0).toString();

        int chon = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa sinh viên " + maSV + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (chon == JOptionPane.YES_OPTION) {

            if (sinhVienDAO.delete(maSV)) {

                JOptionPane.showMessageDialog(
                    this, "Xóa sinh viên thành công!"
                );

                lamMoi();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Không thể xóa! Sinh viên có thể đang có dữ liệu điểm.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void hienThiThongTin() {

        int row = tblSinhVien.getSelectedRow();

        if (row < 0) {
            return;
        }

        txtMaSV.setText(model.getValueAt(row, 0).toString());
        txtHoSV.setText(model.getValueAt(row, 1).toString());
        txtTenSV.setText(model.getValueAt(row, 2).toString());
        cboPhai.setSelectedItem(model.getValueAt(row, 3).toString());
        txtNgaySinh.setText(model.getValueAt(row, 4).toString());

        String maLop = model.getValueAt(row, 5).toString();

        for (int i = 0; i < cboLop.getItemCount(); i++) {

            Lop lop = cboLop.getItemAt(i);

            if (lop.getMaLop().equals(maLop)) {
                cboLop.setSelectedIndex(i);
                break;
            }
        }

        txtMaSV.setEditable(false);
    }

    private void lamMoi() {

        txtMaSV.setText("");
        txtHoSV.setText("");
        txtTenSV.setText("");
        txtNgaySinh.setText("");

        cboPhai.setSelectedIndex(0);

        txtMaSV.setEditable(true);

        tblSinhVien.clearSelection();

        taiDanhSachLop();
        taiDanhSachSinhVien();

        txtMaSV.requestFocusInWindow();
    }

    public JPanel getPanel() {
        return this;
    }
}
