    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package store.ui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import store.controller.HoaDonController;
import store.dao.impl.ChiTietSanPhamDAOImpl;
import store.dao.impl.HoaDonChiTietDAOimpl;
import store.dao.impl.HoaDonDAOimpl;
import store.dao.impl.ImeiDaBanDAOimpl;
import store.dao.impl.KhachHangDAOimpl;
import store.dao.impl.NhanVienDAOimpl;
import store.dao.impl.PhieuGiamGiaDAOimpl;
import store.entity.HoaDon;
import store.entity.HoaDonChiTiet;
import store.entity.HoaDonChiTietDTO;
import store.entity.ImeiDaBan;
import store.entity.KhachHang;
import store.util.TimeRange;
import store.util.XDate;

public class ViewHoaDon extends javax.swing.JPanel implements HoaDonController {

    List<HoaDon> lstHoaDon = new ArrayList<>();
    HoaDonDAOimpl hoaDonDAO = new HoaDonDAOimpl();
    List<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
    HoaDonChiTietDAOimpl hoaDonChiTietDAO = new HoaDonChiTietDAOimpl();
    NhanVienDAOimpl nvDao = new NhanVienDAOimpl();
    List<HoaDonChiTietDTO> lstHoaDonChiTietDTO = new ArrayList<>();
    List<ImeiDaBan> lstImeiDaBan = new ArrayList<>();
    ImeiDaBanDAOimpl imeiDaBanDAOimpl = new ImeiDaBanDAOimpl();
    PhieuGiamGiaDAOimpl pggDAO = new PhieuGiamGiaDAOimpl();
    ChiTietSanPhamDAOImpl ctspDao = new ChiTietSanPhamDAOImpl();
    private int idHD = 0;
    KhachHangDAOimpl khDao = new KhachHangDAOimpl();

    public ViewHoaDon() {
        initComponents();
        fillToTable();

    }

    public void fillToImei(int idHoaDonCT) {
        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
        model.setRowCount(0);
        lstImeiDaBan = imeiDaBanDAOimpl.findByHdct(idHoaDonCT);
        for (ImeiDaBan imeiDaBan : lstImeiDaBan) {
            Object[] row = {
                imeiDaBan.getMaImei()
            };
            model.addRow(row);
        }

    }

    public void selectTimeRange() {
        TimeRange range = TimeRange.today();
        switch (cboTimeRanges.getSelectedIndex()) {
            case 0 ->
                range = null;
            case 1 ->
                range = TimeRange.today();
            case 2 ->
                range = TimeRange.thisWeek();
            case 3 ->
                range = TimeRange.thisMonth();
            case 4 ->
                range = TimeRange.thisQuarter();
            case 5 ->
                range = TimeRange.thisYear();
        }

        if (range != null) {
            txtBegin.setText(XDate.format(range.getBegin(), "MM/dd/yyyy"));
            txtEnd.setText(XDate.format(range.getEnd(), "MM/dd/yyyy"));
        } else {
            txtBegin.setText("--AllTime");
            txtEnd.setText("--AllTime");
        }
        txtFindActionPerformed(null);
    }

    public void fillToHoaDonChiTiet(int idHoaDon) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
        model.setRowCount(0);
        int Stt = 1;
        DecimalFormat df = new DecimalFormat("#,###");

        for (HoaDonChiTiet dto : hoaDonChiTietDAO.findAllByidHD(idHoaDon)) {
            Object[] row = {
                dto.getId(),
                ctspDao.findOne(dto.getIdSanPham()).getGhiChu(),
                df.format(dto.getGia()),
                dto.getSoLuong()
            };
            model.addRow(row);

        }

    }
    
    private void clearProductDetailTable() {
    DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
    model.setRowCount(0);
    tblHoaDon.clearSelection();
}
      private void clearImeiDetail() {
    DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
    model.setRowCount(0);
    tblHoaDon.clearSelection();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonCT = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblImei = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBegin = new javax.swing.JTextField();
        txtEnd = new javax.swing.JTextField();
        cboTimeRanges = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFind = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        pane1.setBackground(new java.awt.Color(255, 255, 255));
        pane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));

        txtTimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Tên Nhân Viên", "Tên Khách Hàng", "Email Khách Hàng", "SDT Khách Hàng", "Ngày Thanh Toán", "Phiếu giảm giá", "Tổng Tiền", "Giá Sau Giảm", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(30);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        tblHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sản phẩm", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCT.setRowHeight(30);
        tblHoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonCT);

        tblImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Imei"
            }
        ));
        jScrollPane3.setViewportView(tblImei);

        jButton1.setText("XUẤT HÓA ĐƠN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Sản phẩm chi tiết");

        jLabel3.setText("Hóa đơn");

        txtBegin.setText("--All");

        txtEnd.setText("--All");

        cboTimeRanges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SelectTime", "Hôm nay", "Tuần nay", "Tháng nay", "Quý nay", "Năm nay" }));
        cboTimeRanges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTimeRangesMouseClicked(evt);
            }
        });
        cboTimeRanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimeRangesActionPerformed(evt);
            }
        });

        jLabel4.setText("From: ");

        jLabel5.setText("To: ");

        txtFind.setText("Tìm kiếm");
        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFind)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtFind))
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pane1.addTab("Hóa Đơn", jPanel1);

        jPanel2.setBackground(new java.awt.Color(128, 203, 196));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1046, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        pane1.addTab("", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        int selectedRow = tblHoaDon.getSelectedRow();
        HoaDon hoaDon = lstHoaDon.get(selectedRow);
        int idHoaDon = hoaDon.getId();
        fillToHoaDonChiTiet(Integer.valueOf(idHoaDon));
        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
        model.setRowCount(0);
        idHD = idHoaDon;
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonActionPerformed


    }//GEN-LAST:event_txtTimKiemHoaDonActionPerformed

    private void tblHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCTMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblHoaDonCT.getSelectedRow();
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietDAO.findAllByidHD(idHD).get(selectedRow);
        int idHoaDonCT = hoaDonChiTiet.getId();
        fillToImei(idHoaDonCT);

    }//GEN-LAST:event_tblHoaDonCTMouseClicked

    private void cboTimeRangesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTimeRangesMouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_cboTimeRangesMouseClicked

    private void cboTimeRangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimeRangesActionPerformed
            // TODO add your handling code here:
        clearProductDetailTable();
        clearImeiDetail();
        selectTimeRange();
