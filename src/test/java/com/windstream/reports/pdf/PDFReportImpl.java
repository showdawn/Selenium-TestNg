package com.windstream.reports.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * Uses iText lib to create PDF
 */
public class PDFReportImpl implements PDFReport {

    @Override
    public void createPDF(String filename, String filePath, List<Object> images) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath + "\\" + filename + ".pdf"));
            document.open();
            Image image;
            float scale;
            for (Object obj : images) {
                image = Image.getInstance((byte[]) obj);
                scale = ((document.getPageSize().getWidth() - document.leftMargin()
                        - document.rightMargin()) / image.getWidth()) * 100;
                image.scalePercent(scale);
                document.add(image);
                document.newPage();
            }
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
