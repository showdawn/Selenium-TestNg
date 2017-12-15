package com.windstream.reports.excelReports;

import com.windstream.reports.pojo.ExcelPojo;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExcelReportImpl extends ExcelReport {
    private static Map<String, CellStyle> createStyles(Workbook wb) {

        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short) 11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);


        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFillBackgroundColor(IndexedColors.DARK_GREEN.getIndex());
        style.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        styles.put("pass", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFillBackgroundColor(IndexedColors.RED.getIndex());
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        styles.put("fail", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }

    @Override
    public void createExcel(String sheetName, String fileName, String fileLocation, List<ExcelPojo> excelPojoList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Map<String, CellStyle> styles = createStyles(workbook);

        XSSFSheet sheet = workbook.createSheet(sheetName);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //Title
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(50);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Test Report of Epay on " + sheetName + " browser");
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

        //Header
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        List<String> titles = Arrays.asList("Iteration", "Login Page(sec)", "WOL Dashboard(sec)", "Make Payment(sec)", "Confirmation Page(sec)", "Test Result");
        for (int i = 0; i < titles.size(); i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(titles.get(i));
            headerCell.setCellStyle(styles.get("header"));
        }

        //Add Data Now
        int rownum = 2;
        for (ExcelPojo excelPojo : excelPojoList) {
            Row row = sheet.createRow(rownum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(excelPojo.getIterationNumber());
            cell = row.createCell(1);
            cell.setCellValue(excelPojo.getLoginPageLoadTime());
            cell = row.createCell(2);
            cell.setCellValue(excelPojo.getWolDasboardLoadTime());
            cell = row.createCell(3);
            cell.setCellValue(excelPojo.getMakePaymentLoadTime());
            cell = row.createCell(4);
            cell.setCellValue(excelPojo.getConformationPageLaodTime());
            cell = row.createCell(5);
            cell.setCellValue(excelPojo.getTestStatus());
            if ("PASS".equals(excelPojo.getTestStatus())) {
                cell.setCellStyle(styles.get("pass"));
            } else {
                cell.setCellStyle(styles.get("fail"));
            }
        }

        //Row with averages
        Row avgRow = sheet.createRow(rownum++);
        avgRow.setHeightInPoints(35);
        Cell cell;
        cell = avgRow.createCell(0);
        cell.setCellValue("Averages");
        cell.setCellStyle(styles.get("header"));

        //finally set column widths, the width is measured in units of 1/256th of a character width
        sheet.setDefaultColumnWidth(500 * 256);
        try {
            // Write the output to a file
            FileOutputStream out = new FileOutputStream(fileLocation + "\\" + fileName + ".xlsx");
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
