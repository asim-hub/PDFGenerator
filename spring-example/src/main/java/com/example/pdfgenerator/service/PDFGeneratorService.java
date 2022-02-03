package com.example.pdfgenerator.service;

import com.example.pdfgenerator.model.Employe;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class PDFGeneratorService {
    @Autowired
    TableService tableService;
    @Autowired
    EmployeService employeService;


    public void export(HttpServletResponse response) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);

            Paragraph paragraph = new Paragraph("Pdf Generator.", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);

            Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
            fontParagraph.setSize(12);
            Paragraph paragraph2 = new Paragraph("Cuprins:", fontParagraph);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

            Paragraph paragraph3 = new Paragraph("1) Text cu valori din baza de date", fontParagraph);
            paragraph3.setAlignment(Paragraph.ALIGN_LEFT);

            Paragraph paragraph4 = new Paragraph("2) Tabel", fontParagraph);
            paragraph4.setAlignment(Paragraph.ALIGN_LEFT);

            Paragraph paragraph5 = new Paragraph("3) pie / bar / line charts", fontParagraph);
            paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

            document.add(paragraph);
            document.add(Chunk.NEWLINE);
            document.add(paragraph2);
            document.add(Chunk.NEWLINE);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph5);
            document.newPage();
            document.add(new Paragraph("Aceasta este o pagina cu text."));
            document.newPage();
            document.add(tableService.generate((ArrayList<Employe>) employeService.getAllEmployees()));

        }catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