//
//        if (cboTimeRanges.getSelectedIndex() != 0) {
//            cboTrangThai.setEnabled(false);
//        } else {
//            cboTrangThai.setEnabled(true);
//        }
    }//GEN-LAST:event_cboTimeRangesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (idHD == 0) {
            JOptionPane.showMessageDialog(ViewHoaDon.this, "Vui lòng chọn hóa đơn để xuất.");
            return;
        }
        HoaDon hoaDon = hoaDonDAO.findOne(idHD);
        exportHoaDonToPDF(hoaDon);

    }//GEN-LAST:event_jButton1ActionPerformed

    private TimeRange getTimeRangeByIndex(int index) {
        switch (index) {
            case 1:
                return TimeRange.today();
            case 2:
                return TimeRange.thisWeek();
            case 3:
                return TimeRange.thisMonth();
            case 4:
                return TimeRange.thisQuarter();
            case 5:
                return TimeRange.thisYear();
            default:
                return TimeRange.today();
        }
    }
    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        clearImeiDetail();
        clearProductDetailTable();
        String text = txtTimKiemHoaDon.getText().trim();
        int timeRangeIndex = cboTimeRanges.getSelectedIndex();
        List<HoaDon> searchResults;

        if (timeRangeIndex == 0) {
            if (text.isEmpty()) {
                searchResults = hoaDonDAO.findAll();
            } else {
                searchResults = hoaDonDAO.findByNameKH(text);
                if (searchResults.isEmpty()) {
                    searchResults = hoaDonDAO.findByMaNV(text);
                }
                if (searchResults.isEmpty()) {
                    searchResults = hoaDonDAO.findByMaHD(text);
                }
                if (searchResults.isEmpty()) {
                    searchResults = hoaDonDAO.findSdt(text);
                }
            }
        } else {
            TimeRange range = getTimeRangeByIndex(timeRangeIndex);
            java.util.Date startDate = range.getBegin();
            java.util.Date endDate = range.getEnd();

            if (text.isEmpty()) {
                searchResults = hoaDonDAO.findByDateRange(startDate, endDate);
            } else {
                searchResults = hoaDonDAO.findByNameKHAndDateRange(text, startDate, endDate);
                if (searchResults.isEmpty()) {
                    searchResults = hoaDonDAO.findByMaNVAndDateRange(text, startDate, endDate);
                }
                if (searchResults.isEmpty()) {
                    searchResults = hoaDonDAO.findByMaHDAndDateRange(text, startDate, endDate);
                }
            }
        }
