package com.shubham.project.pdfExport;

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
import com.shubham.project.entity.Order;

public class OrdersPDFExporter {
	private List<Order> allOrders;

	public OrdersPDFExporter(List<Order> allOrders) {
		super();
		this.allOrders = allOrders;
	}

	private void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.white);

		cell.setPhrase(new Phrase("Order Id", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Product", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Title", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Date", font));
		table.addCell(cell);
	}

//	print the fields required
	private void writeTableData(PdfPTable table) {
		for (Order order : allOrders) {
			table.addCell(String.valueOf(order.getOrdId()));
			table.addCell(String.valueOf(order.getProduct().getId()));
			table.addCell(order.getOrderTitle());
			table.addCell(order.getOrderName());
			table.addCell(String.valueOf(order.getOrderdDate()));
		}

	}

	public void export(HttpServletResponse response) throws IOException {
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);
		Paragraph title = new Paragraph("List of all the orders : ", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();
	}
}

