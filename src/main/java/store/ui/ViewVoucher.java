/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package store.ui;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import store.controller.PhieuGiamGiaController;
import store.dao.impl.PhieuGiamGiaDAOimpl;
import store.entity.PhieuGiamGia;

public class ViewVoucher extends javax.swing.JPanel {

    List<PhieuGiamGia> lstPhieuGiamGia = new ArrayList<>();
    PhieuGiamGiaDAOimpl phieuGiamGiaDAO = new PhieuGiamGiaDAOimpl();

    DefaultComboBoxModel mVou = new DefaultComboBoxModel();

    public ViewVoucher() {
        initComponents();
        fillToTable();
        fillCbo();
        txtGiamToiDa.setText("0");
        txtGiamToiDa.setEditable(false);
        capNhatTrangThaiTuDong();
        jdcNgayBatDau.setDate(new java.util.Date());
        jdcNgayKetThuc.setDate(new java.util.Date());
    }

    public void fillCbo() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoai.getModel();
        model.removeAllElements();
        model.addElement("--Chọn loại giảm giá--");
        model.addElement("Theo %");
        model.addElement("Theo giá trị");

    }

    public void fillForm(int index) {
        lstPhieuGiamGia = phieuGiamGiaDAO.findAll();
        PhieuGiamGia pgg = lstPhieuGiamGia.get(index);

        DecimalFormat df = new DecimalFormat("#,###");

        txtId.setText(String.valueOf(pgg.getId()));
        txtdieuKien.setText(df.format(pgg.getDieuKienAD()));
        txtMa.setText(pgg.getMaPhieu());

        if (pgg.getLoai() == 1) {
            txtGiaTRiGiam.setText(String.valueOf(pgg.getGiaTriGiam().intValue()));
        } else {
            txtGiaTRiGiam.setText(df.format(pgg.getGiaTriGiam()));
        }
        txtTen.setText(pgg.getTen());
        jdcNgayBatDau.setDate(pgg.getNgayTao());
        jdcNgayKetThuc.setDate(pgg.getNgayHetHan());

        txtGiamToiDa.setText(df.format(pgg.getGiamToiDa()));
        txtSoLuong.setText(String.valueOf(pgg.getSoLuong()));
        if (pgg.getLoai() == 1) {
            cboLoai.setSelectedIndex(1);
        } else if (pgg.getLoai() == 2) {
            cboLoai.setSelectedIndex(2);
        } else {
            cboLoai.setSelectedIndex(0);
        }
    }

  public PhieuGiamGia getForm() {
        int selectedRow = tblVoucher.getSelectedRow();
        PhieuGiamGia pgg = new PhieuGiamGia();
        pgg.setId(Integer.valueOf(txtId.getText()));
        pgg.setLoai(cboLoai.getSelectedIndex());
        pgg.setDieuKienAD(new BigDecimal(txtdieuKien.getText().replace(",", "")));
        String giaTriStr = txtGiaTRiGiam.getText().trim().replace(",", "");
        pgg.setGiaTriGiam(new BigDecimal(giaTriStr));
        if (pgg.getLoai() == 2) {
            pgg.setGiamToiDa(pgg.getGiaTriGiam());
        } else {
            String giamToiDaStr = txtGiamToiDa.getText().trim().replace(",", "");
            pgg.setGiamToiDa(new BigDecimal(giamToiDaStr));
        }

        pgg.setMaPhieu(txtMa.getText());
        pgg.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        java.util.Date ngayTao = jdcNgayBatDau.getDate();
        java.util.Date ngayHetHan = jdcNgayKetThuc.getDate();

        if (ngayTao != null) {
            pgg.setNgayTao(new java.sql.Date(ngayTao.getTime()));
        }
        if (ngayHetHan != null) {
            pgg.setNgayHetHan(new java.sql.Date(ngayHetHan.getTime()));

            LocalDate today = LocalDate.now();
            LocalDate ngayTaoLocal = pgg.getNgayTao().toLocalDate();
            LocalDate ngayHetHanLocal = pgg.getNgayHetHan().toLocalDate();

            pgg.setTen(txtTen.getText());

            if ((today.isEqual(ngayTaoLocal) || today.isAfter(ngayTaoLocal))
                    && (today.isEqual(ngayHetHanLocal) || today.isBefore(ngayHetHanLocal))) {
                pgg.setTrangThai(1);
            } else if (today.isAfter(ngayHetHanLocal)) {
                pgg.setTrangThai(0);
            } else if (today.isBefore(ngayTaoLocal)) {
                pgg.setTrangThai(2);
            }
            return pgg;
        };
        return null;

    }
  
  public PhieuGiamGia getForm2() {
        int selectedRow = tblVoucher.getSelectedRow();
        PhieuGiamGia pgg = new PhieuGiamGia();
        
        pgg.setLoai(cboLoai.getSelectedIndex());
        pgg.setDieuKienAD(new BigDecimal(txtdieuKien.getText().replace(",", "")));
        String giaTriStr = txtGiaTRiGiam.getText().trim().replace(",", "");
        pgg.setGiaTriGiam(new BigDecimal(giaTriStr));
        if (pgg.getLoai() == 2) {
            pgg.setGiamToiDa(pgg.getGiaTriGiam());
        } else {
            String giamToiDaStr = txtGiamToiDa.getText().trim().replace(",", "");
            pgg.setGiamToiDa(new BigDecimal(giamToiDaStr));
        }

        pgg.setMaPhieu(txtMa.getText());
        pgg.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        java.util.Date ngayTao = jdcNgayBatDau.getDate();
        java.util.Date ngayHetHan = jdcNgayKetThuc.getDate();

        if (ngayTao != null) {
            pgg.setNgayTao(new java.sql.Date(ngayTao.getTime()));
        }
        if (ngayHetHan != null) {
            pgg.setNgayHetHan(new java.sql.Date(ngayHetHan.getTime()));

            LocalDate today = LocalDate.now();
            LocalDate ngayTaoLocal = pgg.getNgayTao().toLocalDate();
            LocalDate ngayHetHanLocal = pgg.getNgayHetHan().toLocalDate();

            pgg.setTen(txtTen.getText());

            if ((today.isEqual(ngayTaoLocal) || today.isAfter(ngayTaoLocal))
                    && (today.isEqual(ngayHetHanLocal) || today.isBefore(ngayHetHanLocal))) {
                pgg.setTrangThai(1);
            } else if (today.isAfter(ngayHetHanLocal)) {
                pgg.setTrangThai(0);
            } else if (today.isBefore(ngayTaoLocal)) {
                pgg.setTrangThai(2);
            }
            return pgg;
        };
        return null;

    }
    public void capNhatTrangThaiTuDong() {
    phieuGiamGiaDAO.updateTrangThaiAuto(); 
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdieuKien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtGiamToiDa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTRiGiam = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcNgayBatDau = new com.toedter.calendar.JDateChooser();
        jdcNgayKetThuc = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(128, 203, 196));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Giảm Giá", "Ten", "Loại", "giaTriGiam", "giamToiDa", "dieuKienAD", "soLuong", "trangThai", "NgayTao", "Ngày Hết Hạn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setRowHeight(25);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);
        if (tblVoucher.getColumnModel().getColumnCount() > 0) {
            tblVoucher.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher.getColumnModel().getColumn(6).setResizable(false);
            tblVoucher.getColumnModel().getColumn(7).setResizable(false);
            tblVoucher.getColumnModel().getColumn(8).setResizable(false);
            tblVoucher.getColumnModel().getColumn(9).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(128, 203, 196));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(128, 203, 196));
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(128, 203, 196));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Trạng thái");
        jLabel7.setName(""); // NOI18N

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng Thái", "Đã hết hạn", "Đang kích hoạt", "Chưa diễn ra" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản Lý Voucher");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu :");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc :");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Số lượng:");

        txtdieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdieuKienActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Điều kiện:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên:");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã Giảm Giá");

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        txtGiamToiDa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamToiDaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giảm tối đa");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Loại");

        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giá trị giảm");

        txtGiaTRiGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTRiGiamActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Id:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(33, 33, 33)
                        .addComponent(txtGiamToiDa))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaTRiGiam))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(75, 75, 75)
                        .addComponent(cboLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMa))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(87, 87, 87)
                        .addComponent(txtTen))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(55, 55, 55)
                        .addComponent(txtdieuKien))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(58, 58, 58)
                        .addComponent(txtSoLuong))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jdcNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jdcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(txtdieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12))
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jdcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addComponent(txtGiaTRiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11))
                            .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int index = tblVoucher.getSelectedRow();
        fillForm(index);

        LocalDate today = LocalDate.now();
        LocalDate ngayHetHan = null;
        java.util.Date date = jdcNgayKetThuc.getDate();
        if (date != null) {
            ngayHetHan = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        btnSua.setVisible(ngayHetHan != null && !today.isAfter(ngayHetHan));
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clear();
        
    }//GEN-LAST:event_btnLamMoiActionPerformed

    public void clear() {
        txtId.setText("");
        txtTen.setText("");
        txtMa.setText("");
        txtGiaTRiGiam.setText("");
        txtGiamToiDa.setText("0");
        txtdieuKien.setText("");
        txtSoLuong.setText("");
        jdcNgayBatDau.setDate(new java.util.Date());
        jdcNgayKetThuc.setDate(new java.util.Date());
        cboLoai.setSelectedIndex(0);
        tblVoucher.clearSelection();
    }


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int soLuong;
        String ten = txtTen.getText();
        lstPhieuGiamGia = phieuGiamGiaDAO.findName(ten);
        PhieuGiamGia pgg = getForm2();
        if (txtGiaTRiGiam.getText().isEmpty() || txtMa.getText().isEmpty()
                || txtSoLuong.getText().isEmpty()
                || txtTen.getText().isEmpty() || txtdieuKien.getText().isEmpty() || cboLoai.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin Voucher!!!");
            return;
        }
        PhieuGiamGia PGG = phieuGiamGiaDAO.checkTrung(txtMa.getText().trim());
        if (PGG != null) {
            JOptionPane.showMessageDialog(this, "Mã Voucher đã tồn tại!!!");
            return;
        }
        if (!lstPhieuGiamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ten voucher bi trung!!!");
            return;
        }

        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ!");
            return;
        }
        double giaApDung = Double.valueOf(txtdieuKien.getText().replace(",", ""));
        double giaTriGiam = Double.valueOf(txtGiaTRiGiam.getText().replace(",", ""));
        if (giaTriGiam <= 0) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không hợp lệ!!!");
            return;
        }
        if (soLuong < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
            return;
        }
        if (giaTriGiam > giaApDung) {
            JOptionPane.showMessageDialog(this, "Giá áp dụng phải lớn hơn giá trị giảm! ");
            return;
        }
        String dieuKienStr = txtdieuKien.getText().trim().replace(",", "");
        if (!txtGiamToiDa.getText().isEmpty()) {
            String giamToiDaStr = txtGiamToiDa.getText().trim().replace(",", "");
        }

        if (!dieuKienStr.matches("\\d+(?:[.,]\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Điều kiện áp dụng phải là số hợp lệ!");
            return;
        }

        if (!txtGiamToiDa.getText().matches("\\d+(?:[.,]\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giảm tối đa phải là số hợp lệ!");
            return;
        }

        long dieuKienAD = Long.parseLong(dieuKienStr);
        long giamToiDa = 0;
        if (!txtGiamToiDa.getText().isEmpty()) {
            String giamToiDaStr = txtGiamToiDa.getText().trim().replace(",", "");
            giamToiDa = Long.parseLong(giamToiDaStr);
        }

        if (dieuKienAD <= 0) {
            JOptionPane.showMessageDialog(this, "Điều kiện áp dụng phải lớn hơn 0!");
            return;
        }

        if (dieuKienAD <= giamToiDa) {
            JOptionPane.showMessageDialog(this, "Điều kiện áp dụng phải lớn hơn giảm tối đa!");
            return;
        }

        // Kiểm tra ngày hợp lệ
        java.util.Date ngayBatDau = jdcNgayBatDau.getDate();
        java.util.Date ngayKetThuc = jdcNgayKetThuc.getDate();

        LocalDate ngayTaoLocal = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayHetHanLocal = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        if (ngayTaoLocal.isAfter(ngayHetHanLocal)) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn phải sau hoặc bằng ngày tạo!");
            return;
        }

        Date homNay = new Date(System.currentTimeMillis());
        if (ngayTaoLocal.isBefore(today)) {
            JOptionPane.showMessageDialog(this, "Ngày tạo phải từ hôm nay trở đi!");
            return;
        }

        int loai = cboLoai.getSelectedIndex();
        if (loai == 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn loại giảm giá!!!");
            return;
        }

        String value = txtGiaTRiGiam.getText().trim();
        String plainNumber = value.replace(",", "");

        if (plainNumber.isEmpty() || !plainNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm phải là số!");
            return;
        }

        switch (loai) {
            case 1:
                if (!value.matches("\\d{1,2}")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập giá trị giảm theo dạng % (1-2 chữ số)!");
                    return;
                }
                break;
            case 2:
                if (!value.matches("[1-9]\\d*(,\\d{3})*")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập giá trị giảm dạng tiền mặt cố định (ví dụ: 10000 hoặc 10,000)!");
                    return;
                }

                if (plainNumber.length() < 5) {
                    JOptionPane.showMessageDialog(this, "Giá trị giảm phải từ 5 chữ số trở lên!");
                    return;
                }
                break;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm?");
        if (confirm == JOptionPane.YES_OPTION) {
            phieuGiamGiaDAO.create(pgg);
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công!");
            clear();
        }
    }//GEN-LAST:event_btnThemActionPerformed


    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String id = txtId.getText();
        int soLuong;
        int selectedRow = tblVoucher.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chua chon doi tuong sua!!!");
            return;
        }
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ!");
            return;
        }
        if (soLuong < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
            return;
        }
        double giaApDung = Double.valueOf(txtdieuKien.getText().replace(",", ""));
        double giaTriGiam = Double.valueOf(txtGiaTRiGiam.getText().replace(",", ""));
        if (giaTriGiam <= 0) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không hợp lệ!!!");
            return;
        }
        String dieuKienStr = txtdieuKien.getText().trim().replace(",", "");
        String giamToiDaStr = txtGiamToiDa.getText().trim().replace(",", "");

        if (!dieuKienStr.matches("\\d+(?:[.,]\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Điều kiện áp dụng phải là số hợp lệ!");
            return;
        }

        if (!giamToiDaStr.matches("\\d+(?:[.,]\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giảm tối đa phải là số hợp lệ!");
            return;
        }

        long dieuKienAD = Long.parseLong(dieuKienStr);
        long giamToiDa = 0;
        if (!txtGiamToiDa.getText().isEmpty()) {
            giamToiDa = Long.parseLong(giamToiDaStr);
        }

