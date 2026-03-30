    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package store.ui;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import store.controller.KhachHangController;
import store.dao.impl.HoaDonDAOimpl;
import store.dao.impl.KhachHangDAOimpl;
import store.entity.HoaDon;
import store.entity.KhachHang;

public class ViewKhachHang extends javax.swing.JPanel implements KhachHangController {

    List<KhachHang> lstKhachHang = new ArrayList<>();
    KhachHangDAOimpl khachHangDAO = new KhachHangDAOimpl();
    HoaDonDAOimpl hoaDonDAO = new HoaDonDAOimpl();
    List<HoaDon> lstHoaDon = new ArrayList<>();

    public ViewKhachHang() {
        initComponents();
        lstKhachHang = khachHangDAO.findAll();
        fillToTable();

    }

    public void fillDataToForm() {
        int selectedRow = tblKhachHang.getSelectedRow();
        KhachHang kh = lstKhachHang.get(selectedRow);
        txtId.setText(String.valueOf(kh.getId()));
        txtMaKH.setText(kh.getMaKH());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        txtHoTen.setText(kh.getTenKH());
        txtSdt.setText(kh.getSoDT());
        if (kh.getTrangThai() == 1) {
            rdoKichHoat.setSelected(true);
        } else {
            rdoChuaKichHoat.setSelected(true);
        }
        if (kh.getGioiTinh() == 1) {
            rdonam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        lblTenSanPham = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblTenSanPham1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblTenSanPham2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        txtFind = new javax.swing.JTextField();
        lblTenSanPham4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        tab1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        lblTenSanPham3 = new javax.swing.JLabel();
        lblTenSanPham5 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        lblTrangThai1 = new javax.swing.JLabel();
        rdoKichHoat = new javax.swing.JRadioButton();
        rdoChuaKichHoat = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel3.setBackground(new java.awt.Color(128, 203, 196));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 600));

        lblTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham.setText("Mã Khách Hàng:");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai.setText("Giới Tính: ");

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");
        rdonam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        lblTenSanPham1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham1.setText("Địa Chỉ:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        lblTenSanPham2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham2.setText("Email:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnFind.setText("Tìm Kiếm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        lblTenSanPham4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham4.setText("ID:");

        txtId.setEditable(false);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã Khách Hàng", "Tên", "Số Điện Thoại", "Giới Tính", "Địa Chỉ", "Email", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
        }

        tab1.addTab("Khách hàng", jScrollPane2);

        lblTenSanPham3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham3.setText("Sđt:");

        lblTenSanPham5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham5.setText("Họ Tên:");

        lblTrangThai1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai1.setText("Trạng Thái:");

        buttonGroup2.add(rdoKichHoat);
        rdoKichHoat.setText("Đã kích hoạt");

        buttonGroup2.add(rdoChuaKichHoat);
        rdoChuaKichHoat.setText("Chưa kích hoạt");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSanPham3)
                            .addComponent(lblTenSanPham4)
                            .addComponent(lblTenSanPham)
                            .addComponent(lblTenSanPham5)
                            .addComponent(lblTenSanPham2)
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(174, 174, 174))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                            .addComponent(lblTenSanPham1))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGap(48, 48, 48))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdonam)
                                .addGap(67, 67, 67)
                                .addComponent(rdoNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoKichHoat)
                                .addGap(18, 18, 18)
                                .addComponent(rdoChuaKichHoat)
                                .addGap(16, 16, 16))))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi)))
                .addGap(29, 44, Short.MAX_VALUE))
            .addComponent(tab1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham4)
                            .addComponent(lblTenSanPham1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenSanPham5)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTenSanPham3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrangThai)
                            .addComponent(rdonam)
                            .addComponent(rdoNu)
                            .addComponent(lblTrangThai1)
                            .addComponent(rdoKichHoat)
                            .addComponent(rdoChuaKichHoat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSanPham2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 42, Short.MAX_VALUE)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void rdonamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonamActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            List<KhachHang> lstkhachhang = new ArrayList<>();
            String maKH = txtMaKH.getText();
            lstkhachhang = khachHangDAO.CheckTrungMaKH(maKH);
            String email = txtEmail.getText();
            KhachHang kh = getForm1();
            if (txtDiaChi.getText().isEmpty() || txtMaKH.getText().isEmpty() || txtHoTen.getText().isEmpty() || txtSdt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin!!!");
                return;
            }

            if (!txtHoTen.getText().matches("^[\\p{L} ]+$")) {
                JOptionPane.showMessageDialog(this, "Tên chỉ được chứa chữ và khoảng trắng!!!");
                return;
            }

            if (!txtSdt.getText().matches("^\\d{10,12}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và có đủ từ 10-12 số!!!");
                return;
            }

            if (!rdoNu.isSelected() && !rdonam.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui long chon gioi tinh!!!");
                return;
            }

            if (!maKH.matches("^KH\\d{3,7}$")) {
                JOptionPane.showMessageDialog(this, "Vui long nhap dung dinh dang khach hang VD:KH001 ");
                return;
            }

            if (!lstkhachhang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ma khach hang da ton tai!!!");
                return;
            }

            if (!email.isEmpty()) {
                if (!email.matches("^[\\w.+-]+@gmail\\.com$") && !kh.getEmail().matches("^[\\w.+-]+@email\\.com$")) {
                    JOptionPane.showMessageDialog(this, "Vui long nhap dung dinh dang Email!!!");
                    return;
                }
                for (KhachHang khachHang : lstKhachHang) {
                    if (khachHang.getEmail() != null && khachHang.getEmail().equalsIgnoreCase(email)) {
                        JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                        return;
                    }
                }
            }
            String sdt = txtSdt.getText().trim();
            for (KhachHang khachHang : lstKhachHang) {
                if (khachHang.getSoDT() != null && khachHang.getSoDT().equals(sdt)) {
                    JOptionPane.showMessageDialog(this, "số điện thoại đã tồn tại");
                    return;
                }
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!!!");
            if (confirm == JOptionPane.YES_OPTION) {
                khachHangDAO.create(kh);
                lstKhachHang = khachHangDAO.findAll();
                fillToTable();
                Clear();
                JOptionPane.showMessageDialog(this, "Da them thanh cong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = tblKhachHang.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui long chon hang de cap nhat!!!");
            }
            KhachHang oldKH = lstKhachHang.get(selectedRow);
            KhachHang newKH = getForm();
            String email = txtEmail.getText();
            int oldGender = lstKhachHang.get(selectedRow).getGioiTinh();
            int oldStatus = oldKH.getTrangThai();
            int newStatus = rdoKichHoat.isSelected() ? 1 : 0;
            int newGender = rdonam.isSelected() ? 1 : 0;

            KhachHang kh = getForm();
            if (txtMaKH.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hay nhap day du thong tin!!!");
                return;
            }
            if (!txtHoTen.getText().matches("^[\\p{L} ]+$")) {
                JOptionPane.showMessageDialog(this, "Tên chỉ được chứa chữ và khoảng trắng!!!");
                return;
            }

            if (!txtSdt.getText().matches("^\\d{10,12}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và có đủ từ 10-12 số!!!");
                return;
            }

            if (!txtMaKH.getText().matches("^KH\\d{3,7}$")) {
                JOptionPane.showMessageDialog(this, "Vui long nhap dung dinh dang khach hang VD:KH001 ");
                return;
            }
            if (oldKH.getMaKH().equalsIgnoreCase(newKH.getMaKH()) && oldKH.getDiaChi().equalsIgnoreCase(newKH.getDiaChi()) && oldKH.getEmail().equalsIgnoreCase(newKH.getEmail()) && newGender == oldGender
                    && newStatus == oldStatus && newKH.getSoDT().equalsIgnoreCase(oldKH.getSoDT()) && oldKH.getTenKH().equalsIgnoreCase(newKH.getTenKH())) {
                JOptionPane.showMessageDialog(this, "Thong tin chua co gi thay doi!!!");
                return;
            }
            if (!email.isEmpty()) {
                if (!email.matches("^[\\w.+-]+@gmail\\.com$") && !kh.getEmail().matches("^[\\w.+-]+@email\\.com$")) {
                    JOptionPane.showMessageDialog(this, "Vui long nhap dung dinh dang Email!!!");
                    return;
                }
                for (KhachHang khachHang : lstKhachHang) {
                    
                    if (!khachHang.getMaKH().equalsIgnoreCase(oldKH.getMaKH())) {
                        if (khachHang.getEmail() != null && khachHang.getEmail().equalsIgnoreCase(email)) {
                            JOptionPane.showMessageDialog(this, "Đã có khách hàng tồn tại email này");
                            return;
                        }
                    }
                }
            }
            String sdt = txtSdt.getText().trim();
            for (KhachHang khachHang : lstKhachHang) {
               
                if (!khachHang.getMaKH().equalsIgnoreCase(oldKH.getMaKH())) {
                    if (khachHang.getSoDT() != null && khachHang.getSoDT().equals(sdt)) {
                        JOptionPane.showMessageDialog(this, "Đã có khách hàng tồn tại số điện thoại này");
                        return;
                    }
                }
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận Sửa!!!");
            if (confirm == JOptionPane.YES_OPTION) {
                khachHangDAO.update(kh);
                lstKhachHang = khachHangDAO.findAll();
                fillToTable();
                Clear();
                JOptionPane.showMessageDialog(this, "Cap nhat thanh cong!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void Clear() {
        txtDiaChi.setText("");
        txtEmail.setText((""));
        txtMaKH.setText("");
        txtId.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        txtHoTen.setText("");
        txtSdt.setText("");
    }
    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        fillDataToForm();
        int id = Integer.valueOf(txtId.getText());


    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtEmail.setText((""));
        txtMaKH.setText("");
        txtId.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        lstKhachHang = khachHangDAO.findAll();
        fillToTable();

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

        String textFind = txtFind.getText().trim();
        if (textFind.isEmpty()) {
            lstKhachHang = khachHangDAO.findAll();
            fillToTable();
            return;
        }

        lstKhachHang = khachHangDAO.FindByEmail(textFind);
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAO.FindByMaKH(textFind);
        }
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAO.findByName(textFind);

        }
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAO.findBySdt(textFind);
        }

        if (lstKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tồn tại!!!");
            return;
        }

        fillToTable();


    }//GEN-LAST:event_btnFindActionPerformed

    private void fillToTableHoaDon() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTenSanPham1;
    private javax.swing.JLabel lblTenSanPham2;
    private javax.swing.JLabel lblTenSanPham3;
    private javax.swing.JLabel lblTenSanPham4;
    private javax.swing.JLabel lblTenSanPham5;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JRadioButton rdoChuaKichHoat;
    private javax.swing.JRadioButton rdoKichHoat;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JTabbedPane tab1;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables

    @Override
    public KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setGioiTinh(rdonam.isSelected() ? 1 : 0);
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setId(Integer.valueOf(txtId.getText()));
        kh.setTrangThai(rdoKichHoat.isSelected() ? 1 : 0);
        kh.setTenKH(txtHoTen.getText());
        kh.setSoDT(txtSdt.getText());
        return kh;

    }

    public KhachHang getForm1() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setGioiTinh(rdonam.isSelected() ? 1 : 0);
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setTrangThai(rdoKichHoat.isSelected() ? 1 : 0);
        kh.setTenKH(txtHoTen.getText());
        kh.setSoDT(txtSdt.getText());
        return kh;

    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (KhachHang khachHang : lstKhachHang) {
            Object[] row = {
                khachHang.getId(),
                khachHang.getMaKH(),
                khachHang.getTenKH(),
                khachHang.getSoDT(),
                khachHang.getGioiTinh() == 1 ? "Nam" : "Nữ",
                khachHang.getDiaChi(),
                khachHang.getEmail(),
                khachHang.getTrangThai() == 1 ? "Đã kích hoạt" : "Chưa kích hoạt"
            };
            model.addRow(row);
        }
    }

    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForm(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditable(boolean editable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void uncheckAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteCheckedItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void movePrevious() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveNext() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveTo(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
