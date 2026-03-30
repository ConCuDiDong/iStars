package store.ui;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import store.controller.SanPhamController;
import store.dao.impl.ChiTietSanPhamDAOImpl;
import store.dao.impl.HoaDonChiTietDAOimpl;
import store.dao.impl.ImeiDAOimpl;
import store.dao.impl.MauSacDAOimpl;
import store.dao.impl.PinDAOimpl;
import store.dao.impl.RomDAOimpl;
import store.dao.impl.SanPhamDAOimpl;
import store.dao.impl.XuatXuDAOimpl;
import store.entity.ChiTietSanPham;
import store.entity.HoaDonChiTiet;
import store.entity.Imei;
import store.entity.MauSac;
import store.entity.Pin;
import store.entity.Rom;
import store.entity.SanPham;
import store.entity.XuatXu;
import store.excel.ImportChiTietSanPhamExcel;
import store.excel.ImportImeiExcel;
import store.excel.ImportSanPhamExcel;
import store.util.XAuth;

public class ViewSanPham extends javax.swing.JPanel implements SanPhamController {

    List<SanPham> lstSanPham = new ArrayList<>();
    SanPhamDAOimpl sanPhamDAO = new SanPhamDAOimpl();
    List<ChiTietSanPham> lstChiTietSanPham = new ArrayList<>();
    ChiTietSanPhamDAOImpl chiTIetSanPhamDAO = new ChiTietSanPhamDAOImpl();
    List<Imei> listEmei = new ArrayList<>();
    SanPhamDAOimpl spDao = new SanPhamDAOimpl();
    MauSacDAOimpl msDao = new MauSacDAOimpl();
    RomDAOimpl romDao = new RomDAOimpl();
    XuatXuDAOimpl xxDao = new XuatXuDAOimpl();
    PinDAOimpl pinDao = new PinDAOimpl();
    ImeiDAOimpl imeiDao = new ImeiDAOimpl();
    HoaDonChiTietDAOimpl hdctDao = new HoaDonChiTietDAOimpl();
    DefaultComboBoxModel mXuatXu = new DefaultComboBoxModel();
    DefaultComboBoxModel mPin = new DefaultComboBoxModel();
    DefaultComboBoxModel mRom = new DefaultComboBoxModel();
    DefaultComboBoxModel mMau = new DefaultComboBoxModel();
    DefaultComboBoxModel mLoai = new DefaultComboBoxModel();
    DefaultComboBoxModel mCTSP = new DefaultComboBoxModel();
    DefaultComboBoxModel modelIemi = new DefaultComboBoxModel();
    DefaultTableModel modelSP = new DefaultTableModel();
    DefaultTableModel modelCT = new DefaultTableModel();
    private int idSP = 0;
    private String imeiCu;
    private String tenSpcu = "";
    private String maSpcu = "";
    private String ghiChuChiTietCu = "";
    private String lastImei = "100000000000000";

    public ViewSanPham() {
        initComponents();
        init();
        fillCbo();
        fillToTable();
        fillToTable2();
        fillToXXTable();
        fillToMauSacTable();
        fillToPinTable();
        fillToRomTable();
        // Đặt trong constructor hoặc sau initComponents()
        txtGhiChu.setEditable(false); // Không cho nhập tay
        txtMaImei.setEditable(false);
// Gắn sự kiện cập nhật khi chọn combo
        cboLoai.addActionListener(e -> updateGhiChu());
        cboMauSac.addActionListener(e -> updateGhiChu());
        cboRom.addActionListener(e -> updateGhiChu());

// Cập nhật lần đầu
        updateGhiChu();
    }

    private void updateGhiChu() {
        Object loaiObj = cboLoai.getSelectedItem();
        Object mauObj = cboMauSac.getSelectedItem();
        Object romObj = cboRom.getSelectedItem();

        String loai = loaiObj != null ? loaiObj.toString() : "";
        String mau = mauObj != null ? mauObj.toString() : "";
        String rom = romObj != null ? romObj.toString() : "";

        txtGhiChu.setText(loai + " - " + mau + " - " + rom);
    }

    public void init() {
        if (XAuth.user.getChucVu() != 1) {
            pnlSP.setVisible(false);
            pnlCT.setVisible(false);
            pnlimei.setVisible(false);
        }
    }

    public void fillCbo() {
        mXuatXu = (DefaultComboBoxModel) cboXuatXu1.getModel();
        mXuatXu.removeAllElements();
        mPin = (DefaultComboBoxModel) cboPin.getModel();
        mPin.removeAllElements();
        mRom = (DefaultComboBoxModel) cboRom.getModel();
        mRom.removeAllElements();
        mMau = (DefaultComboBoxModel) cboMauSac.getModel();
        mMau.removeAllElements();
        mLoai = (DefaultComboBoxModel) cboLoai.getModel();
        mLoai.removeAllElements();

//        modelIemi = (DefaultComboBoxModel) cboTimKiemImei.getModel();
//        modelIemi.removeAllElements();
        for (XuatXu xx : xxDao.findAll()) {
            mXuatXu.addElement(xx);
        }
        for (Pin pin : pinDao.findAll()) {
            mPin.addElement(pin);
        }
        for (Rom rom : romDao.findAll()) {
            mRom.addElement(rom);
        }
        for (MauSac ms : msDao.findAll()) {
            mMau.addElement(ms);
        }
        for (SanPham sanPham : sanPhamDAO.findAll()) {
            mLoai.addElement(sanPham);
        }
        for (ChiTietSanPham chiTietSanPham : chiTIetSanPhamDAO.findAll()) {
            mCTSP.addElement(chiTietSanPham);
        }
        for (ChiTietSanPham chiTietSanPham : chiTIetSanPhamDAO.findAll()) {
            modelIemi.addElement(chiTietSanPham);
        }
    }

    public void fillForm(int index) {

        lstSanPham = sanPhamDAO.findAll();
        SanPham sp = lstSanPham.get(index);
        txtIdSP.setText(String.valueOf(sp.getId()));
        txtMaSP.setText(sp.getMa());
        txtTenSP.setText(sp.getTen());
        XuatXu xx = xxDao.findOne(sp.getIdXX());
        cboXuatXu1.setSelectedItem(xx);
        Pin p = pinDao.findOne(sp.getIdPin());
        cboPin.setSelectedItem(p);
        txtDai.setText(String.valueOf(sp.getChieuDai()));
        txtRong.setText(String.valueOf(sp.getChieuRong()));
        txtDay.setText(String.valueOf(sp.getDoDay()));
    }

    public void fillForm2(int index) {

        ChiTietSanPham ct = chiTIetSanPhamDAO.findAllByidSP(idSP).get(index);
        SanPham sp = sanPhamDAO.findOne(ct.getIdSP());
        cboLoai.setSelectedItem(sp);
        txtIdCt.setText(String.valueOf(ct.getId()));
        Rom r = romDao.findOne(ct.getIdRom());
        cboRom.setSelectedItem(r);
        MauSac ms = msDao.findOne(ct.getIdMau());
        cboMauSac.setSelectedItem(ms);
        txtGia.setText(String.valueOf(ct.getGia()));
        txtGhiChu.setText(ct.getGhiChu());
        if (ct.getTrangThai() == 1) {
            rdoCon.setSelected(true);
        } else {
            rdoHet.setSelected(true);
        }

    }

    public void fillToImeiForm(int index) {
        int idCTSP = Integer.parseInt(txtIdCt.getText());
        listEmei = imeiDao.FindALLByCTSP(idCTSP);
        Imei imei = listEmei.get(index);
        txtIdImei.setText(String.valueOf(imei.getId()));
        txtMaImei.setText(imei.getMaImei());
        if (imei.getTrangThai() == 1) {
            rdoHoatDong.setSelected(true);
        } else {
            rdoDungHD.setSelected(true);
        }
    }

