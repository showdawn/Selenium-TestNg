package com.windstream.reports;

import com.windstream.reports.excelReports.ExcelReport;
import com.windstream.reports.excelReports.ExcelReportImpl;
import com.windstream.reports.pdf.PDFReport;
import com.windstream.reports.pdf.PDFReportImpl;
import com.windstream.reports.pojo.ExcelPojo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Report {

    public void saveScreenShotToFile(WebDriver driver, String filename, String fileSavelocation) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(fileSavelocation + "\\" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPDF(String filename, String fileSavelocation, List<Object> files) {
        PDFReport pdfReport = new PDFReportImpl();
        pdfReport.createPDF(filename, fileSavelocation, files);

    }

    public byte[] getScreenShotInBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void createExcel(String sheetName, String filename, String fileSavelocation, List<ExcelPojo> excel) {
        ExcelReport excelReport = new ExcelReportImpl();
        excelReport.createExcel(sheetName, filename, fileSavelocation, excel);
    }
}
