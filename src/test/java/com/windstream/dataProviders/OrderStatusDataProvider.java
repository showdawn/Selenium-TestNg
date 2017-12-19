package com.windstream.dataProviders;

import com.windstream.config.Config;
import com.windstream.model.Login;
import com.windstream.model.Orders;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderStatusDataProvider {

    @DataProvider(name = "orderStatusDataProvider")
    public static Object[][] createOrderData() {
        try {
            //Read Data from Excel
            FileInputStream inputStream = new FileInputStream(new File(Config.getInputExcelPath()));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Login loginCredentials = populateLoginDetails(workbook.getSheetAt(0));
            List<Orders> orders = populateOrderDetails(workbook.getSheetAt(1));
            workbook.close();
            inputStream.close();

            int rows = orders.size();
            int cols = 4;
            Object[][] orderTestData = new Object[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j == 0) {
                        orderTestData[i][j] = loginCredentials.getUsername();
                    } else if (j == 1) {
                        orderTestData[i][j] = loginCredentials.getPassword();
                    } else if (j == 2) {
                        orderTestData[i][j] = orders.get(i).getOrderNumber();
                    } else {
                        orderTestData[i][j] = orders.get(i).getOrderStatus();
                    }
                }
            }
            return orderTestData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Login populateLoginDetails(Sheet browserSheet) {
        List<Login> logins = new ArrayList<>();
        Iterator<Row> rows = browserSheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            //Browser Model
            Login login = new Login();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        login.setUsername((String) getCellVal(cell));
                        break;
                    case 1:
                        login.setPassword((String) getCellVal(cell));
                        break;
                }
            }
            logins.add(login);
        }
        return logins.get(0);
    }

    private static List<Orders> populateOrderDetails(Sheet browserSheet) {
        List<Orders> orders = new ArrayList<>();
        Iterator<Row> rows = browserSheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            //Browser Model
            Orders orderModel = new Orders();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        orderModel.setOrderNumber(String.valueOf(getCellVal(cell)));
                        break;
                    case 1:
                        orderModel.setOrderStatus(String.valueOf(getCellVal(cell)));
                        break;
                }
            }
            orders.add(orderModel);
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
