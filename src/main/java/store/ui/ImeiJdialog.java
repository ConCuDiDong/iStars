/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package store.ui;

import java.awt.Frame;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import store.dao.impl.ChiTietSanPhamDAOImpl;
import store.dao.impl.HoaDonChiTietDAOimpl;
import store.dao.impl.ImeiDAOimpl;
import store.dao.impl.ImeiDaBanDAOimpl;
import store.entity.ChiTietSanPham;
import store.entity.HoaDon;
import store.entity.HoaDonChiTiet;
import store.entity.Imei;
import store.entity.ImeiDaBan;

/**
 *
 * @author dell
 */
public class ImeiJdialog extends javax.swing.JDialog {

    /**
     * Creates new form ImeiJdialog
     */
    List<ChiTietSanPham> lstChiTietSanPham = new ArrayList<>();
    ChiTietSanPhamDAOImpl chiTIetSanPhamDAO = new ChiTietSanPhamDAOImpl();
    List<Imei> listEmei = new ArrayList<>();
    private List<Imei> imeisDaChon = new ArrayList<>();
    ImeiDAOimpl imeiDao = new ImeiDAOimpl();
    ImeiDaBanDAOimpl imdbDao = new ImeiDaBanDAOimpl();
    HoaDonChiTietDAOimpl hdctDao = new HoaDonChiTietDAOimpl();
    DefaultComboBoxModel mCTSP = new DefaultComboBoxModel();
    DefaultTableModel modelImei = new DefaultTableModel();
    private int soLuongDaChon = 0;
    private int idHoaDonMoiTao = -1;
    boolean isNewCreated = false;
    private int soLuongCu = 0;

    public ImeiJdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    @Setter
    HoaDon HoaDon;
    @Setter
    ChiTietSanPham ctspp;
    @Setter
    int soLuong;

    public void initData() {
        fillCbo();

        // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng (hoaDonChiTiet)
        HoaDonChiTiet existed = hdctDao.findByIdHoaDonAndIdSP(HoaDon.getId(), ctspp.getId());

        if (existed != null) {
            // Đã có → cập nhật số lượng mới
            soLuongCu = existed.getSoLuong();
            existed.setSoLuong(existed.getSoLuong() + soLuong);
            existed.setGia(ctspp.getGia().multiply(BigDecimal.valueOf(existed.getSoLuong())));
            hdctDao.update2(existed);
            idHoaDonMoiTao = existed.getId();
            isNewCreated = false;
        } else {
            // Chưa có → tạo mới
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setIdSanPham(ctspp.getId());
            hdct.setIdHoaDon(HoaDon.getId());
            hdct.setGia(ctspp.getGia().multiply(BigDecimal.valueOf(soLuong)));
            hdct.setSoLuong(soLuong);
            hdct.setTrangThai(0);
            idHoaDonMoiTao = hdctDao.create(hdct);
            isNewCreated = true;
        }

        filltoImeiTable();
        filltoIMDBtbale();
        fillToImeiForm(0);
    }

