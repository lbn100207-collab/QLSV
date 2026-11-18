/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.qlsv.view;

import com.qlsv.dao.LopDAO;
import com.qlsv.dao.KhoaDAO;
import com.qlsv.dao.NganhDAO;

import com.qlsv.model.Lop;
import com.qlsv.model.Khoa;
import com.qlsv.model.Nganh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LopForm extends JPanel {

    private final LopDAO lopDAO = new LopDAO();
    private final KhoaDAO khoaDAO = new KhoaDAO();
    private final NganhDAO nganhDAO = new NganhDAO();

    private JTextField txtMaLop;
    private JTextField txtTenLop;

    private JComboBox<Khoa> cboKhoa;
    private JComboBox<Nganh> cboNganh;

    private JTable tblLop;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    public LopForm() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        taoGiaoDien();
        caiDatMauNut();

        btnThem.addActionListener(e -> themLop());
        btnSua.addActionListener(e -> suaLop());
        btnXoa.addActionListener(e -> xoaLop());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblLop.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        cboKhoa.addActionListener(e -> locNganhTheoKhoa());

        taiDanhSachKhoa();
        taiDanhSachNganh();
        locNganhTheoKhoa();
        taiDanhSachLop();
    }

    private void taoGiaoDien() {

        JLabel lblTieuDe = new JLabel("QUẢN LÝ LỚP");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTieuDe.setForeground(new Color(15, 118, 110));

        txtMaLop = new JTextField();
        txtTenLop = new JTextField();

        cboKhoa = new JComboBox<>();
        cboNganh = new JComboBox<>();

        JPanel pnlThongTin = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(
            BorderFactory.createTitledBorder("THÔNG TIN LỚP")
        );

        pnlThongTin.add(new JLabel("Mã lớp:"));
        pnlThongTin.add(new JLabel("Tên lớp:"));
        pnlThongTin.add(new JLabel("Khoa:"));
        pnlThongTin.add(new JLabel("Ngành:"));

        pnlThongTin.add(txtMaLop);
        pnlThongTin.add(txtTenLop);
        pnlThongTin.add(cboKhoa);
        pnlThongTin.add(cboNganh);

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
                "Mã lớp", "Tên lớp", "Mã khoa", "Mã ngành"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblLop = new JTable(model);
        tblLop.setRowHeight(28);
        tblLop.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrLop = new JScrollPane(tblLop);
        scrLop.setBorder(
            BorderFactory.createTitledBorder("DANH SÁCH LỚP")
        );

        add(pnlTren, BorderLayout.NORTH);
        add(scrLop, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        caiDatComboBox();
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

    private void caiDatComboBox() {

        cboKhoa.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus
            ) {
                super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof Khoa) {
                    Khoa k = (Khoa) value;
                    setText(k.getMaKhoa() + " - " + k.getTenKhoa());
                }

                return this;
            }
        });

        cboNganh.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus
            ) {
                super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof Nganh) {
                    Nganh n = (Nganh) value;
                    setText(n.getMaNganh() + " - " + n.getTenNganh());
                }

                return this;
            }
        });
    }

    private void taiDanhSachKhoa() {

        cboKhoa.removeAllItems();

        List<Khoa> danhSach = khoaDAO.getAll();

        for (Khoa khoa : danhSach) {
            cboKhoa.addItem(khoa);
        }
    }

    private void taiDanhSachNganh() {

        cboNganh.removeAllItems();

        List<Nganh> danhSach = nganhDAO.getAll();

        for (Nganh nganh : danhSach) {
            cboNganh.addItem(nganh);
        }
    }

    private void locNganhTheoKhoa() {

        Khoa khoa = (Khoa) cboKhoa.getSelectedItem();

        cboNganh.removeAllItems();

        if (khoa == null) {
            return;
        }

        List<Nganh> danhSach = nganhDAO.getAll();

        for (Nganh nganh : danhSach) {

            if (nganh.getMaKhoa().equals(khoa.getMaKhoa())) {
                cboNganh.addItem(nganh);
            }
        }
    }

    private void taiDanhSachLop() {

        model.setRowCount(0);

        List<Lop> danhSach = lopDAO.getAll();

        for (Lop lop : danhSach) {

            model.addRow(new Object[]{
                lop.getMaLop(),
                lop.getTenLop(),
                lop.getMaKhoa(),
                lop.getMaNganh()
            });
        }
    }

    private boolean kiemTraDuLieu() {

        if (txtMaLop.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã lớp!");
            txtMaLop.requestFocus();
            return false;
        }

        if (txtTenLop.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên lớp!");
            txtTenLop.requestFocus();
            return false;
        }

        if (cboKhoa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoa!");
            return false;
        }

        if (cboNganh.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngành!");
            return false;
        }

        return true;
    }

    private Lop layThongTin() {

        Khoa khoa = (Khoa) cboKhoa.getSelectedItem();
        Nganh nganh = (Nganh) cboNganh.getSelectedItem();

        return new Lop(
            txtMaLop.getText().trim(),
            txtTenLop.getText().trim(),
            khoa.getMaKhoa(),
            nganh.getMaNganh()
        );
    }

    private void themLop() {

        if (!kiemTraDuLieu()) {
            return;
        }

        if (lopDAO.insert(layThongTin())) {

            JOptionPane.showMessageDialog(this, "Thêm lớp thành công!");
            lamMoi();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Thêm lớp thất bại! Mã lớp có thể đã tồn tại.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void suaLop() {

        if (tblLop.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp cần sửa!");
            return;
        }

        if (!kiemTraDuLieu()) {
            return;
        }

        if (lopDAO.update(layThongTin())) {

            JOptionPane.showMessageDialog(this, "Cập nhật lớp thành công!");
            lamMoi();

        } else {

            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    private void xoaLop() {

        int row = tblLop.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp cần xóa!");
            return;
        }

        String maLop = model.getValueAt(row, 0).toString();

        int chon = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn xóa lớp " + maLop + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (chon == JOptionPane.YES_OPTION) {

            if (lopDAO.delete(maLop)) {

                JOptionPane.showMessageDialog(this, "Xóa lớp thành công!");
                lamMoi();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Không thể xóa! Lớp có thể đang được sử dụng trong bảng SINHVIEN.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void hienThiThongTin() {

        int row = tblLop.getSelectedRow();

        if (row < 0) {
            return;
        }

        String maKhoa = model.getValueAt(row, 2).toString();
        String maNganh = model.getValueAt(row, 3).toString();

        txtMaLop.setText(model.getValueAt(row, 0).toString());
        txtTenLop.setText(model.getValueAt(row, 1).toString());

        txtMaLop.setEditable(false);

        for (int i = 0; i < cboKhoa.getItemCount(); i++) {

            Khoa khoa = cboKhoa.getItemAt(i);

            if (khoa.getMaKhoa().equals(maKhoa)) {
                cboKhoa.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cboNganh.getItemCount(); i++) {

            Nganh nganh = cboNganh.getItemAt(i);

            if (nganh.getMaNganh().equals(maNganh)) {
                cboNganh.setSelectedIndex(i);
                break;
            }
        }
    }

    private void lamMoi() {

        txtMaLop.setText("");
        txtTenLop.setText("");

        txtMaLop.setEditable(true);

        tblLop.clearSelection();

        taiDanhSachKhoa();
        taiDanhSachNganh();
        locNganhTheoKhoa();
        taiDanhSachLop();

        txtMaLop.requestFocusInWindow();
    }

    public JPanel getPanel() {
        return this;
    }
}
