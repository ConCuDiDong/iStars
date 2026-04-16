package store.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import store.dao.impl.HoaDonDAOimpl;
import store.dao.impl.KhachHangDAOimpl;
import store.entity.HoaDon;
import store.entity.KhachHang;

public class KhachHangJdialog extends javax.swing.JDialog {

    List<KhachHang> lstKhachHang = new ArrayList<>();
    KhachHangDAOimpl khachHangDAOimpl = new KhachHangDAOimpl();
    HoaDonDAOimpl hdDAO = new HoaDonDAOimpl();

    public KhachHangJdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lstKhachHang = khachHangDAOimpl.findAllByTrangThai();
        fillToTable();
        setLocationRelativeTo(null);
    }

    @Setter
    HoaDon HoaDon;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã KH", "Tên KH", "Số ĐT", "Giới tính", "Địa chỉ ", "Email", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(tblKhachHang);

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        txtId.setEditable(false);

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel1.setText("Id:");

        jLabel2.setText("Mã KH:");

        jLabel3.setText("Tên KH:");

        jLabel4.setText("Sđt:");

        jLabel5.setText("Email:");

        jLabel6.setText("Địa Chỉ");

        jLabel7.setText("Giới tính:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnFind)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId)
                                .addComponent(txtMaKH)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(26, 26, 26)
                                .addComponent(rdoNu)
                                .addGap(23, 23, 23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtSdt)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtEmail)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            List<KhachHang> lstkhachhang = new ArrayList<>();
            String maKH = txtMaKH.getText();
            lstkhachhang = khachHangDAOimpl.CheckTrungMaKH(maKH);
            String email = txtEmail.getText();
            KhachHang kh = getForm();
            if (txtDiaChi.getText().isEmpty() || txtMaKH.getText().isEmpty() || txtTenKH.getText().isEmpty() || txtSdt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin!!!");
                return;
            }

            if (!txtTenKH.getText().matches("^[\\p{L} ]+$")) {
                JOptionPane.showMessageDialog(this, "Tên chỉ được chứa chữ và khoảng trắng!!!");
                return;
            }

            if (!txtSdt.getText().matches("^\\d{10,12}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải từ 10 đến 12 số!!!");
                return;
            }

            if (!rdoNu.isSelected() && !rdoNam.isSelected()) {
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
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!!!");
            if (confirm == JOptionPane.YES_OPTION) {
                khachHangDAOimpl.create(kh);
                lstKhachHang = khachHangDAOimpl.findAllByTrangThai();
                fillToTable();
                Clear();
                JOptionPane.showMessageDialog(this, "Da them thanh cong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        fillDataToForm();

        int indexRow = tblKhachHang.getSelectedRow();
 
        KhachHang kh = lstKhachHang.get(indexRow);
        if (evt.getClickCount() == 2) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm khách hàng này vào hóa đơn ?");
            if (confirm == JOptionPane.YES_OPTION) {
                HoaDon newHoaDon = hdDAO.findOne(HoaDon.getId());
                newHoaDon.setIdKH(kh.getId());
                hdDAO.updateIdKH(newHoaDon);
                
                JOptionPane.showMessageDialog(this, "Đã thêm thông tin khách hàng vào hóa đơn");
                this.dispose();
            }
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        String textFind = txtFind.getText().trim();
        if (textFind.isEmpty()) {
            lstKhachHang = khachHangDAOimpl.findAllByTrangThai();
            fillToTable();
            return;
        }

        lstKhachHang = khachHangDAOimpl.FindByEmail(textFind);
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAOimpl.FindByMaKH(textFind);
        }
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAOimpl.findByName(textFind);

        }
        if (lstKhachHang.isEmpty()) {
            lstKhachHang = khachHangDAOimpl.findBySdt(textFind);
        }

        if (lstKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tồn tại!!!");
            return;
        }

        fillToTable();
    }//GEN-LAST:event_btnFindActionPerformed

    public void fillDataToForm() {
        int selectedRow = tblKhachHang.getSelectedRow();
        KhachHang kh = lstKhachHang.get(selectedRow);
        txtId.setText(String.valueOf(kh.getId()));
        txtMaKH.setText(kh.getMaKH());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        txtTenKH.setText(kh.getTenKH());
        txtSdt.setText(kh.getSoDT());

        if (kh.getGioiTinh() == 1) {
            rdoNam.setSelected(true);
            rdoNu.setSelected(false);
        } else if (kh.getGioiTinh() == 0) {
            rdoNam.setSelected(false);
            rdoNu.setSelected(true);
        } else {
            rdoNam.setSelected(false);
            rdoNu.setSelected(false);
        }
    }

    private void Clear() {
        txtDiaChi.setText("");
        txtEmail.setText((""));
        txtMaKH.setText("");
        txtId.setText("");
        buttonGroup1.clearSelection();
        txtTenKH.setText("");
        txtSdt.setText("");
    }

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);

        for (KhachHang kh : lstKhachHang) {
            Object[] row = {
                kh.getId(),
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSoDT(),
                kh.getGioiTinh() == 1 ? "Nam" : kh.getGioiTinh() == 0 ? "Nữ" : "",
                kh.getDiaChi(),
                kh.getEmail(),
                kh.getTrangThai() == 1 ? "Đang hoạt động" : "Không hoạt động"
            };
            model.addRow(row);
        }
    }

    public KhachHang getForm() {
        int selectedRow = tblKhachHang.getSelectedRow();
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setTenKH(txtTenKH.getText());
        kh.setSoDT(txtSdt.getText());
        if (rdoNam.isSelected()) {
            kh.setGioiTinh(1);
        } else if (rdoNu.isSelected()) {
            kh.setGioiTinh(0);
        }
        kh.setDiaChi(txtDiaChi.getText());
        kh.setEmail(txtEmail.getText());
        kh.setTrangThai(1);
        return kh;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhachHangJdialog dialog = new KhachHangJdialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFind;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
