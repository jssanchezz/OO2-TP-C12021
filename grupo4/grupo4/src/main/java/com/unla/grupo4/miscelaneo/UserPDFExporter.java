package com.unla.grupo4.miscelaneo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.grupo4.entities.User;

public class UserPDFExporter {

		private List<User> listUsers;

		public UserPDFExporter(List<User> listUsers) {
	        this.listUsers = listUsers;
	    }

		private void writeTableHeader(PdfPTable table) {
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(Color.ORANGE);
			cell.setPadding(6);

			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(Color.DARK_GRAY);

			cell.setPhrase(new Phrase("Name", font));

			table.addCell(cell);

			cell.setPhrase(new Phrase("Surname", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("tipo", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Dni", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Username", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Role", font));
			table.addCell(cell);
			
		}

		private void writeTableData(PdfPTable table) {
			for (User ur : this.listUsers) {
				table.addCell(ur.getName());
				table.addCell(ur.getSurname());
				table.addCell(String.valueOf(ur.getTypeDoc()));
				table.addCell(ur.getDni());
				table.addCell(ur.getUserName());
				table.addCell(ur.getRole().getRole());
			}
		}

		public void export(HttpServletResponse response) throws DocumentException, IOException {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setSize(18);
			font.setColor(Color.LIGHT_GRAY);

			Paragraph p = new Paragraph("Lista de usuarios", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.5f, 1.5f, 0.5f, 1.5f, 2.0f, 1f});
			table.setSpacingBefore(15);

			writeTableHeader(table);
			writeTableData(table);

			document.add(table);

			document.close();

		}
	}