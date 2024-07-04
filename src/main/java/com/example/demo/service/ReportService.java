package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ProductRepository productRepository;

    public ByteArrayInputStream exportToExcel() throws IOException {
        List<Product> products = productRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Products");

            // Header
            String[] headers = {"ID", "Name Kazakh", "Name Russian", "Quantity", "Price", "Total"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data
            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getNameKazakh());
                row.createCell(2).setCellValue(product.getNameRussian());
                row.createCell(3).setCellValue(product.getQuantity());
                row.createCell(4).setCellValue(product.getPrice());
                row.createCell(5).setCellValue(product.getTotal());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportToPdf() throws IOException {
        List<Product> products = productRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.add(new Paragraph("Products Report").setFont(font).setFontSize(20));

        float[] columnWidths = {1, 3, 3, 1, 1, 1};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));

        table.addHeaderCell("ID");
        table.addHeaderCell("Name Kazakh");
        table.addHeaderCell("Name Russian");
        table.addHeaderCell("Quantity");
        table.addHeaderCell("Price");
        table.addHeaderCell("Total");

        for (Product product : products) {
            table.addCell(product.getId().toString());
            table.addCell(product.getNameKazakh());
            table.addCell(product.getNameRussian());
            table.addCell(String.valueOf(product.getQuantity()));
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getTotal()));
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
