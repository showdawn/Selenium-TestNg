package com.windstream.dataProviders;

import com.windstream.config.Config;
import com.windstream.model.BrowserModel;
import com.windstream.model.LoginModel;
import com.windstream.model.OrderModel;
import com.windstream.testcases.OrderStatusTest;
import com.windstream.util.ExcelHelper;
import com.windstream.util.ExcelModel;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "orderStatusDataProvider")
    public static Iterator<Object[]> createOrderData() {
        List<Object[]> excelDataReturn = new ArrayList<Object[]>();
        try {
            List<ExcelModel> excelModels = ExcelHelper.readFromExcelFile(Config.getInputExcelPath());
            for (ExcelModel excelData : excelModels) {
                excelDataReturn.add(new Object[]{excelData});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelDataReturn.iterator();
    }
}
