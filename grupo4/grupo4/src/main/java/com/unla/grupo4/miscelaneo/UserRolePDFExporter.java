package com.unla.grupo4.miscelaneo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.grupo4.entities.UserRole;

public class UserRolePDFExporter {
	
    private List<UserRole> listUserRoles;
     
    public UserRolePDFExporter(List<UserRole> listUserRoles) {
        this.listUserRoles = listUserRoles;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.DARK_GRAY);
         
        cell.setPhrase(new Phrase("Perfil ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Activo", font));
        table.addCell(cell);  
    }
     
    private void writeTableData(PdfPTable table) {
        for (UserRole ur : this.listUserRoles) {
			table.addCell(String.valueOf(ur.getId()));
            table.addCell(ur.getRole());
            if(ur.isEnabled())
            	table.addCell("SI");
            else
            	table.addCell("NO");
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.LIGHT_GRAY);
         
        Paragraph p = new Paragraph("Lista de perfiles", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1f, 2.5f, 1f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
