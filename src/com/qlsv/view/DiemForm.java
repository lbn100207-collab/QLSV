/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.qlsv.view;

import com.qlsv.dao.DiemDAO;
import com.qlsv.dao.SinhVienDAO;
import com.qlsv.dao.MonHocDAO;

import com.qlsv.model.Diem;
import com.qlsv.model.SinhVien;
import com.qlsv.model.MonHoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DiemForm extends JPanel {

    private JTextField txtMaDiem;
    private JTextField txtDiemQT;
    private JTextField txtDiemThi;
    private JTextField txtDiemTK;
    private JTextField txtLanThi;

    private JComboBox<SinhVien> cboSinhVien;
    private JComboBox<MonHoc> cboMonHoc;

    private JTable tblDiem;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private DiemDAO diemDAO = new DiemDAO();
    private SinhVienDAO sinhVienDAO = new SinhVienDAO();
    private MonHocDAO monHocDAO = new MonHocDAO();

    public DiemForm() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        taoGiaoDien();
        caiDatComboBox();
        caiDatBang();
        caiDatMauNut();

        btnThem.addActionListener(e -> themDiem());
        btnSua.addActionListener(e -> suaDiem());
        btnXoa.addActionListener(e -> xoaDiem());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblDiem.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });

        taiDanhSachSinhVien();
        taiDanhSachMonHoc();
        taiDanhSachDiem();
    }

    private void taoGiaoDien() {

        JLabel lblTieuDe = new JLabel(
                "QUẢN LÝ ĐIỂM",
                SwingConstants.CENTER
        );

        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTieuDe.setForeground(new Color(30, 64, 100));

        add(lblTieuDe, BorderLayout.NORTH);

        JPanel pnlChinh = new JPanel(new BorderLayout(10, 10));
        pnlChinh.setBackground(Color.WHITE);

        JPanel pnlThongTin = new JPanel(new GridLayout(3, 4, 10, 10));
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(
                BorderFactory.createTitledBorder("Thông tin điểm")
        );

        txtMaDiem = new JTextField();
        txtMaDiem.setEditable(false);

        txtDiemQT = new JTextField();
        txtDiemThi = new JTextField();
        txtDiemTK = new JTextField();
        txtLanThi = new JTextField();

        cboSinhVien = new JComboBox<>();
        cboMonHoc = new JComboBox<>();

        pnlThongTin.add(new JLabel("Mã điểm:"));
        pnlThongTin.add(txtMaDiem);

        pnlThongTin.add(new JLabel("Sinh viên:"));
        pnlThongTin.add(cboSinhVien);

        pnlThongTin.add(new JLabel("Môn học:"));
        pnlThongTin.add(cboMonHoc);

        pnlThongTin.add(new JLabel("Điểm QT:"));
        pnlThongTin.add(txtDiemQT);

        pnlThongTin.add(new JLabel("Điểm thi:"));
        pnlThongTin.add(txtDiemThi);

        pnlThongTin.add(new JLabel("Điểm TK:"));
        pnlThongTin.add(txtDiemTK);

        JPanel pnlLanThi = new JPanel(new GridLayout(1, 2, 10, 10));
        pnlLanThi.setBackground(Color.WHITE);
        pnlLanThi.add(new JLabel("Lần thi:"));
        pnlLanThi.add(txtLanThi);

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
        pnlTren.add(pnlLanThi, BorderLayout.SOUTH);
        pnlTren.add(pnlNut, BorderLayout.PAGE_END);

        model = new DefaultTableModel(
                new String[]{
                    "Mã điểm",
                    "Mã sinh viên",
                    "Mã môn học",
                    "Điểm QT",
                    "Điểm thi",
                    "Điểm TK",
                    "Lần thi"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDiem = new JTable(model);

        pnlChinh.add(pnlTren, BorderLayout.NORTH);
        pnlChinh.add(new JScrollPane(tblDiem), BorderLayout.CENTER);

        add(pnlChinh, BorderLayout.CENTER);
    }

    private void caiDatComboBox() {

        cboSinhVien.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof SinhVien) {
                    SinhVien sv = (SinhVien) value;
                    setText(sv.getMaSV() + " - "
                            + sv.getHoSV() + " " + sv.getTenSV());
                }

                return this;
            }
        });

        cboMonHoc.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof MonHoc) {
                    MonHoc mh = (MonHoc) value;
                    setText(mh.getMaMH() + " - " + mh.getTenMH());
                }

                return this;
            }
        });
    }

    private void caiDatBang() {
        tblDiem.setRowHeight(28);
        tblDiem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDiem.getTableHeader().setFont(
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

    private void taiDanhSachSinhVien() {

        cboSinhVien.removeAllItems();

        try {
            List<SinhVien> danhSach = sinhVienDAO.getAll();

            for (SinhVien sv : danhSach) {
                cboSinhVien.addItem(sv);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi tải sinh viên: " + e.getMessage());
        }
    }

    private void taiDanhSachMonHoc() {

        cboMonHoc.removeAllItems();

        try {
            List<MonHoc> danhSach = monHocDAO.getAll();

            for (MonHoc mh : danhSach) {
                cboMonHoc.addItem(mh);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi tải môn học: " + e.getMessage());
        }
    }

    private Diem layThongTin() {

        try {
            SinhVien sv = (SinhVien) cboSinhVien.getSelectedItem();
            MonHoc mh = (MonHoc) cboMonHoc.getSelectedItem();

            if (sv == null || mh == null) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng chọn sinh viên và môn học!");
                return null;
            }

            String qt = txtDiemQT.getText().trim();
            String thi = txtDiemThi.getText().trim();
            String tk = txtDiemTK.getText().trim();
            String lan = txtLanThi.getText().trim();

            if (qt.isEmpty() || thi.isEmpty()
                    || tk.isEmpty() || lan.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập đầy đủ thông tin điểm!");
                return null;
            }

            BigDecimal diemQT = new BigDecimal(qt);
            BigDecimal diemThi = new BigDecimal(thi);
            BigDecimal diemTK = new BigDecimal(tk);

            int lanThi = Integer.parseInt(lan);

            if (!hopLeDiem(diemQT)
                    || !hopLeDiem(diemThi)
                    || !hopLeDiem(diemTK)) {
                JOptionPane.showMessageDialog(this,
                        "Điểm phải nằm trong khoảng từ 0 đến 10!");
                return null;
            }

            if (lanThi < 1) {
                JOptionPane.showMessageDialog(this,
                        "Lần thi phải lớn hơn hoặc bằng 1!");
                return null;
            }

            String maDiemText = txtMaDiem.getText().trim();
            int maDiem = maDiemText.isEmpty()
                    ? 0 : Integer.parseInt(maDiemText);

            return new Diem(
                    maDiem,
                    sv.getMaSV(),
                    mh.getMaMH(),
                    diemQT,
                    diemThi,
                    diemTK,
                    lanThi
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập điểm dạng số và lần thi dạng số nguyên!");
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi nhập dữ liệu: " + e.getMessage());
            return null;
        }
    }

    private boolean hopLeDiem(BigDecimal diem) {
        return diem.compareTo(BigDecimal.ZERO) >= 0
                && diem.compareTo(BigDecimal.TEN) <= 0;
    }

    private void themDiem() {

        Diem diem = layThongTin();

        if (diem == null) {
            return;
        }

        try {
            if (diemDAO.insert(diem)) {
                JOptionPane.showMessageDialog(this,
                        "Thêm điểm thành công!");
                taiDanhSachDiem();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể thêm điểm!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void suaDiem() {

        if (txtMaDiem.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn điểm cần sửa!");
            return;
        }

        Diem diem = layThongTin();

        if (diem == null) {
            return;
        }

        try {
            if (diemDAO.update(diem)) {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật điểm thành công!");
                taiDanhSachDiem();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể cập nhật điểm!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void xoaDiem() {

        String maDiem = txtMaDiem.getText().trim();

        if (maDiem.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn điểm cần xóa!");
            return;
        }

        int chon = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa điểm này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (chon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (diemDAO.delete(Integer.parseInt(maDiem))) {
                JOptionPane.showMessageDialog(this,
                        "Xóa điểm thành công!");
                taiDanhSachDiem();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không thể xóa điểm!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage());
        }
    }

    private void taiDanhSachDiem() {

        model.setRowCount(0);

        try {
            List<Diem> danhSach = diemDAO.getAll();

            for (Diem d : danhSach) {
                model.addRow(new Object[]{
                    d.getMaDiem(),
                    d.getMaSV(),
                    d.getMaMH(),
                    d.getDiemQT(),
                    d.getDiemThi(),
                    d.getDiemTK(),
                    d.getLanThi()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi tải danh sách điểm: " + e.getMessage());
        }
    }

    private void hienThiThongTin() {

        int row = tblDiem.getSelectedRow();

        if (row < 0) {
            return;
        }

        txtMaDiem.setText(model.getValueAt(row, 0).toString());

        String maSV = model.getValueAt(row, 1).toString();
        String maMH = model.getValueAt(row, 2).toString();

        chonSinhVien(maSV);
        chonMonHoc(maMH);

        txtDiemQT.setText(model.getValueAt(row, 3).toString());
        txtDiemThi.setText(model.getValueAt(row, 4).toString());
        txtDiemTK.setText(model.getValueAt(row, 5).toString());
        txtLanThi.setText(model.getValueAt(row, 6).toString());
    }

    private void chonSinhVien(String maSV) {
        for (int i = 0; i < cboSinhVien.getItemCount(); i++) {
            SinhVien sv = cboSinhVien.getItemAt(i);

            if (sv.getMaSV().equals(maSV)) {
                cboSinhVien.setSelectedIndex(i);
                return;
            }
        }
    }

    private void chonMonHoc(String maMH) {
        for (int i = 0; i < cboMonHoc.getItemCount(); i++) {
            MonHoc mh = cboMonHoc.getItemAt(i);

            if (mh.getMaMH().equals(maMH)) {
                cboMonHoc.setSelectedIndex(i);
                return;
            }
        }
    }

    private void lamMoi() {

        txtMaDiem.setText("");
        txtDiemQT.setText("");
        txtDiemThi.setText("");
        txtDiemTK.setText("");
        txtLanThi.setText("");

        if (cboSinhVien.getItemCount() > 0) {
            cboSinhVien.setSelectedIndex(0);
        }

        if (cboMonHoc.getItemCount() > 0) {
            cboMonHoc.setSelectedIndex(0);
        }

        tblDiem.clearSelection();
    }

    public JPanel getPanel() {
        return this;
    }
}