    public ChiTietSanPham getFormCT() {
        SanPham sp = (SanPham) cboLoai.getSelectedItem();
        Rom rom = (Rom) cboRom.getSelectedItem();
        MauSac ms = (MauSac) cboMauSac.getSelectedItem();
        return new ChiTietSanPham(
                0,
                sp.getId(),
                rom.getId(),
                ms.getId(),
                new BigDecimal(txtGia.getText().trim()),
                txtGhiChu.getText(),
                0,
                0
        );

    }

    public void filltoImeiTable() {
        DefaultTableModel tImei = (DefaultTableModel) tblImei.getModel();
        tImei.setRowCount(0);
        for (Imei i : imeiDao.findAll()) {
            tImei.addRow(new Object[]{
                i.getId(),
                i.getMaImei(),
                i.getTrangThai() == 1 ? "Hoạt động" : "Dừng"
            });
        }
    }

    public void filltoImeiTableLoc() {
        int idCTSP = Integer.parseInt(txtIdCt.getText());
        DefaultTableModel tImei = (DefaultTableModel) tblImei.getModel();
        tImei.setRowCount(0);
        for (Imei i : imeiDao.FindALLByCTSP(idCTSP)) {
            tImei.addRow(new Object[]{
                i.getId(),
                i.getMaImei(),
                i.getTrangThai() == 1 ? "Hoạt động" : "Dừng"
            });
        }
    }

    public Imei getImeiForm() {
        int idctsP = Integer.parseInt(txtIdCt.getText());
        String newImei = imeiDao.getNextImei(); // luôn sinh mã mới từ DB

        return new Imei(
                0,
                newImei,
                1,
                idctsP
        );
    }

    public Imei getImeiForm2() {
        int idctsP = Integer.parseInt(txtIdCt.getText());
        return new Imei(
                Integer.parseInt(txtIdImei.getText()),
                txtMaImei.getText().trim(),
                rdoHoatDong.isSelected() ? 1 : 0,
                idctsP
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        pnlSP = new javax.swing.JPanel();
        btnCapNhatSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboPin = new javax.swing.JComboBox<>();
        cboXuatXu1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtIdSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtDai = new javax.swing.JTextField();
        txtRong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnThemSPExcel = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnLocSP = new javax.swing.JButton();
        tab2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblImei = new javax.swing.JTable();
        btnTHemImei = new javax.swing.JButton();
        btnXoaImei1 = new javax.swing.JButton();
        lblTenSanPham6 = new javax.swing.JLabel();
        txtIdImei = new javax.swing.JTextField();
        jlabellman1 = new javax.swing.JLabel();
        txtMaImei = new javax.swing.JTextField();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoDungHD = new javax.swing.JRadioButton();
        lblTrangThai2 = new javax.swing.JLabel();
        btnXoaToanBoImei = new javax.swing.JButton();
        btnImportImeiExcel = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
        lblTrangThai = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        lblChatLieu = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        cboRom = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        pnlCT = new javax.swing.JPanel();
        btnThemCT = new javax.swing.JButton();
        btnSuaCT = new javax.swing.JButton();
        btnXoaCT = new javax.swing.JButton();
        btnLamMoiCT = new javax.swing.JButton();
        lblKichThuoc1 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        lblTenSanPham2 = new javax.swing.JLabel();
        lblTenSanPham3 = new javax.swing.JLabel();
        txtIdCt = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        lblGiaBan1 = new javax.swing.JLabel();
        btnImportExcelCTSP = new javax.swing.JButton();
        tab3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jlabellman = new javax.swing.JLabel();
        txtTenXX = new javax.swing.JTextField();
        pnlimei = new javax.swing.JPanel();
        btnThemXX = new javax.swing.JButton();
        btnXoaXX = new javax.swing.JButton();
        btnLamMoiXX = new javax.swing.JButton();
        lblTenSanPham5 = new javax.swing.JLabel();
        txtIdXX = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblXX = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPin = new javax.swing.JTable();
        lblTenSanPham7 = new javax.swing.JLabel();
        txtIdPin = new javax.swing.JTextField();
        jlabellman2 = new javax.swing.JLabel();
        txtDungLuongPin = new javax.swing.JTextField();
        pnlimei1 = new javax.swing.JPanel();
        btnThemPin = new javax.swing.JButton();
        btnXoaPin = new javax.swing.JButton();
        btnLamMoiPin = new javax.swing.JButton();
        tab4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRom = new javax.swing.JTable();
        lblTenSanPham8 = new javax.swing.JLabel();
        txtIdRom = new javax.swing.JTextField();
        jlabellman3 = new javax.swing.JLabel();
        txtDungLuongROM = new javax.swing.JTextField();
        pnlimei2 = new javax.swing.JPanel();
        btnThemROM = new javax.swing.JButton();
        btnXoaROM = new javax.swing.JButton();
        btnLamMoiROM = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jlabellman4 = new javax.swing.JLabel();
        txtTenMS = new javax.swing.JTextField();
        pnlimei3 = new javax.swing.JPanel();
        btnThemMS = new javax.swing.JButton();
        btnXoaMS = new javax.swing.JButton();
        btnLamMoiMS = new javax.swing.JButton();
        lblTenSanPham9 = new javax.swing.JLabel();
        txtIdMS = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jlabellman5 = new javax.swing.JLabel();
        txtMaMS = new javax.swing.JTextField();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tab1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(128, 203, 196));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");

        pnlSP.setBackground(new java.awt.Color(255, 255, 255));
        pnlSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCapNhatSP.setBackground(new java.awt.Color(255, 204, 0));
        btnCapNhatSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhatSP.setText("Cập Nhật");
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiSP.setText("Làm Mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        btnXoaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(0, 204, 51));
        btnThemSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSPLayout = new javax.swing.GroupLayout(pnlSP);
        pnlSP.setLayout(pnlSPLayout);
        pnlSPLayout.setHorizontalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pnlSPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhatSP, btnLamMoiSP});