//        int statusIndex = cboTrangThai.getSelectedIndex();
//        if (statusIndex == 1) { // "Đã Thanh Toán"
//            searchResults = searchResults.stream()
//                    .filter(hd -> hd.getTrangThai() == 1)
//                    .collect(java.util.stream.Collectors.toList());
//        } else if (statusIndex == 2) { // "Chưa Thanh Toán"
//            searchResults = searchResults.stream()
//                    .filter(hd -> hd.getTrangThai() == 0)
//                    .collect(java.util.stream.Collectors.toList());
//        }

        if (searchResults.isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu phù hợp!");
            return;
        }

        this.lstHoaDon = searchResults;
        FindNameKHNoDate();

    }//GEN-LAST:event_txtFindActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTimeRanges;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane pane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCT;
    private javax.swing.JTable tblImei;
    private javax.swing.JTextField txtBegin;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JButton txtFind;
    private javax.swing.JTextField txtTimKiemHoaDon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForm(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon getForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DecimalFormat df = new DecimalFormat("#,###");
        try {
            String beginDate = txtBegin.getText();
            String endDate = txtEnd.getText();
            if (beginDate.equals("--AllTime") || endDate.equals("--AllTime")
                    || beginDate.isEmpty() || endDate.isEmpty()) {
                lstHoaDon = hoaDonDAO.findAll();
            } else {
                java.util.Date begin = XDate.parse(beginDate, "MM/dd/yyyy");
                java.util.Date end = XDate.parse(endDate, "MM/dd/yyyy");
                if (begin != null && end != null) {
                    lstHoaDon = hoaDonDAO.findByDateRange(begin, end);
                } else {
                    lstHoaDon = hoaDonDAO.findAll();
                }
            }

            // Hiển thị dữ liệu
            for (HoaDon hoadon : lstHoaDon) {
                KhachHang kh = khDao.findOne(hoadon.getIdKH());
                String email = "";
                String tenKH = "";
                String soDT = "";
                String gioiTinh = "";

                if (kh != null) {
                    email = kh.getEmail() != null ? kh.getEmail() : "";
                    tenKH = kh.getTenKH() != null ? kh.getTenKH() : "";
                    soDT = kh.getSoDT() != null ? kh.getSoDT() : "";
                }

                Object[] row = {
                    hoadon.getMaHoaDon(),
                    nvDao.findOne(hoadon.getIdNV()).getMaNV() + " - " + nvDao.findOne(hoadon.getIdNV()).getHoTen(),
                    hoaDonDAO.findName1(hoadon.getIdKH()).getTenKH(),
                    email,
                    soDT,
                    hoadon.getNgayThanhToan(),
                    pggDAO.findOne(hoadon.getMaPGG()),
                    df.format(hoadon.getTongGia()),
                    df.format(hoadon.getTongGiaSauPGG()),
                    
                    hoadon.getTrangThai() == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void FindNameKHNoDate() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DecimalFormat df = new DecimalFormat("#,###");
        
        for (HoaDon hoadon : lstHoaDon) {
                KhachHang kh = khDao.findOne(hoadon.getIdKH()); // ← Lỗi sẽ xảy ra ở đây

                String email = "";
                String tenKH = "";
                String soDT = "";
                String gioiTinh = "";
                if (kh != null) {
                    email = kh.getEmail() != null ? kh.getEmail() : "";
                    tenKH = kh.getTenKH() != null ? kh.getTenKH() : "";
                    soDT = kh.getSoDT() != null ? kh.getSoDT() : "";
                    
                }

                Object[] row = {
                    hoadon.getMaHoaDon(),
                    
                    nvDao.findOne(hoadon.getIdNV()).getMaNV() + " - " + nvDao.findOne(hoadon.getIdNV()).getHoTen(),
                    hoaDonDAO.findName1(hoadon.getIdKH()).getTenKH(),
                    email,
                    soDT,
                    hoadon.getNgayThanhToan(),
                    pggDAO.findOne(hoadon.getMaPGG()),
                    df.format(hoadon.getTongGia()),
                    df.format(hoadon.getTongGiaSauPGG()),
                    hoadon.getTrangThai() == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán"
                };
                model.addRow(row);
            }
    }

    public void exportHoaDonToPDF(HoaDon hd) {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("src/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Font font = new Font(bf, 12);
        Document document = new Document();
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("HoaDon_" + hd.getMaHoaDon() + ".pdf"));
            int option = fileChooser.showSaveDialog(this);
            if (option != JFileChooser.APPROVE_OPTION) {
                return;
            }

            PdfWriter.getInstance(document, new FileOutputStream(fileChooser.getSelectedFile()));
            document.open();

            com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            com.itextpdf.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("HÓA ĐƠN THANH TOÁN", font));
            document.add(new Paragraph("Mã hóa đơn: " + hd.getMaHoaDon(), font));
            document.add(new Paragraph("Ngày thanh toán: " + hd.getNgayThanhToan(), font));
            document.add(new Paragraph("Nhân viên: " + nvDao.findOne(hd.getIdNV()).getHoTen(), font));
            document.add(new Paragraph("--------------------------------------------------------------"));

            PdfPTable table = new PdfPTable(4); // 4 cột


            float[] columnWidths = {1f, 4f, 2f, 2f}; // “Sản phẩm” rộng gấp 4 lần STT
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.addCell(new PdfPCell(new Phrase("STT", font)));
            table.addCell(new PdfPCell(new Phrase("Sản phẩm", font)));
            table.addCell(new PdfPCell(new Phrase("Giá", font)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", font)));

            List<HoaDonChiTiet> chiTietList = hoaDonChiTietDAO.findAllByidHD(hd.getId());
            int i = 1;
            for (HoaDonChiTiet ct : chiTietList) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(i++), font)));
                table.addCell(new PdfPCell(new Phrase(ctspDao.findOne(ct.getIdSanPham()).getGhiChu(), font)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(ct.getGia()), font)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(ct.getSoLuong()), font)));

            }

            document.add(table);

            document.add(new Paragraph("Tổng tiền: " + hd.getTongGiaSauPGG() + " VND", font));
            document.add(new Paragraph("Trạng thái: " + (hd.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"), font));

            JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất hóa đơn!");
        } finally {
            document.close();
        }
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
