package com.windstream.util;

import com.windstream.model.BrowserModel;
import com.windstream.model.LoginModel;
import com.windstream.model.OrderModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<ExcelModel> readFromExcelFile(String excelFilePath) throws IOException {

        List<ExcelModel> excelModels = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);

        //Browsers - Get All browsers from sheet
        List<BrowserModel> browsers = populateBrowserList(workbook.getSheetAt(0));

        //Login Sheet - Get All Login Credentials from sheet
        List<LoginModel> logins = populateLoginDetails(workbook.getSheetAt(1));

        //Orders Sheet - Get All List of Orders to validate from sheet
        List<OrderModel> orders = populateOrderDetails(workbook.getSheetAt(2));

        for (BrowserModel browser : browsers) {
            ExcelModel excelModel = new ExcelModel();
            excelModel.setBrowser(browser);
            excelModel.setLoginCredentials(logins);
            excelModel.setOrders(orders);
            excelModels.add(excelModel);
        }
        workbook.close();
        inputStream.close();
        return excelModels;
    }

    private static List<BrowserModel> populateBrowserList(Sheet browserSheet) {
        List<BrowserModel> browsers = new ArrayList<>();
        Iterator<Row> rows = browserSheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            //Browser Model
            BrowserModel browser = new BrowserModel();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 1:
                        browser.setBrowserName((String) getCellVal(cell));
                        break;
                    case 2:
                        browser.setOs((String) getCellVal(cell));
                        break;
                    case 3:
                        browser.setBrowserHeight((String) getCellVal(cell));
                        break;
                    case 4:
                        browser.setBrowserWidth((String) getCellVal(cell));
                        break;
                }
                browsers.add(browser);
            }
        }
        return browsers;
    }

    private static List<LoginModel> populateLoginDetails(Sheet browserSheet) {
        List<LoginModel> loginModels = new ArrayList<>();
        Iterator<Row> rows = browserSheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            //Browser Model
            LoginModel loginModel = new LoginModel();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 1:
                        loginModel.setUsername((String) getCellVal(cell));
                        break;
                    case 2:
                        loginModel.setPassword((String) getCellVal(cell));
                        break;
                }
                loginModels.add(loginModel);
            }
        }
        return loginModels;
    }

    private static List<OrderModel> populateOrderDetails(Sheet browserSheet) {
        List<OrderModel> orders = new ArrayList<>();
        Iterator<Row> rows = browserSheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            //Browser Model
            OrderModel orderModel = new OrderModel();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 1:
                        orderModel.setOrderNumber((String) getCellVal(cell));
                        break;
                    case 2:
                        orderModel.setOrderStatus((String) getCellVal(cell));
                        break;
                    case 3:
                        orderModel.setOrderDate((String) getCellVal(cell));
                        break;
                }
                orders.add(orderModel);
            }
        }
        return orders;
    }

    private static Object getCellVal(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }

}