        pnlSPLayout.setVerticalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSPLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pnlSPLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCapNhatSP, btnLamMoiSP});

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Dài");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Xuất xứ ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Pin");

        cboPin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        cboXuatXu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("id");

        txtIdSP.setEditable(false);
        txtIdSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSPActionPerformed(evt);
            }
        });

        txtDai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDaiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Rộng ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Độ dày");

        btnThemSPExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemSPExcel.setText("Nhập từ excel");
        btnThemSPExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdSP, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtDai, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtRong, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btnThemSPExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(cboXuatXu1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboPin, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cboXuatXu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(11, Short.MAX_VALUE)
                        .addComponent(pnlSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtDai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(btnThemSPExcel))
                .addGap(17, 17, 17))
        );

        jPanel5.setBackground(new java.awt.Color(128, 203, 196));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã Điện Thoại", "Xuất xứ", "Pin", "Kích Thước", "Số lượng "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(30);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnLocSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocSP.setText("Tìm Kiếm");
        btnLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 991, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", tab1);

        tab2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(128, 203, 196));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblSanPhamCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên", "Dung Lượng", "Màu", "Giá", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPhamCT);

        tblImei.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Mã Imei", "Trạng thái"
            }
        ));
        tblImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblImei);

        btnTHemImei.setBackground(new java.awt.Color(0, 204, 51));
        btnTHemImei.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTHemImei.setText("Thêm Imei");
        btnTHemImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTHemImeiActionPerformed(evt);
            }
        });

        btnXoaImei1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaImei1.setText("Xóa Imei");
        btnXoaImei1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaImei1ActionPerformed(evt);
            }
        });

        lblTenSanPham6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham6.setText("id");

        txtIdImei.setEditable(false);

        jlabellman1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman1.setText("Mã");

        buttonGroup2.add(rdoHoatDong);
        rdoHoatDong.setText("Hoạt động ");

        buttonGroup2.add(rdoDungHD);
        rdoDungHD.setText("Dừng hoạt động");
        rdoDungHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDungHDActionPerformed(evt);
            }
        });

        lblTrangThai2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai2.setText("Trạng Thái");

        btnXoaToanBoImei.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaToanBoImei.setText("Xóa Toàn Bộ Imei trong Bảng");
        btnXoaToanBoImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaToanBoImeiActionPerformed(evt);
            }
        });

        btnImportImeiExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImportImeiExcel.setText("Nhập Imei từ excel");
        btnImportImeiExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportImeiExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaToanBoImei, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(btnTHemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaImei1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(lblTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoDungHD)
                                            .addComponent(rdoHoatDong)))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlabellman1)
                                            .addComponent(lblTenSanPham6))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaImei, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 17, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImportImeiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnImportImeiExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham6)
                            .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabellman1)
                            .addComponent(txtMaImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoHoatDong)
                            .addComponent(lblTrangThai2))
                        .addGap(18, 18, 18)
                        .addComponent(rdoDungHD)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTHemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaImei1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaToanBoImei, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(128, 203, 196));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 265));

        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn Hàng");

        buttonGroup1.add(rdoHet);
        rdoHet.setText("Hết Hàng");
        rdoHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetActionPerformed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai.setText("Trạng Thái");

        lblGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGiaBan.setText("Giá Bán");

        lblKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKichThuoc.setText("Rom");

        lblChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChatLieu.setText("Màu Sắc");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        cboRom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboRom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRomActionPerformed(evt);
            }
        });

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        pnlCT.setBackground(new java.awt.Color(255, 255, 255));
        pnlCT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemCT.setBackground(new java.awt.Color(0, 204, 51));
        btnThemCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemCT.setText("Thêm SP");
        btnThemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTActionPerformed(evt);
            }
        });

        btnSuaCT.setBackground(new java.awt.Color(255, 204, 0));
        btnSuaCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaCT.setText("Sửa SP");
        btnSuaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTActionPerformed(evt);
            }
        });

        btnXoaCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaCT.setText("Xóa SP");
        btnXoaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTActionPerformed(evt);
            }
        });

        btnLamMoiCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiCT.setText("Làm Mới SP");
        btnLamMoiCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCTLayout = new javax.swing.GroupLayout(pnlCT);
        pnlCT.setLayout(pnlCTLayout);
        pnlCTLayout.setHorizontalGroup(
            pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCTLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnlCTLayout.setVerticalGroup(
            pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCTLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoiCT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaCT)
                    .addComponent(btnXoaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlCTLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaCT, btnThemCT, btnXoaCT});

        lblKichThuoc1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKichThuoc1.setText("Sản phẩm");

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        lblTenSanPham2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblTenSanPham3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham3.setText("id");

        txtIdCt.setEditable(false);

        txtGhiChu.setEditable(false);

        lblGiaBan1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGiaBan1.setText("Chi Tiết");

        btnImportExcelCTSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImportExcelCTSP.setText("Nhập sp từ excel");
        btnImportExcelCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTenSanPham2)
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblGiaBan1)
                        .addGap(105, 105, 105)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lblTenSanPham3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdCt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lblGiaBan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblKichThuoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChatLieu)
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKichThuoc))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboRom, 0, 125, Short.MAX_VALUE)
                                    .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(rdoHet))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(rdoCon)))
                        .addGap(18, 18, 18)
                        .addComponent(pnlCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImportExcelCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChatLieu, lblGiaBan, lblKichThuoc, lblTrangThai});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblChatLieu)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTrangThai)
                                    .addComponent(rdoCon)
                                    .addComponent(rdoHet))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblTenSanPham3)
                                            .addComponent(txtIdCt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblKichThuoc))))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGiaBan))
                                .addGap(43, 43, 43)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham2)
                            .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKichThuoc1)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGiaBan1)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(pnlCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImportExcelCTSP)))
                .addGap(50, 50, 50))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblChatLieu, lblGiaBan, lblKichThuoc, lblTrangThai});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboMauSac, cboRom});

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", tab2);

        jPanel12.setBackground(new java.awt.Color(128, 203, 196));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Xuất Xứ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(1000, 265));

        jlabellman.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman.setText("Tên xuất xứ");

        pnlimei.setBackground(new java.awt.Color(255, 255, 255));
        pnlimei.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemXX.setBackground(new java.awt.Color(0, 204, 51));
        btnThemXX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemXX.setText("Thêm");
        btnThemXX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXXActionPerformed(evt);
            }
        });

        btnXoaXX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaXX.setText("Xóa");
        btnXoaXX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaXXActionPerformed(evt);
            }
        });

        btnLamMoiXX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiXX.setText("Làm Mới");
        btnLamMoiXX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiXXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlimeiLayout = new javax.swing.GroupLayout(pnlimei);
        pnlimei.setLayout(pnlimeiLayout);
        pnlimeiLayout.setHorizontalGroup(
            pnlimeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimeiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThemXX, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnLamMoiXX)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlimeiLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnXoaXX, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlimeiLayout.setVerticalGroup(
            pnlimeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimeiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlimeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiXX, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnThemXX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaXX, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblTenSanPham5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham5.setText("Id");

        txtIdXX.setEditable(false);

        tblXX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblXX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Tên xuất xứ"
            }
        ));
        tblXX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXXMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblXX);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenSanPham5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabellman))
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdXX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenXX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(pnlimei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(pnlimei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdXX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenSanPham5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenXX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabellman))
                        .addGap(103, 103, 103))))
        );

        jPanel14.setBackground(new java.awt.Color(128, 203, 196));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Pin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblPin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblPin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Dung lượng pin"
            }
        ));
        tblPin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPinMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPin);

        lblTenSanPham7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham7.setText("Id");

        txtIdPin.setEditable(false);

        jlabellman2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman2.setText("Dung Lượng");

        pnlimei1.setBackground(new java.awt.Color(255, 255, 255));
        pnlimei1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemPin.setBackground(new java.awt.Color(0, 204, 51));
        btnThemPin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemPin.setText("Thêm");
        btnThemPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPinActionPerformed(evt);
            }
        });

        btnXoaPin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaPin.setText("Xóa");
        btnXoaPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPinActionPerformed(evt);
            }
        });

        btnLamMoiPin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiPin.setText("Làm Mới");
        btnLamMoiPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiPinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlimei1Layout = new javax.swing.GroupLayout(pnlimei1);
        pnlimei1.setLayout(pnlimei1Layout);
        pnlimei1Layout.setHorizontalGroup(
            pnlimei1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThemPin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnLamMoiPin)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlimei1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnXoaPin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlimei1Layout.setVerticalGroup(
            pnlimei1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlimei1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiPin, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnThemPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaPin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jlabellman2)
                        .addGap(18, 18, 18)
                        .addComponent(txtDungLuongPin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(lblTenSanPham7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(txtIdPin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(pnlimei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlimei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtIdPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenSanPham7))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDungLuongPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabellman2))))))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Xuất xứ && Pin", tab3);

        jPanel16.setBackground(new java.awt.Color(128, 203, 196));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Rom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblRom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblRom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Dung lượng rom"
            }
        ));
        tblRom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRomMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblRom);

        lblTenSanPham8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham8.setText("Id");

        txtIdRom.setEditable(false);

        jlabellman3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman3.setText("Dung Lượng");

        pnlimei2.setBackground(new java.awt.Color(255, 255, 255));
        pnlimei2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemROM.setBackground(new java.awt.Color(0, 204, 51));
        btnThemROM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemROM.setText("Thêm");
        btnThemROM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemROMActionPerformed(evt);
            }
        });

        btnXoaROM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaROM.setText("Xóa");
        btnXoaROM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaROMActionPerformed(evt);
            }
        });

        btnLamMoiROM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiROM.setText("Làm Mới");
        btnLamMoiROM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiROMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlimei2Layout = new javax.swing.GroupLayout(pnlimei2);
        pnlimei2.setLayout(pnlimei2Layout);
        pnlimei2Layout.setHorizontalGroup(
            pnlimei2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThemROM, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnLamMoiROM)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlimei2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnXoaROM, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlimei2Layout.setVerticalGroup(
            pnlimei2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlimei2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiROM, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnThemROM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaROM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jlabellman3)
                        .addGap(18, 18, 18)
                        .addComponent(txtDungLuongROM, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(lblTenSanPham8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(txtIdRom, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(pnlimei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlimei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtIdRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenSanPham8))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDungLuongROM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabellman3))))))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(128, 203, 196));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Màu Sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(1000, 265));

        jlabellman4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman4.setText("Tên xuất xứ");

        pnlimei3.setBackground(new java.awt.Color(255, 255, 255));
        pnlimei3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemMS.setBackground(new java.awt.Color(0, 204, 51));
        btnThemMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemMS.setText("Thêm");
        btnThemMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMSActionPerformed(evt);
            }
        });

        btnXoaMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaMS.setText("Xóa");
        btnXoaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMSActionPerformed(evt);
            }
        });

        btnLamMoiMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiMS.setText("Làm Mới");
        btnLamMoiMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlimei3Layout = new javax.swing.GroupLayout(pnlimei3);
        pnlimei3.setLayout(pnlimei3Layout);
        pnlimei3Layout.setHorizontalGroup(
            pnlimei3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThemMS, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnLamMoiMS)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlimei3Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnXoaMS, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlimei3Layout.setVerticalGroup(
            pnlimei3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlimei3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlimei3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiMS, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnThemMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaMS, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblTenSanPham9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham9.setText("Id");

        txtIdMS.setEditable(false);

        tblMauSac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Mã màu", "Tên màu"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblMauSac);

        jlabellman5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabellman5.setText("Mã màu");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSanPham9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabellman4))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jlabellman5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaMS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addComponent(pnlimei3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenSanPham9))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(pnlimei3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabellman4))))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabellman5))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Màu sắc && Rom", tab4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void tblSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTMouseClicked
        // TODO add your handling code here:
        int index = tblSanPhamCT.getSelectedRow();
        fillForm2(index);
        int idCTSP = Integer.parseInt(txtIdCt.getText());
        ghiChuChiTietCu = txtGhiChu.getText();