// ✅ Điều kiện áp dụng > 0
        if (dieuKienAD <= 0) {
            JOptionPane.showMessageDialog(this, "Điều kiện áp dụng phải lớn hơn 0!");
            return;
        }

        java.util.Date ngayBatDau = jdcNgayBatDau.getDate();
        java.util.Date ngayKetThuc = jdcNgayKetThuc.getDate();

        LocalDate ngayTaoLocal = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayHetHanLocal = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        if (ngayTaoLocal.isAfter(ngayHetHanLocal)) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn phải sau hoặc bằng ngày tạo!");
            return;
        }

        Date homNay = new Date(System.currentTimeMillis());

        int loai = cboLoai.getSelectedIndex();
        if (loai == 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn loại giảm giá!!!");
            return;
        }

        String value = txtGiaTRiGiam.getText().trim();
        String plainNumber = value.replace(",", "");

        if (plainNumber.isEmpty() || !plainNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm phải là số!");
            return;
        }

        switch (loai) {
            case 1:
                if (!value.matches("\\d{1,2}")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập giá trị giảm theo dạng % (1-2 chữ số)!");
                    return;
                }
                break;
            case 2:
                if (!value.matches("[1-9]\\d*(,\\d{3})*")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập giá trị giảm dạng tiền mặt cố định (ví dụ: 10000 hoặc 10,000)!");
                    return;
                }

                if (plainNumber.length() < 5) {
                    JOptionPane.showMessageDialog(this, "Giá trị giảm phải từ 5 chữ số trở lên!");
                    return;
                }
                break;
        }

        PhieuGiamGia pggMoi = getForm();
        PhieuGiamGia pggCu = phieuGiamGiaDAO.findById(Integer.valueOf(id));
        if (pggCu.getDieuKienAD().compareTo(pggMoi.getDieuKienAD()) == 0
                && pggCu.getGiaTriGiam().compareTo(pggMoi.getGiaTriGiam()) == 0
                && pggCu.getGiamToiDa().compareTo(pggMoi.getGiamToiDa()) == 0
                && pggCu.getId() == pggMoi.getId()
                && pggCu.getLoai() == pggMoi.getLoai()
                && pggCu.getMaPhieu().equalsIgnoreCase(pggMoi.getMaPhieu())
                && pggCu.getNgayHetHan().equals(pggMoi.getNgayHetHan())
                && pggCu.getNgayTao().equals(pggMoi.getNgayTao())
                && pggCu.getSoLuong() == pggMoi.getSoLuong()
                && pggCu.getTrangThai() == pggMoi.getTrangThai()) {
            JOptionPane.showMessageDialog(this, "Chua co gi thay doi!!!");
            return;
        }

        if (!pggCu.getTen().equalsIgnoreCase(pggMoi.getTen())) {
            String ten = txtTen.getText();
            lstPhieuGiamGia = phieuGiamGiaDAO.findName(ten);
            if (!lstPhieuGiamGia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ten voucher bi trung!!!");
                return;
            }
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật?");
        if (confirm == JOptionPane.YES_OPTION) {
            phieuGiamGiaDAO.update(pggMoi);
            clear();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!!!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed


    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:
        if (cboLoai.getSelectedIndex() == 2) {
            txtGiamToiDa.setEditable(false);
        } else {
            txtGiamToiDa.setEditable(true);
        }
    }//GEN-LAST:event_cboLoaiActionPerformed

    private void txtGiamToiDaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamToiDaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamToiDaActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtdieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdieuKienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdieuKienActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtGiaTRiGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTRiGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTRiGiamActionPerformed

    
    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        if (cboTrangThai.getSelectedIndex() == 0) {
            fillToTable();
        } else if (cboTrangThai.getSelectedIndex() == 1) {
            int trangThai = 0;
            lstPhieuGiamGia = phieuGiamGiaDAO.FindByTrangThai(trangThai);
            fillToTable2();
        } else if (cboTrangThai.getSelectedIndex() == 2) {
            int trangThai = 1;
            lstPhieuGiamGia = phieuGiamGiaDAO.FindByTrangThai(trangThai);
            fillToTable2();
        } else if (cboTrangThai.getSelectedIndex() == 3) {
            int trangThai = 2;
            lstPhieuGiamGia = phieuGiamGiaDAO.FindByTrangThai(trangThai);
            fillToTable2();
        }
    }//GEN-LAST:event_cboTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgayBatDau;
    private com.toedter.calendar.JDateChooser jdcNgayKetThuc;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtGiaTRiGiam;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtdieuKien;
    // End of variables declaration//GEN-END:variables

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        lstPhieuGiamGia = phieuGiamGiaDAO.findAll();

        DecimalFormat df = new DecimalFormat("#,###");

        for (PhieuGiamGia phieuGiamGia : lstPhieuGiamGia) {
            Object[] row = {
                phieuGiamGia.getId(),
                phieuGiamGia.getMaPhieu(),
                phieuGiamGia.getTen(),
                phieuGiamGia.getLoai() == 1 ? "Giảm theo %" : "Giảm cố định",
                formatNumberSafe(phieuGiamGia.getGiaTriGiam(), df)
                + (phieuGiamGia.getLoai() == 1 ? " %" : " đ"),
                formatNumberSafe(phieuGiamGia.getGiamToiDa(), df) + " đ",
                formatNumberSafe(phieuGiamGia.getDieuKienAD(), df) + " đ",
                formatNumberSafe(phieuGiamGia.getSoLuong(), df),
                phieuGiamGia.getTrangThai() == 1 ? "Đang kích hoạt"
                : (phieuGiamGia.getTrangThai() == 0 ? "Đã hết hạn" : "Chưa khả dụng"),
                phieuGiamGia.getNgayTao(),
                phieuGiamGia.getNgayHetHan()
            };
            model.addRow(row);
        }
    }

    public void fillToTable2() {
        DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#,###");

        for (PhieuGiamGia phieuGiamGia : lstPhieuGiamGia) {
            Object[] row = {
                phieuGiamGia.getId(),
                phieuGiamGia.getMaPhieu(),
                phieuGiamGia.getTen(),
                phieuGiamGia.getLoai() == 1 ? "Giảm theo %" : "Giảm cố định",
                formatNumberSafe(phieuGiamGia.getGiaTriGiam(), df)
                + (phieuGiamGia.getLoai() == 1 ? " %" : " đ"),
                formatNumberSafe(phieuGiamGia.getGiamToiDa(), df) + " đ",
                formatNumberSafe(phieuGiamGia.getDieuKienAD(), df) + " đ",
                formatNumberSafe(phieuGiamGia.getSoLuong(), df),
                phieuGiamGia.getTrangThai() == 1 ? "Đang kích hoạt"
                : (phieuGiamGia.getTrangThai() == 0 ? "Đã hết hạn" : "Chưa khả dụng"),
                phieuGiamGia.getNgayTao(),
                phieuGiamGia.getNgayHetHan()
            };
            model.addRow(row);
        }
    }

    private String formatNumberSafe(Object value, DecimalFormat df) {
        if (value == null) {
            return "";
        }
        if (value instanceof Number) {
            return df.format(value);
        }
        try {
            return df.format(Double.parseDouble(value.toString()));
        } catch (NumberFormatException e) {
            return value.toString(); // nếu không phải số thì trả nguyên chuỗi
        }
    }

}
