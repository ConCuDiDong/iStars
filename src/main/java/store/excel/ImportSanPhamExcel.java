package store.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import store.util.XJdbc;

public class ImportSanPhamExcel {

    public void importExcelToDatabase(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;

            for (Row row : sheet) {
                // Bỏ qua header
                if (rowCount++ == 0) continue;

                String ma = getCellString(row.getCell(0));
                String ten = getCellString(row.getCell(1));
                Integer idXX = getCellInt(row.getCell(2), 0);
                Integer idPin = getCellInt(row.getCell(3), 0);
                BigDecimal chieuDai = getCellBigDecimal(row.getCell(4), BigDecimal.ZERO);
                BigDecimal chieuRong = getCellBigDecimal(row.getCell(5), BigDecimal.ZERO);
                BigDecimal doDay = getCellBigDecimal(row.getCell(6), BigDecimal.ZERO);
                Integer soLuong = getCellInt(row.getCell(7), 0);

                // Validate dữ liệu bắt buộc
                if (ma == null || ma.trim().isEmpty() || ten == null || ten.trim().isEmpty()) {
                    System.out.println("⚠️ Bỏ qua dòng " + row.getRowNum() + " vì thiếu mã hoặc tên sản phẩm");
                    continue;
                }

                // ================== CHECK TRÙNG MÃ HOẶC TÊN ==================
                String checkSql = "SELECT COUNT(*) FROM SanPham WHERE ma = ? OR ten = ?";
                Integer count = XJdbc.getValue(checkSql, ma, ten);

                if (count != null && count > 0) {
                    System.out.println("⚠️ Bỏ qua vì mã hoặc tên đã tồn tại: " + ma + " - " + ten);
                    continue;
                }

                // ================== INSERT DỮ LIỆU ==================
                String insertSql = """
                        INSERT INTO SanPham(ma, ten, idXX, idPin, chieuDai, chieuRong, doDay, soLuong)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        """;
                int result = XJdbc.executeUpdate(insertSql,
                        ma, ten, idXX, idPin, chieuDai, chieuRong, doDay, soLuong);

                if (result > 0) {
                    System.out.println("✅ Đã import sản phẩm: " + ma + " - " + ten);
                }
            }

            System.out.println("🎉 Import hoàn tất!");

        } catch (IOException e) {
            throw new RuntimeException("❌ Lỗi đọc file Excel", e);
        }
    }

    // ================== HÀM HỖ TRỢ ==================

    private String getCellString(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private Integer getCellInt(Cell cell, int defaultValue) {
        try {
            if (cell == null) return defaultValue;
            if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                String val = cell.getStringCellValue().trim();
                return val.isEmpty() ? defaultValue : Integer.parseInt(val);
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    private BigDecimal getCellBigDecimal(Cell cell, BigDecimal defaultValue) {
        try {
            if (cell == null) return defaultValue;
            if (cell.getCellType() == CellType.NUMERIC) {
                return BigDecimal.valueOf(cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                String val = cell.getStringCellValue().trim();
                return val.isEmpty() ? defaultValue : new BigDecimal(val);
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }
}