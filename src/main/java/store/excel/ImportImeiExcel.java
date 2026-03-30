package store.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import store.util.XJdbc;

import java.io.File;
import java.io.FileInputStream;

/**
 * Import IMEI từ Excel và gắn vào 1 ChiTietSanPham cụ thể
 */
public class ImportImeiExcel {

    /**
     * Import IMEI từ Excel
     *
     * @param excelFile file Excel
     * @param idCTSP    id ChiTietSanPham để gắn tất cả IMEI
     * @return số lượng IMEI đã thêm thành công
     */
    public static int importExcel(File excelFile, int idCTSP) {
        int addedCount = 0; // đếm số IMEI thêm thành công
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // ✅ Check idCTSP có tồn tại
            String sqlCheckCTSP = "SELECT COUNT(*) FROM ChiTietSanPham WHERE id = ?";
            int ctspExists = ((Number) XJdbc.getValue(sqlCheckCTSP, idCTSP)).intValue();
            if (ctspExists == 0) {
                throw new RuntimeException("ChiTietSanPham ID không tồn tại: " + idCTSP);
            }

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;

            for (Row row : sheet) {
                if (rowCount++ == 0) continue; // bỏ header
                if (row == null) continue;

                // Đọc cột maImei an toàn, xử lý cả numeric lẫn string
                String maImei = getCellAsString(row.getCell(0)).trim();
                if (maImei.isEmpty()) continue;

                int trangThai = 1; // mặc định trạng thái mới là 1

                // ✅ Check maImei đã tồn tại
                String sqlCheckImei = "SELECT COUNT(*) FROM Imei WHERE maImei = ?";
                int imeiExists = ((Number) XJdbc.getValue(sqlCheckImei, maImei)).intValue();
                if (imeiExists > 0) {
                    System.out.println("⚠️ IMEI đã tồn tại: " + maImei);
                    continue; // bỏ qua dòng này nhưng không lỗi
                }

                // ✅ Insert vào DB
                String sqlInsert = "INSERT INTO Imei (maImei, trangThai, idCTSP) VALUES (?, ?, ?)";
                XJdbc.executeUpdate(sqlInsert, maImei, trangThai, idCTSP);
                addedCount++; // tăng số lượng IMEI thành công
            }

            System.out.println("✅ Import IMEI hoàn tất cho ChiTietSanPham ID: " + idCTSP
                    + " | Số lượng thêm: " + addedCount);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi import IMEI: " + e.getMessage());
        }

        return addedCount; // trả về số dòng IMEI thêm thành công
    }

    /**
     * Chuyển cell Excel sang String an toàn, xử lý cả NUMERIC và STRING
     */
    private static String getCellAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING -> {
                return cell.getStringCellValue();
            }
            case NUMERIC -> {
                // Chuyển numeric thành chuỗi nguyên, tránh scientific notation
                double num = cell.getNumericCellValue();
                long longVal = (long) num;
                return String.valueOf(longVal);
            }
            case FORMULA -> {
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook()
                        .getCreationHelper().createFormulaEvaluator();
                CellValue value = evaluator.evaluate(cell);
                if (value.getCellType() == CellType.NUMERIC) {
                    long longVal = (long) value.getNumberValue();
                    return String.valueOf(longVal);
                } else if (value.getCellType() == CellType.STRING) {
                    return value.getStringValue();
                }
            }
            default -> {
                return "";
            }
        }
        return "";
    }
}