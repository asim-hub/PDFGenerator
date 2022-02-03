package com.example.pdfgenerator.service;

import com.example.pdfgenerator.model.Employe;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import java.awt.*;
import java.util.*;


@Service
public class TableService {
    private static void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("FIRST NAME", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("LAST NAME", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("EMAIL", font));
        table.addCell(cell);
    }

    private static void writeTableData(PdfPTable table, ArrayList<Employe> employees){
        for(int i=0; i<employees.size(); i++) {
            table.addCell(String.valueOf(employees.get(i).getId()));
            table.addCell(employees.get(i).getFirst_name());
            table.addCell(employees.get(i).getLast_name());
            table.addCell(String.valueOf(employees.get(i).getEmail()));
        }
    }

    public static PdfPTable generate(ArrayList<Employe> employees) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, employees);
        return table;
    }


}
