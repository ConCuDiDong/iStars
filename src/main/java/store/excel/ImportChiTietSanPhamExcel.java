package store.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import store.util.XJdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class ImportChiTietSanPhamExcel {

    /**
     * Import ChiTietSanPham từ file Excel.
     * Excel cột theo thứ tự:
     *   [0] idRom, [1] idMau, [2] gia, [3] trangThai, [4] soLuong
     *
     * @param file file Excel
     * @param idSP id sản phẩm cha (truyền từ biến toàn cục khi gọi nút)
     */
    public void importExcelToDatabase(File file, int idSP) {
        // ✅ Kiểm tra idSP có tồn tại
        Integer spCount = XJdbc.getValue("SELECT COUNT(*) FROM SanPham WHERE id = ?", idSP);
        if (spCount == null || spCount == 0) {
            throw new RuntimeException("Sản phẩm (idSP=" + idSP + ") không tồn tại trong bảng SanPham.");
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;

            for (Row row : sheet) {
                // Bỏ qua header
                if (rowCount++ == 0) continue;
                if (row == null) continue;

                // === Lấy dữ liệu từ Excel ===
                Integer idRom     = getCellInt(row.getCell(0), null);
                Integer idMau     = getCellInt(row.getCell(1), null);
                BigDecimal gia    = getCellBigDecimal(row.getCell(2), BigDecimal.ZERO);
                Integer trangThai = getCellInt(row.getCell(3), 1);    // mặc định 1
                Integer soLuong   = getCellInt(row.getCell(4), 0);    // mặc định 0

                // === Validate bắt buộc: idRom, idMau ===
                if (idRom == null || idMau == null) {
                    System.out.println("⚠️ Bỏ qua dòng " + row.getRowNum() + " vì thiếu idRom hoặc idMau");
                    continue;
                }

                // ✅ Check tồn tại idRom
                Integer romExists = XJdbc.getValue("SELECT COUNT(*) FROM Rom WHERE id = ?", idRom);
                if (romExists == null || romExists == 0) {
                    System.out.println("⚠️ Bỏ qua dòng " + row.getRowNum() + " vì Rom(id=" + idRom + ") không tồn tại.");
                    continue;
                }

                // ✅ Check tồn tại idMau
                Integer mauExists = XJdbc.getValue("SELECT COUNT(*) FROM MauSac WHERE id = ?", idMau);
                if (mauExists == null || mauExists == 0) {
                    System.out.println("⚠️ Bỏ qua dòng " + row.getRowNum() + " vì MauSac(id=" + idMau + ") không tồn tại.");
                    continue;
                }

                // ✅ Check trùng logic: (idSP, idRom, idMau)
                String sqlCheckDup = "SELECT COUNT(*) FROM ChiTietSanPham WHERE idSP=? AND idRom=? AND idMau=?";
                Integer exists = XJdbc.getValue(sqlCheckDup, idSP, idRom, idMau);
                if (exists != null && exists > 0) {
                    System.out.println("⚠️ Bỏ qua dòng " + row.getRowNum()
                            + " vì CTSP đã tồn tại (idSP=" + idSP + ", idRom=" + idRom + ", idMau=" + idMau + ")");
                    continue;
                }

                // ✅ Lấy tên SP, tên Màu, dung lượng
                String tenSP  = XJdbc.getValue("SELECT ten FROM SanPham WHERE id=?", idSP);
                String tenMau = XJdbc.getValue("SELECT ten FROM MauSac WHERE id=?", idMau);
                int dungLuong = XJdbc.getValue("SELECT dungLuong FROM Rom WHERE id=?", idRom);
                String ghiChu = tenSP + " - " + tenMau + " - " + formatDungLuong(dungLuong);

                // ✅ Insert
                String sqlInsert = """
                        INSERT INTO ChiTietSanPham (idSP, idRom, idMau, gia, ghiChu, trangThai, soLuong)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """;
                int result = XJdbc.executeUpdate(sqlInsert, idSP, idRom, idMau, gia, ghiChu, 0, 0);

                if (result > 0) {
                    System.out.println("✅ Đã import CTSP: " + ghiChu + " | gia=" + gia + " | soLuong=" + soLuong);
                }
            }

            System.out.println("🎉 Import ChiTietSanPham hoàn tất!");

        } catch (IOException e) {
            throw new RuntimeException("❌ Lỗi đọc file Excel", e);
        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi khi import ChiTietSanPham: " + e.getMessage(), e);
        }
    }

    // ================== HÀM HỖ TRỢ ==================

    private String getCellString(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        String val = cell.getStringCellValue();
        return val != null ? val.trim() : null;
    }

    private Integer getCellInt(Cell cell, Integer defaultValue) {
        try {
            if (cell == null) return defaultValue;
            return switch (cell.getCellType()) {
                case NUMERIC -> (int) cell.getNumericCellValue();
                case STRING -> {
                    String s = cell.getStringCellValue();
                    if (s == null) yield defaultValue;
                    s = s.trim();
                    yield s.isEmpty() ? defaultValue : Integer.parseInt(s);
                }
                case BOOLEAN -> cell.getBooleanCellValue() ? 1 : 0;
                default -> defaultValue;
            };
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private BigDecimal getCellBigDecimal(Cell cell, BigDecimal defaultValue) {
        try {
            if (cell == null) return defaultValue;
            return switch (cell.getCellType()) {
                case NUMERIC -> BigDecimal.valueOf(cell.getNumericCellValue());
                case STRING -> {
                    String s = cell.getStringCellValue();
                    if (s == null) yield defaultValue;
                    s = s.trim();
                    yield s.isEmpty() ? defaultValue : new BigDecimal(s);
                }
                default -> defaultValue;
            };
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // ================== FORMAT DUNG LƯỢNG ==================
    private String formatDungLuong(int dungLuong) {
    if (dungLuong >= 1024) {           // >= 1 TB
        return (dungLuong / 1024) + " TB";
    } else {                            // < 1 TB
        return dungLuong + " GB";
    }
}
}