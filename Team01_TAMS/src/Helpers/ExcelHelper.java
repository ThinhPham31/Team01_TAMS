package Helpers;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap; // FIX: dùng LinkedHashMap để giữ thứ tự

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;

    private Map<String, Integer> columns = new HashMap<>();


    public void setExcelFile(String ExcelPath, String SheetName){
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                System.out.println("File doesn't exist.");
            }

            fis = new FileInputStream(ExcelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);

            if (sh == null) {
                throw new Exception("Sheet name doesn't exist.");
            }

            this.excelFilePath = ExcelPath;

            // FIX: phải clear columns trước khi load sheet mới
            columns.clear();

            // Load header row
            sh.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public String getCellData(int columnIndex, int string) {
        try {
            row = sh.getRow(string);
            if (row == null) return "";

            cell = row.getCell(columnIndex);
            if (cell == null) return "";

            // FIX: dùng DataFormatter thay vì ép kiểu long
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell).trim();

        } catch (Exception e) {
            return "";
        }
    }


    public String getCellData(String columnName, int rowIndex) {
        return getCellData(columns.get(columnName), rowIndex);
    }


    // set by column index
    public void setCellData(String text, int columnIndex, int rowIndex) {
        try {
            row = sh.getRow(rowIndex);
            if (row == null) {
                row = sh.createRow(rowIndex);
            }

            cell = row.getCell(columnIndex);
            if (cell == null) {
                cell = row.createCell(columnIndex);
            }

            cell.setCellValue(text);

            CellStyle style = wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }


    // set by column name
    public void setCellData(String text, String columnName, int rowIndex) {
        try {
            row = sh.getRow(rowIndex);
            if (row == null) row = sh.createRow(rowIndex);

            cell = row.getCell(columns.get(columnName));
            if (cell == null) cell = row.createCell(columns.get(columnName));

            cell.setCellValue(text);

            CellStyle style = wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }


    // FIX: bổ sung – đọc Excel dạng List<Map>
    public List<Map<String, String>> readExcelAsListOfMap(String excelPath, String sheetName) {

        List<Map<String, String>> dataList = new ArrayList<>();

        try {
            setExcelFile(excelPath, sheetName);

            int lastRow = sh.getLastRowNum();
            Row headerRow = sh.getRow(0);

            for (int i = 1; i <= lastRow; i++) {
                Row row = sh.getRow(i);
                if (row == null) continue;

                // FIX: dùng LinkedHashMap để giữ thứ tự cột
                Map<String, String> rowData = new LinkedHashMap<>();

                for (Cell headerCell : headerRow) {
                    String headerName = headerCell.getStringCellValue().trim();
                    int colIndex = headerCell.getColumnIndex();
                    String value = getCellData(colIndex, i);

                    rowData.put(headerName, value);
                }

                dataList.add(rowData);
            }

        } catch (Exception e) {
            System.out.println("Error reading excel: " + e.getMessage());
        }

        return dataList;
    }
}
