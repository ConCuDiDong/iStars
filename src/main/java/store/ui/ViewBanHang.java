package store.ui;

import java.awt.Frame;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import store.dao.impl.ChiTietSanPhamDAOImpl;
import store.dao.impl.HoaDonChiTietDAOimpl;
import store.dao.impl.HoaDonDAOimpl;
import store.dao.impl.ImeiDAOimpl;
import store.dao.impl.MauSacDAOimpl;
import store.dao.impl.RomDAOimpl;
import store.dao.impl.SanPhamDAOimpl;
import store.entity.ChiTietSanPham;
import store.entity.HoaDon;
import store.entity.HoaDonChiTiet;
import store.entity.KhachHang;
import store.util.XAuth;
import java.sql.Date;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import store.dao.impl.ImeiDaBanDAOimpl;
import store.dao.impl.KhachHangDAOimpl;
import store.dao.impl.PhieuGiamGiaDAOimpl;
import store.entity.Imei;
import store.entity.PhieuGiamGia;

public class ViewBanHang extends javax.swing.JPanel {

    List<HoaDon> listHD = new ArrayList<>();
    HoaDonDAOimpl hdDao = new HoaDonDAOimpl();
    KhachHangDAOimpl khDao = new KhachHangDAOimpl();
    PhieuGiamGiaDAOimpl pggDao = new PhieuGiamGiaDAOimpl();
    ChiTietSanPhamDAOImpl ctspDao = new ChiTietSanPhamDAOImpl();
    HoaDonChiTietDAOimpl hdctDao = new HoaDonChiTietDAOimpl();
    ImeiDAOimpl imeiDao = new ImeiDAOimpl();
    SanPhamDAOimpl spDao = new SanPhamDAOimpl();
    MauSacDAOimpl msDao = new MauSacDAOimpl();
    RomDAOimpl romDao = new RomDAOimpl();
    ImeiDaBanDAOimpl imeiDB = new ImeiDaBanDAOimpl();
    DefaultTableModel modelHD = new DefaultTableModel();
    DefaultTableModel modelCT = new DefaultTableModel();
    int idHoaDon = 0;
    private HoaDon bill;
    @Setter
    KhachHang khSet;

    public ViewBanHang() {
        initComponents();
        fillToCbo();
        fillToHDtable();
        fillToCTSPTable();
        txtMaHoaDon.setEditable(false);
    }

    public void fillToCbo() {
//        DefaultComboBoxModel mKhachHang = (DefaultComboBoxModel) cboMaKH.getModel();
//        mKhachHang.removeAllElements();
//        for (KhachHang khachHang : khDao.findAll()) {
//            mKhachHang.addElement(khachHang);
//        }
        DefaultComboBoxModel mPGG = (DefaultComboBoxModel) cboVoucher.getModel();
        mPGG.removeAllElements();

        for (PhieuGiamGia phieuGiamGia : pggDao.findAll2()) {
            mPGG.addElement(phieuGiamGia);
        }
    }
    @Setter
    HoaDon HoaDon;
    @Setter
    List<Imei> imeisDaChon;

    public void fillToHDtable() {
        modelHD = (DefaultTableModel) tblHoaDonBan.getModel();
        modelHD.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,###");
        for (HoaDon hd : hdDao.tongTien()) {
            KhachHang kh = khDao.findOne(hd.getIdKH());
            String SDT = "";
            if (kh != null) {
                SDT = kh.getSoDT();
            }
            modelHD.addRow(new Object[]{
                hd.getId(), hd.getMaHoaDon(), SDT, df.format(hd.getTongGia()), df.format(hd.getTongGiaSauPGG()), hd.getNgayTao()
            });
        }
    }

    public void fillFormHD(int index) {
        listHD = hdDao.findOneByAvailaible();
        HoaDon hd = listHD.get(index);
        KhachHang kh = khDao.findOne(hd.getIdKH());
        txtId.setText(String.valueOf(hd.getId()));
        txtKhachHang.setText(kh.getTenKH());
        txtSDT.setText(kh.getSoDT());
        txtMaHoaDon.setText(hd.getMaHoaDon());

        cboVoucher.setSelectedItem(pggDao.findOne(hd.getMaPGG()));
    }

