package com.shubham.project.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shubham.project.entity.Order;
import com.shubham.project.service.OrderService;

@ComponentScan
public class OrdersPDFExporter {
//	private List<Order> allOrders;
	@Autowired
	OrderService orderService;
	
	@Value("${reportFileName}")
	private String reportFileName;
	
	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;
	
	@Value("${localDateFormat}")
	private String localDateFormat;
	
	@Value("${table_noOfColumns}")
	private int noOfColumns;
	
	@Value("${table.columnNames}")
	private List<String> columnNames;

//	public OrdersPDFExporter(List<Order> allOrders) {
//		super();
////		this.allOrders = allOrders;
//	}

	private void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.white);

		cell.setPhrase(new Phrase("Order Id", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Product Name", font));
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
		List<Order> list = orderService.getAllOrders();
		for (Order order : list) {
			table.addCell(String.valueOf(order.getOrdId()));
			table.addCell(String.valueOf(order.getProduct().getProductName()));
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

//		PdfPTable table = new PdfPTable(5);
//		table.setWidthPercentage(100);
//		table.setSpacingBefore(15);
//		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });

		createTable(document, noOfColumns);
//		writeTableHeader(table);
//		writeTableData(table);

//		document.add(table);

		document.close();
	}

	private void createTable(Document document, int noOfColumns) {
		// TODO Auto-generated method stub
//		Paragraph paragraph =  new Paragraph();
		
		PdfPTable table = new PdfPTable(5);
		for(int i=0;i<5;i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}
		
		table.setHeaderRows(1);
		writeTableData(table);
		document.add(table);
		
	}

//	private void getDbData(PdfPTable table) {
//		// TODO Auto-generated method stub
//		
//	}
}

