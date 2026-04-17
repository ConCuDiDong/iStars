/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package store.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



public class ViewThongKe extends javax.swing.JPanel {

    

    public ViewThongKe() {
        initComponents();
        SwingUtilities.invokeLater(() -> {
        loadBieuDo();
    });
        int namHienTai = java.time.Year.now().getValue(); // Lấy năm hiện tại

cboNam.removeAllItems(); // Xóa các item cũ nếu có
for (int nam = 2000; nam <= namHienTai; nam++) {
    cboNam.addItem(String.valueOf(nam)); // hoặc addItem(nam) nếu kiểu Integer
}

// Chọn mặc định năm hiện tại
cboNam.setSelectedItem(String.valueOf(namHienTai));

cboThang.removeAllItems();
for (int t = 1; t <= 12; t++) {
    cboThang.addItem(String.valueOf(t));
}
cboThang.setSelectedItem(String.valueOf(java.time.LocalDate.now().getMonthValue()));

    }
 
       public void loadBieuDo() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try {
        // Kết nối SQL Server
        Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=iStars;encrypt=true;trustServerCertificate=true;",
            "sa", "123"
        );

        // Truy vấn doanh thu theo tháng (lưu ý: cột đúng là 'TongGia' chứ không phải 'TongTien' nếu theo dữ liệu bạn gửi)
        String sql = "SELECT MONTH(ngayTao) AS Thang, SUM(tongGia) AS DoanhThu " +
                     "FROM HoaDon WHERE trangThai = 1 " +
                     "GROUP BY MONTH(ngayTao) ORDER BY Thang";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int thang = rs.getInt("Thang");
            double doanhThu = rs.getDouble("DoanhThu");
            dataset.addValue(doanhThu, "Doanh thu", "Tháng " + thang);
        }
        
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Tạo biểu đồ
    JFreeChart barChart = ChartFactory.createBarChart(
        "Thống kê doanh thu theo tháng",
        "Tháng",
        "Doanh thu (VNĐ)",
        dataset
    );

    // Hiển thị biểu đồ vào panel
    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(pnlThongKe.getSize());
    chartPanel.setMouseWheelEnabled(true);

    pnlThongKe.removeAll();
    pnlThongKe.setLayout(new BorderLayout());
    pnlThongKe.add(chartPanel, BorderLayout.CENTER);
    pnlThongKe.revalidate();
    pnlThongKe.repaint();
}
       
       
         // Load Theo Năm
         private void loadBieuDo(int year) {
    // Hỏi xác nhận trước khi tải biểu đồ
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Bạn có chắc muốn tải biểu đồ doanh thu năm " + year + " không?",
        "Xác nhận",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    if (confirm != JOptionPane.YES_OPTION) {
        return; // Người dùng chọn "Không" thì dừng
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Tự động set ngày bắt đầu và kết thúc của năm
    String ngayBD = year + "-01-01";
    String ngayKT = year + "-12-31";
    txtNgayBD.setText(ngayBD);
    txtNgayKT.setText(ngayKT);

    String sqlDoanhThuThang = """
        WITH Months AS (
            SELECT 1 AS Thang UNION ALL
            SELECT 2 UNION ALL
            SELECT 3 UNION ALL
            SELECT 4 UNION ALL
            SELECT 5 UNION ALL
            SELECT 6 UNION ALL
            SELECT 7 UNION ALL
            SELECT 8 UNION ALL
            SELECT 9 UNION ALL
            SELECT 10 UNION ALL
            SELECT 11 UNION ALL
            SELECT 12
        )
        SELECT m.Thang,
               ISNULL(SUM(hdct.gia * hdct.soLuong), 0) AS DoanhThu
        FROM Months m
        LEFT JOIN HoaDon hd 
            ON MONTH(hd.ngayThanhToan) = m.Thang
            AND hd.trangThai = 1
            AND hd.ngayThanhToan BETWEEN ? AND ?
        LEFT JOIN HoaDonChiTiet hdct 
            ON hd.id = hdct.idHoaDon
        GROUP BY m.Thang
        ORDER BY m.Thang
        """;

    String sqlTongCong = """
        SELECT 
            ISNULL(SUM(hdct.gia * hdct.soLuong), 0) AS TongDoanhThu,
            ISNULL(SUM(hdct.soLuong), 0) AS TongSP
        FROM HoaDon hd
        JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon
        WHERE hd.trangThai = 1
        AND hd.ngayThanhToan BETWEEN ? AND ?
        """;

    try (Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=iStars;encrypt=true;trustServerCertificate=true;",
            "sa", "123")) {

        // Lấy dữ liệu biểu đồ
        try (PreparedStatement ps = conn.prepareStatement(sqlDoanhThuThang)) {
            ps.setString(1, ngayBD);
            ps.setString(2, ngayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("Thang");
                double doanhThu = rs.getDouble("DoanhThu");
                dataset.addValue(doanhThu, "Doanh thu", "Tháng " + thang);
            }
        }

        // Lấy tổng doanh thu và tổng sản phẩm
        try (PreparedStatement ps = conn.prepareStatement(sqlTongCong)) {
            ps.setString(1, ngayBD);
            ps.setString(2, ngayKT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtdoanhthu.setText(String.format("%,.0f VNĐ", rs.getDouble("TongDoanhThu")));
                txttongsp.setText(String.valueOf(rs.getInt("TongSP")));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    // Vẽ biểu đồ
    JFreeChart barChart = ChartFactory.createBarChart(
        "Thống kê doanh thu năm " + year,
        "Tháng",
        "Doanh thu (VNĐ)",
        dataset,
        PlotOrientation.VERTICAL,
        false, true, false
    );

    CategoryPlot plot = barChart.getCategoryPlot();
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    for (int col = 0; col < dataset.getColumnCount(); col++) {
        double val = dataset.getValue(0, col).doubleValue();
        renderer.setSeriesPaint(0, val == 0 ? Color.RED : Color.BLUE);
    }

    ChartPanel chartPanel = new ChartPanel(barChart);
    pnlThongKe.removeAll();
    pnlThongKe.setLayout(new BorderLayout());
    pnlThongKe.add(chartPanel, BorderLayout.CENTER);
    pnlThongKe.revalidate();
    pnlThongKe.repaint();

    // Thông báo khi tải xong
        JOptionPane.showMessageDialog(
        this,
        "Đã tải biểu đồ doanh thu năm " + year + " thành công!",
        "Thông báo",
        JOptionPane.INFORMATION_MESSAGE
    );
}
         
           // Load Theo Tháng
        private void loadBieuDoTheoThang(int year, int month) {
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Bạn có chắc muốn tải biểu đồ doanh thu tháng " + month + "/" + year + " không?",
        "Xác nhận",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Ngày bắt đầu và kết thúc của tháng
    String ngayBD = String.format("%d-%02d-01", year, month);
    String ngayKT = String.format("%d-%02d-%02d", year, month, 
        java.time.YearMonth.of(year, month).lengthOfMonth());

    txtNgayBD.setText(ngayBD);
    txtNgayKT.setText(ngayKT);

    String sqlDoanhThuNgay = """
        WITH Days AS (
            SELECT 1 AS Ngay UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL 
            SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL 
            SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL 
            SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20 UNION ALL 
            SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL SELECT 25 UNION ALL 
            SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30 UNION ALL SELECT 31
        )
        SELECT d.Ngay,
               ISNULL(SUM(hdct.gia * hdct.soLuong), 0) AS DoanhThu
        FROM Days d
        LEFT JOIN HoaDon hd 
            ON DAY(hd.ngayThanhToan) = d.Ngay
            AND MONTH(hd.ngayThanhToan) = ?
            AND YEAR(hd.ngayThanhToan) = ?
            AND hd.trangThai = 1
            AND hd.ngayThanhToan BETWEEN ? AND ?
        LEFT JOIN HoaDonChiTiet hdct 
            ON hd.id = hdct.idHoaDon
        GROUP BY d.Ngay
        ORDER BY d.Ngay
        """;

    String sqlTongCong = """
        SELECT 
            ISNULL(SUM(hdct.gia * hdct.soLuong), 0) AS TongDoanhThu,
            ISNULL(SUM(hdct.soLuong), 0) AS TongSP
        FROM HoaDon hd
        JOIN HoaDonChiTiet hdct ON hd.id = hdct.idHoaDon
        WHERE hd.trangThai = 1
        AND hd.ngayThanhToan BETWEEN ? AND ?
        """;

    try (Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=iStars;encrypt=true;trustServerCertificate=true;",
            "sa", "123")) {

        // Lấy dữ liệu biểu đồ
        try (PreparedStatement ps = conn.prepareStatement(sqlDoanhThuNgay)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setString(3, ngayBD);
            ps.setString(4, ngayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ngay = rs.getInt("Ngay");
                double doanhThu = rs.getDouble("DoanhThu");
                dataset.addValue(doanhThu, "Doanh thu", "Ngày " + ngay);
            }
        }

        // Lấy tổng doanh thu và tổng sản phẩm
        try (PreparedStatement ps = conn.prepareStatement(sqlTongCong)) {
            ps.setString(1, ngayBD);
            ps.setString(2, ngayKT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtdoanhthu.setText(String.format("%,.0f VNĐ", rs.getDouble("TongDoanhThu")));
                txttongsp.setText(String.valueOf(rs.getInt("TongSP")));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    // Vẽ biểu đồ
    JFreeChart barChart = ChartFactory.createBarChart(
        "Thống kê doanh thu tháng " + month + "/" + year,
        "Ngày",
        "Doanh thu (VNĐ)",
        dataset,
        PlotOrientation.VERTICAL,
        false, true, false
    );

    CategoryPlot plot = barChart.getCategoryPlot();
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, Color.BLUE);

    ChartPanel chartPanel = new ChartPanel(barChart);
    pnlThongKe.removeAll();
    pnlThongKe.setLayout(new BorderLayout());
    pnlThongKe.add(chartPanel, BorderLayout.CENTER);
    pnlThongKe.revalidate();
    pnlThongKe.repaint();

    JOptionPane.showMessageDialog(
        this,
        "Đã tải biểu đồ doanh thu tháng " + month + "/" + year + " thành công!",
        "Thông báo",
        JOptionPane.INFORMATION_MESSAGE
    );
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtdoanhthu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txttongsp = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNgayBD = new javax.swing.JTextField();
        txtNgayKT = new javax.swing.JTextField();
        pnlThongKe = new javax.swing.JPanel();
        btnLoc = new javax.swing.JButton();
        cboNam = new javax.swing.JComboBox<>();
        btnThang = new javax.swing.JButton();
        cboThang = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Doanh thu 1 năm :");

        txtdoanhthu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtdoanhthu.setForeground(new java.awt.Color(255, 0, 0));
        txtdoanhthu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtdoanhthu.setText("_");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tổng sản phẩm bán ra:");

        txttongsp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttongsp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txttongsp.setText("_");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttongsp, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel3, txtdoanhthu, txttongsp});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtdoanhthu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttongsp))
                .addGap(33, 33, 33))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel3, txtdoanhthu, txttongsp});

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Ngày kết thúc: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Ngày bắt đầu: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayBD)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(0, 48, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel6});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel5, jLabel6});

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setText("Lọc Theo Năm");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThang.setText("Lọc Theo Tháng");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btnThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboThang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
    int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
    loadBieuDo(nam);
    }//GEN-LAST:event_btnLocActionPerformed

    private void dateNgayBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBDMouseClicked

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        // TODO add your handling code here:
         int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
    int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
    loadBieuDoTheoThang(nam, thang);
    }//GEN-LAST:event_btnThangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnThang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtNgayKT;
    private javax.swing.JLabel txtdoanhthu;
    private javax.swing.JLabel txttongsp;
    // End of variables declaration//GEN-END:variables

    

}