    public void fillCbo() {

        mCTSP = (DefaultComboBoxModel) cboCTSP.getModel();
        mCTSP.removeAllElements();

        for (ChiTietSanPham chiTietSanPham : chiTIetSanPhamDAO.findAll()) {

            mCTSP.addElement(chiTietSanPham);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblImei = new javax.swing.JTable();
        jlabellman = new javax.swing.JLabel();
        txtMaImei = new javax.swing.JTextField();
        lblKichThuoc3 = new javax.swing.JLabel();
        cboCTSP = new javax.swing.JComboBox<>();
        lblTenSanPham5 = new javax.swing.JLabel();
        txtIdImei = new javax.swing.JTextField();
        btnThoat = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblImeiDB = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblImei.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mã Imei"
            }
        ));
        tblImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblImei);

        jlabellman.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman.setText("Mã");

        lblKichThuoc3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKichThuoc3.setText("Sản phẩm");

        cboCTSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cboCTSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblTenSanPham5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham5.setText("id");

        txtIdImei.setEditable(false);

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoat.setText("Hoàn tác");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        tblImeiDB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblImeiDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mã Imei đã chọn"
            }
        ));
        tblImeiDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiDBMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblImeiDB);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTenSanPham5)
                                        .addGap(69, 69, 69)
                                        .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlabellman)
                                        .addGap(60, 60, 60)
                                        .addComponent(txtMaImei, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 584, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblKichThuoc3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cboCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKichThuoc3)
                            .addComponent(cboCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham5)
                            .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabellman))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiMouseClicked
        // TODO add your handling code here:
        int index = tblImei.getSelectedRow();

        Imei imei = imeiDao.FindByTT(ctspp.getId()).get(index);
        fillToImeiForm(index);
        if (evt.getClickCount() == 2) {
            if (soLuongDaChon >= soLuong) {
                JOptionPane.showMessageDialog(this, "Bạn đã chọn đủ số lượng Imei", "Thông báo", JOptionPane.WARNING_MESSAGE);
                this.dispose();
                return;
            }
            imeisDaChon.add(imei);
            for (HoaDonChiTiet hoaDonChiTiet : hdctDao.findAllByidHD(HoaDon.getId())) {
                if (hoaDonChiTiet.getIdSanPham() == ctspp.getId()) {

                    ImeiDaBan imdb = new ImeiDaBan();
                    imdb.setIdHoaDonCT(hoaDonChiTiet.getId());
                    imdb.setMaImei(imei.getMaImei());
                    imdbDao.create(imdb);
                    imeiDao.avilable(imei.getId());
                    soLuongDaChon++;

                    hdctDao.update(hoaDonChiTiet);
                    chiTIetSanPhamDAO.changeAvailable2(ctspp.getId());
                    chiTIetSanPhamDAO.changeAvailable(ctspp.getId());

                    filltoImeiTable();
                    filltoIMDBtbale();
                    if (soLuongDaChon >= soLuong) {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Bạn đã chọn đủ số lượng Imei", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
            }
            System.out.println("ID dùng để tạo ImeiDaBan: " + idHoaDonMoiTao);
            ImeiDaBan imdb = new ImeiDaBan();
            imdb.setIdHoaDonCT(idHoaDonMoiTao);
            imdb.setMaImei(imei.getMaImei());
            imdbDao.create(imdb);
            imeiDao.avilable(imei.getId());
            soLuongDaChon++;
            if (soLuongDaChon >= soLuong) {
                this.dispose();
                JOptionPane.showMessageDialog(this, "Bạn đã chọn đủ số lượng Imei", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }

        }
        chiTIetSanPhamDAO.changeAvailable2(ctspp.getId());
        chiTIetSanPhamDAO.changeAvailable(ctspp.getId());

        filltoImeiTable();
        filltoIMDBtbale();
    }//GEN-LAST:event_tblImeiMouseClicked

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        for (Imei imei : imeisDaChon) {
            imeiDao.avilable1(imei.getMaImei());
            imdbDao.delete2(imei.getMaImei());
            // Cập nhật trạng thái IMEI về 1 (active)
        }

        // Nếu đây là bản ghi mới tạo thì xóa luôn toàn bộ
        if (isNewCreated) {
            // Chỉ xóa ImeiDaBan nếu là hóa đơn chi tiết mới tạo
            

            // Xóa luôn dòng HoaDonChiTiet tương ứng
            hdctDao.delete(idHoaDonMoiTao);
        } else {
            // Nếu là bản ghi cũ → chỉ hoàn tác IMEI, không xóa trong DB
            // Có thể log hoặc xử lý thêm nếu cần
            HoaDonChiTiet hdct = hdctDao.findOne(idHoaDonMoiTao);
            hdct.setSoLuong(soLuongCu);
            hdct.setGia(ctspp.getGia().multiply(BigDecimal.valueOf(soLuongCu)));
            hdctDao.update2(hdct);
            System.out.println("Đây là bản ghi cũ, không xóa HDCT hay IMDB.");
        }

        JOptionPane.showMessageDialog(this, "Đã hoàn tác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        this.dispose(); // Đóng dialog

    }//GEN-LAST:event_btnThoatActionPerformed

    private void tblImeiDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiDBMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblImeiDBMouseClicked

    public void fillToImeiForm(int index) {
        listEmei = imeiDao.FindByTT(ctspp.getId());
        Imei imei = listEmei.get(index);
        txtIdImei.setText(String.valueOf(imei.getId()));
        txtMaImei.setText(imei.getMaImei());
        cboCTSP.setSelectedItem(chiTIetSanPhamDAO.findOne(imei.getIdCTSP()));

    }

    public void filltoImeiTable() {
        if (ctspp == null) {
            System.err.println("ctspp chưa được truyền vào => không load được bảng IMEI.");
            return;
        }
        String[] columnNames = {"Mã Imei", "Trạng thái"};

        DefaultTableModel tImei = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bất kỳ cell nào
            }
        };

        for (Imei i : imeiDao.FindByTT(ctspp.getId())) {
            tImei.addRow(new Object[]{
                i.getMaImei()
            });
        }

        tblImei.setModel(tImei);
    }

    public void filltoIMDBtbale() {
        modelImei = (DefaultTableModel) tblImeiDB.getModel();
        modelImei.setRowCount(0);
        for (ImeiDaBan imeiDaBan : imdbDao.findOne(idHoaDonMoiTao)) {
            modelImei.addRow(new Object[]{
                imeiDaBan.getMaImei()
            });
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImeiJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImeiJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImeiJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImeiJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImeiJdialog dialog = new ImeiJdialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCTSP;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jlabellman;
    private javax.swing.JLabel lblKichThuoc3;
    private javax.swing.JLabel lblTenSanPham5;
    private javax.swing.JTable tblImei;
    private javax.swing.JTable tblImeiDB;
    private javax.swing.JTextField txtIdImei;
    private javax.swing.JTextField txtMaImei;
    // End of variables declaration//GEN-END:variables
}