    public void fillToCTSPTable() {
        String[] columns = {"ID", "Tên sản phẩm", "Dung lượng ", "Màu sắc", "Giá", "Số lượng"};

        DefaultTableModel modelCT = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DecimalFormat df = new DecimalFormat("#,###");
        for (ChiTietSanPham ctsp : ctspDao.findOneByAV()) {
            int dungLuong = romDao.findOne(ctsp.getIdRom()).getDungLuong();

            String dungLuongHienThi;
            if (dungLuong >= 1024) {           // >= 1 TB
                dungLuongHienThi = (dungLuong / 1024) + " TB";
            } else {                            // < 1 TB
                dungLuongHienThi = dungLuong + " GB";
            }
            Object[] row = {
                ctsp.getId(),
                spDao.findOne(ctsp.getIdSP()).getTen(),
                dungLuongHienThi,
                msDao.findOne(ctsp.getIdMau()).getTen(),
                df.format(ctsp.getGia()),
                ctsp.getSoLuong()
            };
            modelCT.addRow(row);
        }
        tblSanPhamCT.setModel(modelCT);
    }

    public void fillToHDCTTable(int index) {
        DefaultTableModel ghModel = (DefaultTableModel) tblGioHang.getModel();
        ghModel.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,###");
        for (HoaDonChiTiet hdct : hdctDao.findAllByidHD(index)) {
            ghModel.addRow(new Object[]{hdct.getId(), ctspDao.findOne(hdct.getIdSanPham()).getGhiChu(),
                df.format(ctspDao.findOne(hdct.getIdSanPham()).getGia()), hdct.getSoLuong()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonBan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiemGioHang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        cboVoucher = new javax.swing.JComboBox<>();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtMaHoaDon = new javax.swing.JTextField();
        btnTHemKh = new javax.swing.JButton();
        btnApDungVOUCHER = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoaDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã hóa đơn", "SDT khách hàng ", "Tổng tiền", "Tổng tiền sau giảm", "Ngày tạo"
            }
        ));
        tblHoaDonBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonBanMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDonBan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(128, 203, 196));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiem.setText("Tìm Kiếm");

        tblSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên sản phẩm", "Dung lượng", "Màu sắc", "Giá", "Số lượng"
            }
        ));
        tblSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamCT);

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(128, 203, 196));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sản Phẩm", "Đơn Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(25);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(128, 203, 196));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Id");

        jLabel2.setText("Email KKH");

        jLabel3.setText("Tên người nhận ");

        jLabel4.setText("Số Điện Thoại");

        jLabel5.setText("Mã Hóa Đơn");

        jLabel11.setText("Voucher");

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        cboVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVoucherActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setText("Làm Mới");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTHemKh.setText("Thêm khách hàng ");
        btnTHemKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTHemKhActionPerformed(evt);
            }
        });

        btnApDungVOUCHER.setText("Áp dụng");
        btnApDungVOUCHER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungVOUCHERActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(btnTHemKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnApDungVOUCHER)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboVoucher, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(12, 12, 12))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel11, jLabel2, jLabel3, jLabel4, jLabel5});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnTaoHoaDon, btnThanhToan});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTHemKh))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(59, 59, 59)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(btnApDungVOUCHER)
                .addGap(51, 51, 51)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboVoucher, jLabel1, jLabel11, jLabel2, jLabel3, jLabel4, jLabel5, txtId, txtKhachHang, txtSDT});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnTaoHoaDon, btnThanhToan});

        btnRemove.setText("Xóa toàn bộ số lượng sản phẩm đã chọn ");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        String ma = txtMaHoaDon.getText();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn tạo hóa đơn ?");
        if (confirm == JOptionPane.YES_OPTION) {

            int maxSo = 0;
            for (HoaDon hoaDon : hdDao.findAll()) {
                String ma2 = hoaDon.getMaHoaDon();
                if (ma2 != null && ma2.startsWith("HD")) {
                    try {
                        int so = Integer.parseInt(ma2.substring(2));
                        if (so > maxSo) {
                            maxSo = so;
                        }
                    } catch (NumberFormatException e) {

                    }
                }
            }
            int soMoi = maxSo + 1;
            String maMoi = String.format("HD%03d", soMoi);

            Date currentDate = new Date(System.currentTimeMillis());
            PhieuGiamGia pgg = (PhieuGiamGia) cboVoucher.getSelectedItem();
            HoaDon hd = new HoaDon();
            hd.setId(0);
            hd.setIdKH(1);
            hd.setIdNV(XAuth.user.getId());
            hd.setMaHoaDon(maMoi);
            hd.setNgayTao((java.sql.Date) currentDate);
            hd.setNgayThanhToan((java.sql.Date) currentDate);
            hd.setTrangThai(0);
            hd.setMaPGG(1);
            hd.setTongGia(new BigDecimal(0));
            hd.setTongGiaSauPGG(new BigDecimal(0));
            hdDao.create(hd);
            fillToHDtable();

            JOptionPane.showMessageDialog(this, "Đã tạo hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnTaoHoaDonActionPerformed


    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        if (evt.getClickCount() == 2) {
            Window owner = SwingUtilities.getWindowAncestor(this);
            ImeiDaBanJdialog dialog = new ImeiDaBanJdialog((Frame) owner, true);
            int index = tblGioHang.getSelectedRow();
            int idHDCT = (int) tblGioHang.getValueAt(index, 0);
            dialog.fillToImei(idHDCT);
            dialog.setVisible(true);
            hdctDao.delete2(idHDCT);
            fillToHDCTTable(Integer.parseInt(txtId.getText()));
            fillToHDtable();
            fillToCTSPTable();
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void cboVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVoucherActionPerformed

    }//GEN-LAST:event_cboVoucherActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        int index = tblHoaDonBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn thanh toán", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
//int idHd = Integer.parseInt(txtId.getText());
//            
//            
//            // Cập nhật mã PGG trong Hóa đơn
//            HoaDon hd = hdDao.findOne(idHd);
//            if (hd == null) {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
//                return;
//            }
//            
//            hdDao.update(hd);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thanh toán ?");
        if (confirm == JOptionPane.YES_OPTION) {
            PhieuGiamGia pgg = (PhieuGiamGia) cboVoucher.getSelectedItem();
            HoaDon hd = new HoaDon();
//            KhachHang kh = (KhachHang) cboMaKH.getSelectedItem();
            String tongTienStr = tblHoaDonBan.getValueAt(index, 3).toString().replace(",", "");
            String tongTienSauGiamStr = tblHoaDonBan.getValueAt(index, 4).toString().replace(",", "");
            BigDecimal tongTien = new BigDecimal(tongTienStr);
            BigDecimal tongTienSauGiam = new BigDecimal(tongTienSauGiamStr);
            Date currentDate = new Date(System.currentTimeMillis());
            hd.setNgayThanhToan((java.sql.Date) currentDate);
            hd.setTongGia(tongTien);
            hd.setTongGiaSauPGG(tongTienSauGiam);
//            hd.setIdKH(kh.getId());
            hd.setMaPGG(pgg.getId());
            hd.setId(Integer.parseInt(txtId.getText()));
            hdDao.thanhToan(hd);
            fillToHDtable();

            DefaultTableModel ghModel = (DefaultTableModel) tblGioHang.getModel();
            ghModel.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Đã thanh toán thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        txtMaHoaDon.setText("");
        txtId.setText("");
        txtKhachHang.setText("");
        txtSDT.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        int index = tblGioHang.getSelectedRow();

        HoaDon hd = hdDao.findByHoaDon(txtMaHoaDon.getText());

        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        HoaDonChiTiet hdcttt = hdctDao.findAllByidHD(hd.getId()).get(index);
        int hdct = (int) tblGioHang.getValueAt(index, 0);
        String ghiChuhdct = (String) tblGioHang.getValueAt(index, 1);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa ?");
        if (confirm == JOptionPane.YES_OPTION) {
            imeiDao.avilable2(hdct);
            imeiDB.delete(hdct);
            hdctDao.delete(hdct);

            fillToHDtable();
            ctspDao.changeAvailable2(hdcttt.getIdSanPham());

            fillToHDCTTable(hd.getId());
            fillToCTSPTable();
            JOptionPane.showMessageDialog(this, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tblHoaDonBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonBanMouseClicked
        int index = tblHoaDonBan.getSelectedRow();
        fillFormHD(index);
        idHoaDon = (int) tblHoaDonBan.getValueAt(index, 0);
        fillToHDCTTable(idHoaDon);
    }//GEN-LAST:event_tblHoaDonBanMouseClicked

    private void tblSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTMouseClicked
        // TODO add your handling code here:
        if (txtMaHoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Window owner = SwingUtilities.getWindowAncestor(this);
        ImeiJdialog dialog = new ImeiJdialog((Frame) owner, true);
        int index = tblSanPhamCT.getSelectedRow();
        ChiTietSanPham ctsp = ctspDao.findOneByAV().get(index);

        int slTon = imeiDao.countImeiActiveByCTSP(ctsp.getId());

        String input = JOptionPane.showInputDialog(this, "Nhập số lượng (tối đa: " + slTon + "):");
        if (input == null) {
            return;
        }
        int soLuong;
        try {
            soLuong = Integer.parseInt(input);
            if (soLuong <= 0 || soLuong > slTon) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HoaDon hd = hdDao.findByHoaDon(txtMaHoaDon.getText()); // lấy hóa đơn từ mã

        dialog.setSoLuong(soLuong);
        dialog.setHoaDon(hd);
        dialog.setCtspp(ctsp);
        dialog.initData();
        dialog.setVisible(true);

        fillToHDCTTable(hd.getId());
        fillToHDtable();
        fillToCTSPTable();
    }//GEN-LAST:event_tblSanPhamCTMouseClicked

    private void btnTHemKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTHemKhActionPerformed
        if (txtMaHoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Window owner = SwingUtilities.getWindowAncestor(this);
        KhachHangJdialog dialog = new KhachHangJdialog((Frame) owner, true);
        HoaDon hd = hdDao.findOne(Integer.parseInt(txtId.getText()));
        dialog.setHoaDon(hd);
        dialog.setVisible(true);
        fillToHDtable();
    }//GEN-LAST:event_btnTHemKhActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String ma = txtTimKiemGioHang.getText();

    }//GEN-LAST:event_btnLocActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnApDungVOUCHERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungVOUCHERActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn!");
            return;
        }
        try {
            int idHd = Integer.parseInt(txtId.getText());
            PhieuGiamGia pgg = (PhieuGiamGia) cboVoucher.getSelectedItem();

            // Cập nhật mã PGG trong Hóa đơn
            HoaDon hd = hdDao.findOne(idHd);
            if (hd == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
                return;
            }

            // Tính tổng tiền trước giảm
            BigDecimal tienGoc = hdctDao.tinhTongTienTheoHoaDon(idHd);
            BigDecimal tienSauGiam = tienGoc;

            if (tienGoc.compareTo(pgg.getDieuKienAD()) < 0) {
                JOptionPane.showMessageDialog(this,
                        "Hóa đơn chưa đạt điều kiện áp dụng phiếu giảm giá!");
                return; // Thoát, không áp dụng
            }
            // Áp dụng giảm giá
            if (pgg.getLoai() == 1) {
                // Giảm theo %
                BigDecimal giamTheoPT = tienGoc.multiply(pgg.getGiaTriGiam())
                        .divide(BigDecimal.valueOf(100));
                if (giamTheoPT.compareTo(pgg.getGiamToiDa()) > 0) {
                    giamTheoPT = pgg.getGiamToiDa();
                }
                tienSauGiam = tienGoc.subtract(giamTheoPT);
            } else if (pgg.getLoai() == 2) {
                // Giảm cố định
                tienSauGiam = tienGoc.subtract(pgg.getGiaTriGiam());
                if (tienSauGiam.compareTo(BigDecimal.ZERO) < 0) {
                    tienSauGiam = BigDecimal.ZERO;
                }
            }

            // Cập nhật trực tiếp giá trị Tiền gốc trong bảng
            DefaultTableModel model = (DefaultTableModel) tblHoaDonBan.getModel();
            DecimalFormat df = new DecimalFormat("#,###");
            int colTienGoc = 4; // Cột tiền gốc
            int colId = 0;      // Cột chứa id hóa đơn
            for (int i = 0; i < model.getRowCount(); i++) {
                Object val = model.getValueAt(i, colId);
                if (val != null && val.toString().equals(String.valueOf(idHd))) {
                    model.setValueAt(df.format(tienSauGiam), i, colTienGoc);
                    break;
                }
            }

            JOptionPane.showMessageDialog(this, "Áp dụng mã giảm giá thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi áp dụng mã giảm giá!");
        }
    }//GEN-LAST:event_btnApDungVOUCHERActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDungVOUCHER;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnTHemKh;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonBan;
    private javax.swing.JTable tblSanPhamCT;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiemGioHang;
    // End of variables declaration//GEN-END:variables

}
