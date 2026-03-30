package store.ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import store.util.XAuth;
import store.util.XDialog;

public class ViewQuanLy extends javax.swing.JFrame {

    private JPanel childPanel;

    public ViewQuanLy() {
        initComponents();

        setLocationRelativeTo(null);
        setpanel(new ViewBanHang());
        color();
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnBanHang};
        new Thread(() -> {
            for (JButton btn11 : btn1) {
                btn11.setBackground(new Color(128, 203, 196));
            }
            for (JButton btn22 : btn2) {
                btn22.setBackground(new Color(128, 203, 196));
            }
        }).start();
        init();
    }

    public void init() {
        LoginJdialog login = new LoginJdialog(null, true);

        login.setVisible(true);
        lblTenNguoiDung.setText(XAuth.user.getHoTen());
        lblChuVu.setText(XAuth.user.getChucVu() == 1 ? "Quản lý" : "Nhân viên");
        if (XAuth.user.getChucVu() != 1) {
            btnNhanVien.setEnabled(false);
            btnThongKe.setEnabled(false);
        }
    }

//    public void init() {
//        this.setIconImage(XIcon.getIcon("trump-small.png").getImage());
//        this.setLocationRelativeTo(null);
//        // Gọi lần lượt 
//        this.showWelcomeJDialog(this);
//        this.showLoginJDialog(this);
//
//        //XIcon.setIcon(lblPhoto, "photos/" + XAuth.user.getPhoto());
//        lblFullname.setText(XAuth.user.getFullname());
//        if (!XAuth.user.isManager()) {
//            pnlCenter.remove(pnlManager);
//        }
//    }
    private void setpanel(JPanel panel) {
        JPanel childPanel = panel;
        panelMain.removeAll();
        panelMain.add(childPanel);
        panelMain.validate();
    }

    private void color() {
        JButton[] btns = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        for (JButton btn : btns) {
            btn.setBackground(new Color(9, 107, 104));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        lblTenNguoiDung = new javax.swing.JLabel();
        lblChuVu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnDoiMK = new javax.swing.JButton();
        btnDanguat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 700));
        jPanel1.setLayout(null);

        panelMain.setBackground(new java.awt.Color(239, 243, 234));
        panelMain.setMaximumSize(new java.awt.Dimension(1920, 1080));
        panelMain.setMinimumSize(new java.awt.Dimension(0, 0));
        panelMain.setPreferredSize(new java.awt.Dimension(1080, 720));
        panelMain.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelMain);
        panelMain.setBounds(200, 0, 1080, 720);

        btnBanHang.setBackground(new java.awt.Color(128, 203, 196));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 253, 246));
        btnBanHang.setText("Bán Hàng");
        btnBanHang.setAlignmentY(0.0F);
        btnBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBanHangMouseClicked(evt);
            }
        });
        jPanel1.add(btnBanHang);
        btnBanHang.setBounds(0, 0, 200, 60);

        btnSanPham.setBackground(new java.awt.Color(128, 203, 196));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 253, 246));
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setAlignmentY(0.0F);
        btnSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseClicked(evt);
            }
        });
        jPanel1.add(btnSanPham);
        btnSanPham.setBounds(0, 60, 200, 60);

        btnHoaDon.setBackground(new java.awt.Color(128, 203, 196));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 253, 246));
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setAlignmentY(0.0F);
        btnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoaDon);
        btnHoaDon.setBounds(0, 120, 200, 60);

        btnThongKe.setBackground(new java.awt.Color(128, 203, 196));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 253, 246));
        btnThongKe.setText("Thống Kê");
        btnThongKe.setAlignmentY(0.0F);
        btnThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKe);
        btnThongKe.setBounds(0, 180, 200, 60);

        btnKhachHang.setBackground(new java.awt.Color(128, 203, 196));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 253, 246));
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.setAlignmentY(0.0F);
        btnKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
        });
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnKhachHang);
        btnKhachHang.setBounds(0, 240, 200, 60);

        btnNhanVien.setBackground(new java.awt.Color(128, 203, 196));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 253, 246));
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setAlignmentY(0.0F);
        btnNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseClicked(evt);
            }
        });
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhanVien);
        btnNhanVien.setBounds(0, 360, 200, 60);

        btnVoucher.setBackground(new java.awt.Color(128, 203, 196));
        btnVoucher.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(255, 253, 246));
        btnVoucher.setText("Voucher");
        btnVoucher.setAlignmentY(0.0F);
        btnVoucher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoucherMouseClicked(evt);
            }
        });
        jPanel1.add(btnVoucher);
        btnVoucher.setBounds(0, 300, 200, 60);

        btnThoat.setBackground(new java.awt.Color(128, 203, 196));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 253, 246));
        btnThoat.setText("Thoát");
        btnThoat.setAlignmentY(0.0F);
        btnThoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat);
        btnThoat.setBounds(0, 540, 200, 60);

        lblTenNguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        lblTenNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTenNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNguoiDung.setText("NGƯỜI DÙNG:");
        jPanel1.add(lblTenNguoiDung);
        lblTenNguoiDung.setBounds(10, 630, 180, 40);

        lblChuVu.setBackground(new java.awt.Color(255, 255, 255));
        lblChuVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblChuVu.setForeground(new java.awt.Color(255, 0, 51));
        lblChuVu.setText("Chức vụ:");
        jPanel1.add(lblChuVu);
        lblChuVu.setBounds(90, 680, 100, 30);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("NGƯỜI DÙNG:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 600, 130, 30);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Chức vụ:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 680, 74, 30);

        btnDoiMK.setBackground(new java.awt.Color(128, 203, 196));
        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDoiMK.setForeground(new java.awt.Color(255, 253, 246));
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.setAlignmentY(0.0F);
        btnDoiMK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnDoiMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoiMKMouseClicked(evt);
            }
        });
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });
        jPanel1.add(btnDoiMK);
        btnDoiMK.setBounds(0, 420, 200, 60);

        btnDanguat.setBackground(new java.awt.Color(128, 203, 196));
        btnDanguat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDanguat.setForeground(new java.awt.Color(255, 253, 246));
        btnDanguat.setText("Đăng xuất");
        btnDanguat.setAlignmentY(0.0F);
        btnDanguat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnDanguat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDanguatMouseClicked(evt);
            }
        });
        btnDanguat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanguatActionPerformed(evt);
            }
        });
        jPanel1.add(btnDanguat);
        btnDanguat.setBounds(0, 480, 200, 60);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewSanPham());
        JButton[] btn1 = {btnBanHang, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnSanPham};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnSanPhamMouseClicked

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewHoaDon());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnHoaDon};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnThongKeMouseClicked

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewKhachHang());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnKhachHang};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseClicked
//
//        // TODO add yowNhur handling code here:
//        setpanel(new ViewNhanVien());
//
//        // TODO add your handling code here:
//        setpanel(new ViewNhanVien(this, rootPaneCheckingEnabled));
//        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnThoat};
//        JButton[] btn2 = {btnNhanVien};
//        for (JButton btn22 : btn2) {
//            btn22.setBackground(new Color(9, 107, 104));
//        }
//        for (JButton btn11 : btn1) {
//            btn11.setBackground(new Color(128,203,196));
//        }
    }//GEN-LAST:event_btnNhanVienMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        // TODO add your handling code here:
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien};
        JButton[] btn2 = {btnThoat};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
        setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseClicked

        setpanel(new ViewBanHang());
        JButton[] btn1 = {btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnBanHang};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnBanHangMouseClicked

    private void btnVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoucherMouseClicked
        setpanel(new ViewVoucher());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnVoucher};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnVoucherMouseClicked

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        setpanel(new ViewNhanVien());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnThoat};
        JButton[] btn2 = {btnNhanVien};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        setpanel(new ViewThongKe());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnThongKe};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128, 203, 196));
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnDoiMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMKMouseClicked
        // TODO add your handling code here:

        if (XAuth.user.getChucVu() != 1) {
            XDialog.alert("Chỉ quản lý mới được phép đổi mật khẩu!");
            return;
        }

        ResetPasswordWindow resetWindow = new ResetPasswordWindow();
        resetWindow.setVisible(true);

        // btnDoiMK.setText(XAuth.user.getChucVu() == 1 ? "Quản lý" : "Nhân viên");
        if (XAuth.user.getChucVu() != 1) {
            btnThongKe.setEnabled(false);
            btnDoiMK.setEnabled(false);
        }
    }//GEN-LAST:event_btnDoiMKMouseClicked

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnDanguatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDanguatMouseClicked


    }//GEN-LAST:event_btnDanguatMouseClicked

    private void btnDanguatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanguatActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn đăng xuất?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose(); // đóng form quản lý
            new ViewQuanLy().setVisible(true);
        }
    }//GEN-LAST:event_btnDanguatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn thoát không?",
                "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // hoặc this.dispose() nếu chỉ muốn đóng form
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQuanLy().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDanguat;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChuVu;
    private javax.swing.JLabel lblTenNguoiDung;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
