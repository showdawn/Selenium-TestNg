package com.windstream.reports.excelReports;

import com.windstream.reports.pojo.ExcelPojo;

import java.util.List;

public abstract class ExcelReport {
    public abstract void createExcel(String sheetName, String fileName, String fileLocation, List<ExcelPojo> excelPojoList);
}