//        System.out.println(ghiChuChiTietCu);
        DefaultTableModel tImei = (DefaultTableModel) tblImei.getModel();
        tImei.setRowCount(0);
        for (Imei i : imeiDao.FindALLByCTSP(idCTSP)) {
            tImei.addRow(new Object[]{
                i.getId(),
                i.getMaImei(),
                i.getTrangThai() == 1 ? "Hoạt động" : "Dừng"
            });
        }

    }//GEN-LAST:event_tblSanPhamCTMouseClicked

    private void btnLamMoiCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới ?");
        if (confirm == JOptionPane.YES_OPTION) {
            txtGia.setText("");
            txtGhiChu.setText("");
            cboLoai.setSelectedIndex(0);
            cboRom.setSelectedIndex(0);
            cboMauSac.setSelectedIndex(0);
            rdoCon.setSelected(true);
        }
    }//GEN-LAST:event_btnLamMoiCTActionPerformed

    private void btnXoaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTActionPerformed

        int index = tblSanPhamCT.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtIdCt.getText());
        for (HoaDonChiTiet hoaDonChiTiet : hdctDao.findAll()) {
            if (hoaDonChiTiet.getIdSanPham() == id) {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đã từng nằm trong hóa đơn, không thể xóa, hãy đổi thành hết hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (Imei imei : imeiDao.findAll()) {
            if (imei.getIdCTSP() == id) {
                JOptionPane.showMessageDialog(this, "Không được xóa sản phẩm ct đã có các Imei đã liên kết", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa ?");
        if (confirm == JOptionPane.YES_OPTION) {

            chiTIetSanPhamDAO.delete(id);
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnXoaCTActionPerformed

    private void btnSuaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTActionPerformed
        int index = tblSanPhamCT.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá!");
            txtGia.requestFocus();
            return;
        }
        try {
            Double.parseDouble(txtGia.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải khồn hợp lệ");
            txtGia.requestFocus();
            return;
        }
        if (txtGhiChu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ghi chú!");
            txtGhiChu.requestFocus();
            return;
        }

        String ghiChu = txtGhiChu.getText();

        if (!ghiChu.equals(ghiChuChiTietCu)) {
            for (ChiTietSanPham chiTietSanPham : chiTIetSanPhamDAO.findAllByidSP(idSP)) {
                if (chiTietSanPham.getGhiChu().equalsIgnoreCase(ghiChu)) {
                    JOptionPane.showMessageDialog(this, "Không thể sửa sản phẩm cùng kiểu với sản phẩm đã tồn tại");
                    return;
                }
            }
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn sửa sản phẩm ?");
        if (confirm == JOptionPane.YES_OPTION) {
            SanPham sp = (SanPham) cboLoai.getSelectedItem();
            Rom rom = (Rom) cboRom.getSelectedItem();
            MauSac ms = (MauSac) cboMauSac.getSelectedItem();
            ChiTietSanPham ctsp = new ChiTietSanPham();
            int idCtsp = Integer.parseInt(txtIdCt.getText());
            ctsp.setId(idCtsp);
            ctsp.setIdSP(sp.getId());
            ctsp.setIdRom(rom.getId());
            ctsp.setIdMau(ms.getId());
            ctsp.setGia(new BigDecimal(txtGia.getText().trim()));
            ctsp.setGhiChu(txtGhiChu.getText());
            ctsp.setTrangThai(rdoCon.isSelected() ? 1 : 0);

            chiTIetSanPhamDAO.update(ctsp);
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnSuaCTActionPerformed

    private void btnThemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTActionPerformed
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá");
            txtGia.requestFocus();
            return;
        }
        try {
            Double.parseDouble(txtGia.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải khồn hợp lệ");
            txtGia.requestFocus();
            return;
        }
        if (txtGhiChu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ghi chú");
            txtGhiChu.requestFocus();
            return;
        }

        String ghiChu = txtGhiChu.getText();
        for (ChiTietSanPham chiTietSanPham : chiTIetSanPhamDAO.findAll()) {
            if (chiTietSanPham.getGhiChu().equalsIgnoreCase(ghiChu)) {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm cùng kiểu với sản phẩm đã tồn tại");
                return;
            }
        }
//        if (!rdoCon.isSelected() && !rdoHet.isSelected()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn CÒN HÀNG hoặc HẾT HÀNG");
//            return;
//        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm sản phẩm ?");
        if (confirm == JOptionPane.YES_OPTION) {
            chiTIetSanPhamDAO.create(getFormCT());
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnThemCTActionPerformed

    private void rdoHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetActionPerformed

    private void btnLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocSPActionPerformed
        // TODO add your handling code here:
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        modelSP.setRowCount(0);
        String maS = txtTimKiem.getText();
        List<SanPham> ketQua = sanPhamDAO.search("%" + maS + "%");

        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có mã sản phẩm đang tìm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            fillToTable();
            return;
        }

        for (SanPham sanPham : sanPhamDAO.search("%" + maS + "%")) {
            Object[] row = {
                sanPham.getId(),
                sanPham.getMa(),
                xxDao.findOne(sanPham.getIdXX()).getNoiSanXuat(),
                pinDao.findOne(sanPham.getIdPin()).getDungLuong() + " mAh",
                sanPham.getChieuDai() + " x " + sanPham.getChieuRong() + " x " + sanPham.getDoDay() + " mm",
                sanPham.getSoLuong()
            };
            modelSP.addRow(row);
        }
    }//GEN-LAST:event_btnLocSPActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtIdSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSPActionPerformed
        // TODO add your handling code here:    
    }//GEN-LAST:event_txtIdSPActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        String ma = txtMaSP.getText().trim().replaceAll("\\s+", "");
        String ten = txtTenSP.getText().trim().replaceAll("\\s+", "");

        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            txtMaSP.requestFocus();
            return;
        }

        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm!");
            txtTenSP.requestFocus();
            return;
        }

        if (txtDai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chiều dài!");
            txtDai.requestFocus();
            return;
        }
        BigDecimal dai;
        try {
            dai = new BigDecimal(txtDai.getText().trim());
            if (dai.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Chiều dài không được là số âm!");
                txtDai.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều dài phải là số hợp lệ!");
            txtDai.requestFocus();
            return;
        }

        BigDecimal day;
        if (txtDay.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập độ dày!");
            txtDay.requestFocus();
            return;
        }
        try {
            day = new BigDecimal(txtDay.getText().trim());
            if (day.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Độ dày không được là số âm!");
                txtDay.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều dày phải là số hợp lệ!");
            txtDay.requestFocus();
            return;
        }

        BigDecimal rong;
        if (txtRong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chiều rộng!");
            txtRong.requestFocus();
            return;
        }
        try {
            rong = new BigDecimal(txtRong.getText().trim());
            if (rong.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Chiều rộng không được là số âm!");
                txtRong.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều rộng phải là số hợp lệ!");
            txtRong.requestFocus();
            return;
        }

        for (SanPham sanPham : sanPhamDAO.findAll()) {
            if (sanPham.getMa().replaceAll("\\s+", "").toLowerCase().equalsIgnoreCase(ma)) {
                JOptionPane.showMessageDialog(this, "Không được thêm mã sản phẩm đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (SanPham sanPham : sanPhamDAO.findAll()) {
            if (sanPham.getTen().replaceAll("\\s+", "").toLowerCase().equalsIgnoreCase(ten)) {
                JOptionPane.showMessageDialog(this, "Không được thêm tên sản phẩm đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm sp ?");
        if (confirm == JOptionPane.YES_OPTION) {
            sanPhamDAO.create(getForm());
            fillToTable();
            fillToTable2();
            fillCbo();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int index = tblSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtIdSP.getText());
        for (ChiTietSanPham ctsp : chiTIetSanPhamDAO.findAll()) {
            if (ctsp.getIdSP() == id) {
                JOptionPane.showMessageDialog(this, "Không được xóa sản phẩm đã có có các sản phẩm chi tiết ở trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa ?");
        if (confirm == JOptionPane.YES_OPTION) {
            sanPhamDAO.delete(id);
            fillToTable();
            fillCbo();
            JOptionPane.showMessageDialog(this, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới ?");
        if (confirm == JOptionPane.YES_OPTION) {
            txtMaSP.setText("");
            txtTenSP.setText("");
            txtDai.setText("");
            cboPin.setSelectedIndex(0);
            cboXuatXu1.setSelectedIndex(0);
            txtDai.setText("");
            txtDay.setText("");
            txtRong.setText("");

            fillToTable();
        }

    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        String ma = txtMaSP.getText().trim().replaceAll("\\s+", "");
        String ten = txtTenSP.getText().trim().replaceAll("\\s+", "");
        String maSpcu1 = maSpcu.trim().replaceAll("\\s+", "");
        String tenSpcu2 = tenSpcu.trim().replaceAll("\\s+", "");
//        System.out.println(ten);
//        System.out.println(maSpcu1);
//        System.out.println(tenSpcu2);
        int index = tblSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!ma.equals(maSpcu1)) {
            for (SanPham sanPham : sanPhamDAO.findAll()) {
                if (sanPham.getMa().replaceAll("\\s+", "").toLowerCase().equalsIgnoreCase(ma)) {
                    JOptionPane.showMessageDialog(this, "Không được cập nhật mã sản phẩm đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        if (!ten.equals(tenSpcu2)) {
            for (SanPham sanPham : sanPhamDAO.findAll()) {
                if (sanPham.getTen().replaceAll("\\s+", "").toLowerCase().equalsIgnoreCase(ten)) {
                    JOptionPane.showMessageDialog(this, "Không được cập nhật tên sản phẩm đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            txtMaSP.requestFocus();
            return;
        }

        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm!");
            txtTenSP.requestFocus();
            return;
        }

        if (txtDai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chiều dài!");
            txtDai.requestFocus();
            return;
        }
        BigDecimal dai;
        try {
            dai = new BigDecimal(txtDai.getText().trim());
            if (dai.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Chiều dài không được là số âm!");
                txtDai.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều dài phải là số hợp lệ!");
            txtDai.requestFocus();
            return;
        }

        BigDecimal day;
        if (txtDay.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập độ dày!");
            txtDay.requestFocus();
            return;
        }
        try {
            day = new BigDecimal(txtDay.getText().trim());
            if (day.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Độ dày không được là số âm!");
                txtDay.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều dày phải là số hợp lệ!");
            txtDay.requestFocus();
            return;
        }

        BigDecimal rong;
        if (txtRong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chiều rộng!");
            txtRong.requestFocus();
            return;
        }
        try {
            rong = new BigDecimal(txtRong.getText().trim());
            if (rong.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Chiều rộng không được là số âm!");
                txtRong.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Chiều rộng phải là số hợp lệ!");
            txtRong.requestFocus();
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn cập nhật sản phẩm ?");
        if (confirm == JOptionPane.YES_OPTION) {
            XuatXu xx = (XuatXu) cboXuatXu1.getSelectedItem();
            Pin p = (Pin) cboPin.getSelectedItem();

            SanPham sp = new SanPham();
            sp.setId(Integer.parseInt(txtIdSP.getText()));
            sp.setMa(ma);
            sp.setTen(txtTenSP.getText().trim());
            sp.setIdXX(xx.getId());
            sp.setIdPin(p.getId());
            sp.setChieuDai(new BigDecimal(txtDai.getText().trim()));
            sp.setChieuRong(new BigDecimal(txtRong.getText().trim()));
            sp.setDoDay(new BigDecimal(txtDay.getText().trim()));
            sanPhamDAO.update(sp);

            fillToTable();
            fillToTable2();
            fillCbo();
            JOptionPane.showMessageDialog(this, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnThemXXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXXActionPerformed
        // TODO add your handling code here:

        String noiSanXuat = txtTenXX.getText().trim().replaceAll("\\s+", "");

        if (noiSanXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên xuất xứ không được để trống!");
            txtTenXX.requestFocus();
            return;
        }

        if (noiSanXuat.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên xuất xứ không được vượt quá 50 ký tự!");
            txtTenXX.requestFocus();
            return;
        }

        for (XuatXu xuatXu : xxDao.findAll()) {
            if (xuatXu.getNoiSanXuat().replaceAll("\\s+", "").toLowerCase().equalsIgnoreCase(noiSanXuat)) {
                JOptionPane.showMessageDialog(this, "Không được thêm xuất xứ đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm xuất xứ ?");
        if (confirm == JOptionPane.YES_OPTION) {
            XuatXu xx = new XuatXu();
            xx.setId(0);
            xx.setNoiSanXuat(txtTenXX.getText());
            xxDao.create(xx);
            fillToXXTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnThemXXActionPerformed

    private void btnXoaXXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaXXActionPerformed
        // TODO add your handling code here:
        int index = tblXX.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng xuất xứ muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idXX = Integer.parseInt(txtIdXX.getText());
        for (SanPham sanPham : sanPhamDAO.findAll()) {
            if (sanPham.getIdXX() == idXX) {
                JOptionPane.showMessageDialog(this, "Không được xóa xuất xứ đã có các sản phẩm đã liên kết", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa xuất xứ?");
        if (confirm == JOptionPane.YES_OPTION) {
            xxDao.delete(idXX);
            fillToXXTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnXoaXXActionPerformed

    private void btnLamMoiXXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiXXActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới ?");
        if (confirm == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btnLamMoiXXActionPerformed

    private void tblPinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPinMouseClicked
        // TODO add your handling code here:
        int index = tblPin.getSelectedRow();
        Pin p = pinDao.findAll().get(index);
        txtIdPin.setText(String.valueOf(p.getId()));
        txtDungLuongPin.setText(String.valueOf(p.getDungLuong()));
    }//GEN-LAST:event_tblPinMouseClicked

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboLoaiActionPerformed

    private void cboRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRomActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboRomActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboMauSacActionPerformed

    private void tblImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiMouseClicked
        // TODO add your handling code here:
        int index = tblImei.getSelectedRow();
        int id = (int) tblImei.getValueAt(index, 0);
        Imei imei = imeiDao.findOne(id);
        fillToImeiForm(index);
    }//GEN-LAST:event_tblImeiMouseClicked

    private void btnTHemImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTHemImeiActionPerformed
        // TODO add your handling code here:
        String maImei = txtMaImei.getText().trim().replaceAll("\\s+", "");;
//        for (Imei imei : imeiDao.findAll()) {
//            if (imei.getMaImei().equals(maImei)) {
//                JOptionPane.showMessageDialog(this, "Không được thêm giống mã Imei đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                txtMaImei.requestFocus();
//                return;
//            }
//        }
//        if (txtMaImei.getText().trim().length() != 15) {
//            JOptionPane.showMessageDialog(this, "Mã IMEI phải đúng 15 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            txtMaImei.requestFocus();
//            return;
//        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm imei ?");
        if (confirm == JOptionPane.YES_OPTION) {
            int idctsP = Integer.parseInt(txtIdCt.getText());
            imeiDao.create(getImeiForm());
            ChiTietSanPham ctsp = chiTIetSanPhamDAO.findOne(idctsP);
            int newSOLuong = ctsp.getSoLuong() + 1;
            ctsp.setSoLuong(newSOLuong);
            chiTIetSanPhamDAO.updateSL(ctsp);
            chiTIetSanPhamDAO.changeAvailable(idctsP);
            chiTIetSanPhamDAO.changeAvailable2(idctsP);

            filltoImeiTableLoc();
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnTHemImeiActionPerformed

    private void btnXoaImei1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaImei1ActionPerformed
        // TODO add your handling code here:
        int index = tblImei.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa ?");
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtIdImei.getText());
            imeiDao.delete(id);
            int idctsP = Integer.parseInt(txtIdCt.getText());
            ChiTietSanPham ctsp = chiTIetSanPhamDAO.findOne(idctsP);
            int newSOLuong = ctsp.getSoLuong() - 1;
            ctsp.setSoLuong(newSOLuong);
            chiTIetSanPhamDAO.updateSL(ctsp);
            chiTIetSanPhamDAO.changeAvailable(idctsP);

            filltoImeiTableLoc();
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_btnXoaImei1ActionPerformed

    private void rdoDungHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDungHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDungHDActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        fillForm(index);
        tenSpcu = txtTenSP.getText();
        maSpcu = txtMaSP.getText();

        int idSp = Integer.parseInt(txtIdSP.getText());
        if (evt.getClickCount() == 2) {
            jTabbedPane1.setSelectedIndex(1);

            idSP = idSp;

            cboLoai.setSelectedItem(sanPhamDAO.findOne(idSP));

            fillToTable2();

        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemSPExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPExcelActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel để import");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                ImportSanPhamExcel importer = new ImportSanPhamExcel();
                importer.importExcelToDatabase(file);

                fillToTable();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Lỗi khi import: " + e.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemSPExcelActionPerformed

    private void btnImportExcelCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelCTSPActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Chọn file Excel chi tiết sản phẩm");
            chooser.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx, *.xls)", "xlsx", "xls"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                new ImportChiTietSanPhamExcel().importExcelToDatabase(file, idSP);

                // TODO: gọi hàm reload bảng
                fillToTable2();
                fillToTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi import: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnImportExcelCTSPActionPerformed

    private void btnImportImeiExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportImeiExcelActionPerformed
        int idCTSP = Integer.parseInt(txtIdCt.getText());
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx, *.xls)", "xlsx", "xls"));
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                // Lấy số lượng IMEI vừa thêm
                int addedCount = ImportImeiExcel.importExcel(file, idCTSP);

                if (addedCount > 0) {
                    // Cập nhật soLuong hiện tại của ChiTietSanPham
                    ChiTietSanPham ctsp = chiTIetSanPhamDAO.findOne(idCTSP);
                    ctsp.setSoLuong(ctsp.getSoLuong() + addedCount); // cộng số lượng mới
                    chiTIetSanPhamDAO.updateSL(ctsp);
                }
                chiTIetSanPhamDAO.changeAvailable(idCTSP);
                chiTIetSanPhamDAO.changeAvailable2(idCTSP);
                filltoImeiTableLoc();
                fillToTable2();
                fillToTable();

                JOptionPane.showMessageDialog(this, "Import thành công " + addedCount + " IMEI!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi import: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnImportImeiExcelActionPerformed

    private void btnXoaToanBoImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaToanBoImeiActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa toàn bộ Imei trong bảng ?");
        if (confirm == JOptionPane.YES_OPTION) {
            int idCTSP = Integer.parseInt(txtIdCt.getText());
            imeiDao.deleteAllImeiFromIdCTSP(idCTSP);
            int idctsP = Integer.parseInt(txtIdCt.getText());
            ChiTietSanPham ctsp = chiTIetSanPhamDAO.findOne(idctsP);
            int newSOLuong = 0;
            ctsp.setSoLuong(newSOLuong);
            chiTIetSanPhamDAO.updateSL(ctsp);
            chiTIetSanPhamDAO.changeAvailable(idctsP);

            filltoImeiTableLoc();
            fillToTable2();
            fillToTable();
            JOptionPane.showMessageDialog(this, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_btnXoaToanBoImeiActionPerformed

    private void tblXXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXXMouseClicked
        // TODO add your handling code here:
        int index = tblXX.getSelectedRow();
        XuatXu xx = xxDao.findAll().get(index);
        txtIdXX.setText(String.valueOf(xx.getId()));
        txtTenXX.setText(xx.getNoiSanXuat());
    }//GEN-LAST:event_tblXXMouseClicked

    private void btnThemPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPinActionPerformed
        // TODO add your handling code here:
        String dungLuongStr = txtDungLuongPin.getText().trim().replaceAll("\\s+", "");

        if (dungLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dung lượng pin không được để trống!");
            txtDungLuongPin.requestFocus();
            return;
        }

        int dungLuong;

        try {
            dungLuong = Integer.parseInt(dungLuongStr);
            if (dungLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Dung lượng pin phải là số nguyên dương");
                txtDungLuongPin.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dung lượng pin phải là số nguyên hợp lệ");
            txtDungLuongPin.requestFocus();
            return;
        }

        if (dungLuong < 1000 || dungLuong > 5000) {
            JOptionPane.showMessageDialog(this, "Dung lượng pin không hợp lệ (1000–5000 mAh)");
            txtDungLuongPin.requestFocus();
            return;
        }

        for (Pin pin : pinDao.findAll()) {
            if (pin.getDungLuong() == Integer.parseInt(dungLuongStr)) {
                JOptionPane.showMessageDialog(this, "Dung lượng pin đã tồn tại");
                txtDungLuongPin.requestFocus();
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm pin?");
        if (confirm == JOptionPane.YES_OPTION) {
            Pin p = new Pin();
            p.setDungLuong(Integer.parseInt(txtDungLuongPin.getText()));
            pinDao.create(p);
            fillToPinTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnThemPinActionPerformed

    private void btnXoaPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPinActionPerformed
        // TODO add your handling code here:
        int index = tblPin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng pin muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idPin = Integer.parseInt(txtIdPin.getText());
        for (SanPham sanPham : sanPhamDAO.findAll()) {
            if (sanPham.getIdPin() == idPin) {
                JOptionPane.showMessageDialog(this, "Không được xóa pin đã có các sản phẩm đã liên kết", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là xóa pin ?");
        if (confirm == JOptionPane.YES_OPTION) {
            pinDao.delete(idPin);
            fillToPinTable();
            fillCbo();

        }
    }//GEN-LAST:event_btnXoaPinActionPerformed

    private void btnLamMoiPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiPinActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới ?");
        if (confirm == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btnLamMoiPinActionPerformed

    private void tblRomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRomMouseClicked
        // TODO add your handling code here:
        int index = tblRom.getSelectedRow();
        Rom r = romDao.findAll().get(index);
        txtIdRom.setText(String.valueOf(r.getId()));
        txtDungLuongROM.setText(String.valueOf(r.getDungLuong()));
    }//GEN-LAST:event_tblRomMouseClicked

    private void btnThemROMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemROMActionPerformed
        // TODO add your handling code here:
        String dungLuongStr = txtDungLuongROM.getText().trim().replaceAll("\\s+", "");

        if (dungLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dung lượng rom không được để trống!");
            txtDungLuongROM.requestFocus();
            return;
        }

        int dungLuong;

        try {
            dungLuong = Integer.parseInt(dungLuongStr);
            if (dungLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Dung lượng rom phải là số nguyên dương!");
                txtDungLuongROM.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dung lượng rom phải là số nguyên hợp lệ!");
            txtDungLuongROM.requestFocus();
            return;
        }

        int[] romHopLe = {4, 8, 16, 32, 64, 128, 256, 512, 1024};
        boolean hopLe = false;

        for (int r : romHopLe) {
            if (dungLuong == r) {
                hopLe = true;
                break;
            }
        }

        if (!hopLe) {
            JOptionPane.showMessageDialog(this, "Dung lượng ROM không hợp lệ! Chọn 4, 8, 16, 32, 64, 128, 256, 512 hoặc 1024 GB.");
            txtDungLuongROM.requestFocus();
            return;
        }

        for (Rom rom : romDao.findAll()) {
            if (rom.getDungLuong() == Integer.parseInt(dungLuongStr)) {
                JOptionPane.showMessageDialog(this, "Đã tồn tại rom này rồi");
                txtDungLuongROM.requestFocus();
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm rom ?");
        if (confirm == JOptionPane.YES_OPTION) {
            Rom r = new Rom();
            r.setDungLuong(Integer.parseInt(txtDungLuongROM.getText()));
            romDao.create(r);
            fillToRomTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnThemROMActionPerformed

    private void btnXoaROMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaROMActionPerformed
        // TODO add your handling code here:
        int index = tblRom.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng rom muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idRom = Integer.parseInt(txtIdRom.getText());
        for (ChiTietSanPham ctsp : chiTIetSanPhamDAO.findAll()) {
            if (ctsp.getIdRom() == idRom) {
                JOptionPane.showMessageDialog(this, "Không được xóa rom đã có các sản phẩm đã liên kết", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa rom ?");
        if (confirm == JOptionPane.YES_OPTION) {
            romDao.delete(idRom);
            fillToRomTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnXoaROMActionPerformed

    private void btnLamMoiROMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiROMActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới ?");
        if (confirm == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btnLamMoiROMActionPerformed

    private void btnThemMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMSActionPerformed
        // TODO add your handling code here:
        String maMau = txtMaMS.getText().trim().replaceAll("\\s+", "");
        String tenMau = txtTenMS.getText().trim().replaceAll("\\s+", "");

        if (maMau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã màu không được để trống!");
            txtMaMS.requestFocus();
            return;
        }
        if (tenMau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên màu không được để trống!");
            txtTenMS.requestFocus();
            return;
        }

        if (maMau.length() > 10) {
            JOptionPane.showMessageDialog(this, "Mã màu không được vượt quá 10 ký tự!");
            txtMaMS.requestFocus();
            return;
        }
        if (tenMau.length() > 10) {
            JOptionPane.showMessageDialog(this, "Tên màu không được vượt quá 10 ký tự!");
            txtTenMS.requestFocus();
            return;
        }

        for (MauSac mauSac : msDao.findAll()) {
            if (mauSac.getMaMau().equalsIgnoreCase(maMau)) {
                JOptionPane.showMessageDialog(this, "Không được thêm mã màu sắc đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (mauSac.getTen().equalsIgnoreCase(tenMau)) {
                JOptionPane.showMessageDialog(this, "Không được thêm tên màu sắc đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn thêm màu ?");
        if (confirm == JOptionPane.YES_OPTION) {
            MauSac ms = new MauSac();
            ms.setMaMau(txtMaMS.getText());
            ms.setTen(txtTenMS.getText());
            msDao.create(ms);
            fillToMauSacTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnThemMSActionPerformed

    private void btnXoaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMSActionPerformed
        // TODO add your handling code here:
        int index = tblMauSac.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng màu sắc muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idMauSac = Integer.parseInt(txtIdMS.getText());
        for (ChiTietSanPham ctsp : chiTIetSanPhamDAO.findAll()) {
            if (ctsp.getIdMau() == idMauSac) {
                JOptionPane.showMessageDialog(this, "Không được xóa màu sắc đã có các sản phẩm đã liên kết", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa màu ?");
        if (confirm == JOptionPane.YES_OPTION) {
            msDao.delete(idMauSac);
            fillToMauSacTable();
            fillCbo();
        }
    }//GEN-LAST:event_btnXoaMSActionPerformed

    private void btnLamMoiMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiMSActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn làm mới?");
        if (confirm == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btnLamMoiMSActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        int index = tblMauSac.getSelectedRow();
        MauSac ms = msDao.findAll().get(index);
        txtIdMS.setText(String.valueOf(ms.getId()));
        txtMaMS.setText(ms.getMaMau());
        txtTenMS.setText(ms.getTen());
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void txtDaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDaiActionPerformed
//private void btnSuaImei1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
//        // TODO add your handling code here:
//        int row = tblImei.getSelectedRow();
//        if (row != -1) {
//            imeiCu = (String) tblImei.getValueAt(row, 1);
//            System.out.println(imeiCu);
//        }
//        String maImeiMoi = txtMaImei.getText().trim().replaceAll("\\s+", "");
//        if (!maImeiMoi.equals(imeiCu)) {
//            for (Imei imei : imeiDao.findAll()) {
//                if (imei.getMaImei().equals(maImeiMoi)) {
//                    JOptionPane.showMessageDialog(this, "Không được sửa giống mã Imei đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//        }
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn sửa imei ?");
//        if (confirm == JOptionPane.YES_OPTION) {
//            int idctsP = Integer.parseInt(txtIdCt.getText());
//            imeiDao.update(getImeiForm2());
//
//            chiTIetSanPhamDAO.changeAvailable2(idctsP);
//            chiTIetSanPhamDAO.changeAvailable(idctsP);
//            filltoImeiTableLoc();
//            fillToTable2();
//            fillToTable();
//            JOptionPane.showMessageDialog(this, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        }
//    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnImportExcelCTSP;
    private javax.swing.JButton btnImportImeiExcel;
    private javax.swing.JButton btnLamMoiCT;
    private javax.swing.JButton btnLamMoiMS;
    private javax.swing.JButton btnLamMoiPin;
    private javax.swing.JButton btnLamMoiROM;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiXX;
    private javax.swing.JButton btnLocSP;
    private javax.swing.JButton btnSuaCT;
    private javax.swing.JButton btnTHemImei;
    private javax.swing.JButton btnThemCT;
    private javax.swing.JButton btnThemMS;
    private javax.swing.JButton btnThemPin;
    private javax.swing.JButton btnThemROM;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPExcel;
    private javax.swing.JButton btnThemXX;
    private javax.swing.JButton btnXoaCT;
    private javax.swing.JButton btnXoaImei1;
    private javax.swing.JButton btnXoaMS;
    private javax.swing.JButton btnXoaPin;
    private javax.swing.JButton btnXoaROM;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaToanBoImei;
    private javax.swing.JButton btnXoaXX;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JComboBox<String> cboRom;
    private javax.swing.JComboBox<String> cboXuatXu1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlabellman;
    private javax.swing.JLabel jlabellman1;
    private javax.swing.JLabel jlabellman2;
    private javax.swing.JLabel jlabellman3;
    private javax.swing.JLabel jlabellman4;
    private javax.swing.JLabel jlabellman5;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaBan1;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblKichThuoc1;
    private javax.swing.JLabel lblTenSanPham2;
    private javax.swing.JLabel lblTenSanPham3;
    private javax.swing.JLabel lblTenSanPham5;
    private javax.swing.JLabel lblTenSanPham6;
    private javax.swing.JLabel lblTenSanPham7;
    private javax.swing.JLabel lblTenSanPham8;
    private javax.swing.JLabel lblTenSanPham9;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai2;
    private javax.swing.JPanel pnlCT;
    private javax.swing.JPanel pnlSP;
    private javax.swing.JPanel pnlimei;
    private javax.swing.JPanel pnlimei1;
    private javax.swing.JPanel pnlimei2;
    private javax.swing.JPanel pnlimei3;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoDungHD;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JTable tblImei;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblPin;
    private javax.swing.JTable tblRom;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamCT;
    private javax.swing.JTable tblXX;
    private javax.swing.JTextField txtDai;
    private javax.swing.JTextField txtDay;
    private javax.swing.JTextField txtDungLuongPin;
    private javax.swing.JTextField txtDungLuongROM;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtIdCt;
    private javax.swing.JTextField txtIdImei;
    private javax.swing.JTextField txtIdMS;
    private javax.swing.JTextField txtIdPin;
    private javax.swing.JTextField txtIdRom;
    private javax.swing.JTextField txtIdSP;
    private javax.swing.JTextField txtIdXX;
    private javax.swing.JTextField txtMaImei;
    private javax.swing.JTextField txtMaMS;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtRong;
    private javax.swing.JTextField txtTenMS;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenXX;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForm(SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham getForm() {
        XuatXu xx = (XuatXu) cboXuatXu1.getSelectedItem();
        Pin p = (Pin) cboPin.getSelectedItem();
        return new SanPham(
                0,
                txtMaSP.getText().trim(),
                txtTenSP.getText().trim(),
                xx.getId(),
                p.getId(),
                new BigDecimal(txtDai.getText().trim()),
                new BigDecimal(txtRong.getText().trim()),
                new BigDecimal(txtDay.getText().trim()),
                0
        );
    }

    @Override
    public void fillToTable() {
        DefaultTableModel modelSP = (DefaultTableModel) tblSanPham.getModel();
        modelSP.setRowCount(0);
        lstSanPham = sanPhamDAO.findAll();
        for (SanPham sanPham : lstSanPham) {
            Object[] row = {
                sanPham.getId(),
                sanPham.getMa(),
                xxDao.findOne(sanPham.getIdXX()).getNoiSanXuat(),
                pinDao.findOne(sanPham.getIdPin()).getDungLuong() + " mAh",
                sanPham.getChieuDai() + " x " + sanPham.getChieuRong() + " x " + sanPham.getDoDay() + " mm",
                sanPham.getSoLuong()
            };
            modelSP.addRow(row);
        }
    }

    public void fillToTable2() {

        modelCT = (DefaultTableModel) tblSanPhamCT.getModel();
        modelCT.setRowCount(0);

        for (ChiTietSanPham ctsp : chiTIetSanPhamDAO.findAllByidSP(idSP)) {
            int dungLuong = romDao.findOne(ctsp.getIdRom()).getDungLuong();

            String dungLuongHienThi;
            if (dungLuong >= 1024) {           // >= 1 TB
                dungLuongHienThi = (dungLuong / 1024) + " TB";
            } else {                            // < 1 TB
                dungLuongHienThi = dungLuong + " GB";
            }

            Object[] row = {
                spDao.findOne(ctsp.getIdSP()).getTen(),
                dungLuongHienThi,
                msDao.findOne(ctsp.getIdMau()).getTen(),
                ctsp.getGia(),
                ctsp.getSoLuong(),
                ctsp.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng",};
            modelCT.addRow(row);
        }

    }

    public void fillToMauSacTable() {
        DefaultTableModel modelMS = (DefaultTableModel) tblMauSac.getModel();
        modelMS.setRowCount(0);
        for (MauSac ms : msDao.findAll()) {
            Object[] row = {
                ms.getId(),
                ms.getMaMau(),
                ms.getTen()
            };
            modelMS.addRow(row);
        }
    }

    public void fillToXXTable() {
        DefaultTableModel modelXX = (DefaultTableModel) tblXX.getModel();
        modelXX.setRowCount(0);
        for (XuatXu xx : xxDao.findAll()) {
            Object[] row = {
                xx.getId(),
                xx.getNoiSanXuat()
            };
            modelXX.addRow(row);
        }
    }

    public void fillToPinTable() {
        DefaultTableModel modelPin = (DefaultTableModel) tblPin.getModel();
        modelPin.setRowCount(0);
        for (Pin pin : pinDao.findAll()) {
            Object[] row = {
                pin.getId(),
                pin.getDungLuong() + " mAh"
            };
            modelPin.addRow(row);
        }
    }

    public void fillToRomTable() {
        DefaultTableModel modelRom = (DefaultTableModel) tblRom.getModel();
        modelRom.setRowCount(0);

        for (Rom rom : romDao.findAll()) {
            int dungLuong = romDao.findOne(rom.getId()).getDungLuong();

            String dungLuongHienThi;
            if (dungLuong >= 1024) {           // >= 1 TB
                dungLuongHienThi = (dungLuong / 1024) + " TB";
            } else {                            // < 1 TB
                dungLuongHienThi = dungLuong + " GB";
            }
            Object[] row = {
                rom.getId(),
                dungLuongHienThi
            };
            modelRom.addRow(row);
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
