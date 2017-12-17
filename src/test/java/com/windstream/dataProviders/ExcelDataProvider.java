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

            ExcelModel excelModels = ExcelHelper.readFromExcelFile(Config.getInputExcelPath());

            List<BrowserModel> browsers = excelModels.getBrowsersList();
            List<LoginModel> logins = excelModels.getLoginCredentials();
            List<OrderModel> orders = excelModels.getOrders();

            List<OrderStatusTestData> result = new ArrayList<>();

            for(BrowserModel browser : browsers) {
                result.add()
            }

            for(LoginModel login : logins) {

            }

            for(OrderModel order : orders) {

            }


            int totalCount = excelModels.getLoginCredentials().size() + excelModels.getBrowsersList().size() + excelModels.getOrders().size();

            for()


            for(int i=0; i<totalCount; i++){

                OrderStatusTestData orderStatusTestData = new OrderStatusTestData();

                orderStatusTestData.setBrowser(excelModels.getBrowsersList().get(i));
                orderStatusTestData.setLoginCredential(excelModels.getLoginCredentials().get(i));


                excelDataReturn.add(new Object[]{orderStatusTestData});

            }

            //Add data for each browser
            for (BrowserModel browser : excelModels.getBrowsersList()) {



                excelDataReturn.add(new Object[]{excelModels});

                ExcelModel excelModel = new ExcelModel();
                excelModel.setBrowser(browser);
                excelModel.setLoginCredentials(logins);
                excelModel.setOrders(orders);
                excelModels.add(excelModel);
            }

            for (ExcelModel excelData : excelModels) {
                excelDataReturn.add(new Object[]{excelData});
            }

            //Add data for each Order Number



        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelDataReturn.iterator();
    }


}
