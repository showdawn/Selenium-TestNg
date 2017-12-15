package com.windstream.reports.pdf;


import java.util.List;


public interface PDFReport {

    public void createPDF(String filename, String filePath, List<Object> images);

}
