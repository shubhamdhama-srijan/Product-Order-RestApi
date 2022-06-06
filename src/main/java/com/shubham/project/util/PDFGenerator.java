package com.shubham.project.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shubham.project.entity.Order;
import com.shubham.project.repository.OrderRepository;

@Component("pdfGenerator")
public class PDFGenerator {
	
	@Value("${pdfDir}")
	private String pdfDir;
	
	@Value("${reportFileName}")
	private String reportFileName;
	
	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;
	
	@Value("${localDateFormat}")
	private String localDateFormat;
	
//	@Value("${logoImgPath}")
//	private String logoImgPath;
//	
//	@Value("${logoImgScale}")
//	private Float[] logoImgScale;
//	
//	@Value("${currencySymbol:}")
//	private String currencySymbol;
	
	@Value("${table_noOfColumns}")
	private int noOfColumns;
	
	@Value("${table.columnNames}")
	private List<String> columnNames;
	
	@Autowired
	OrderRepository eRepo;

	private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

	public void generatePdfReport(HttpServletResponse response) throws IOException {

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
            DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	  		
	  		String currentDateTime=dateFormatter.format(new Date());
	  		String headerKey="Content-Disposition";
	  		String headerValue="attachment; filename=orders_"+ currentDateTime+".pdf";
	  		
	  		response.setHeader(headerKey, headerValue);
//			addLogo(document);
			addDocTitle(document);
			createTable(document,noOfColumns);
			addFooter(document);
			document.close();
			System.out.println("------------------Your PDF Report is ready!-------------------------");

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	private void addDocTitle(Document document) throws DocumentException {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph(reportFileName, COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));

		document.add(p1);

	}

	private void createTable(Document document, int noOfColumns) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		
		for(int i=0; i<noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}

		table.setHeaderRows(1);
		getDbData(table);
		document.add(table);
	}
	
	private void getDbData(PdfPTable table) {
		
		List<Order> list = eRepo.findAll();
		for (Order order : list) {
			
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			table.addCell(String.valueOf(order.getOrdId()));
			table.addCell(order.getProduct().getProductName());
			table.addCell(order.getOrderTitle());
			table.addCell(order.getOrderName());
			table.addCell(String.valueOf(order.getOrderdDate()));
			
//			System.out.println(employee.getEmpName());
		}
		
	}
	
	private void addFooter(Document document) throws DocumentException {
		Paragraph p2 = new Paragraph();
		leaveEmptyLine(p2, 3);
		p2.setAlignment(Element.ALIGN_MIDDLE);
		p2.add(new Paragraph(
				"------------------------End Of " +reportFileName+"------------------------", 
				COURIER_SMALL_FOOTER));
		
		document.add(p2);
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	private String getPdfNameWithDate() {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
		return pdfDir+reportFileName+"-"+localDateString+".pdf";
	}
}





//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.shubham.project.entity.Order;
//import com.shubham.project.repository.OrderRepository;
//@Component("pDFGenerator")
//public class PDFGenerator {
//	
//	
//	@Value("${pdfDir}")
//	private String pdfDir;
//	
//	@Value("${reportFileName}")
//	private String reportFileName;
//	
//	@Value("${reportFileNameDateFormat}")
//	private String reportFileNameDateFormat;
//	
//	@Value("${localDateFormat}")
//	private String localDateFormat;
//	
//	@Value("${table_noOfColumns}")
//	private int noOfColumns;
//	
//	@Value("${table.columnNames}")
//	private List<String> columnNames;
//	
//	@Autowired
//	OrderRepository oRepo;
//	
//	
//	private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
//	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
//	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
//	
//	
//	public void generatePdfReport(HttpServletResponse response) throws IOException {
//		
//		Document document = new Document();
//		try {
//			PdfWriter.getInstance(document,response.getOutputStream());
//			document.open();
//			DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	  		
//	  		String currentDateTime=dateFormatter.format(new Date());
//	  		String headerKey="Content-Disposition";
//	  		String headerValue="attachment; filename=orders_"+ currentDateTime+".pdf";
//	  		
//	  		response.setHeader(headerKey, headerValue);
//			addDocTitle(document);
//			createTable(document,noOfColumns);
//			addFooter(document);
//			document.close();
//			System.out.println("------------------Your Order details are ready!-------------------------");
//		} catch (FileNotFoundException | DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	private void addDocTitle(Document document) throws DocumentException {
//		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
//		Paragraph p1 = new Paragraph();
//		leaveEmptyLine(p1, 1);
//		p1.add(new Paragraph(reportFileName, COURIER));
//		p1.setAlignment(Element.ALIGN_CENTER);
//		leaveEmptyLine(p1, 1);
//		p1.add(new Paragraph("PDF generated on " + localDateString, COURIER_SMALL));
//		document.add(p1);
//	}
//	private void createTable(Document document, int noOfColumns) throws DocumentException {
//		Paragraph paragraph = new Paragraph();
//		leaveEmptyLine(paragraph, 3);
//		document.add(paragraph);
//		PdfPTable table = new PdfPTable(noOfColumns);
//		
//		for(int i=0; i<noOfColumns; i++) {
//			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cell.setBackgroundColor(BaseColor.CYAN);
//			table.addCell(cell);
//		}
//		table.setHeaderRows(1);
//		getDbData(table);
//		document.add(table);
//	}
//	
//	private void getDbData(PdfPTable table) {
//		
//		List<Order> orders = oRepo.findAll();
//		for (Order order : orders) {
//			
//			table.setWidthPercentage(100);
//			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//			
//			table.addCell(String.valueOf(order.getOrdId()));
//			table.addCell(order.getProduct().getProductName());
//			table.addCell(order.getOrderTitle());
//			table.addCell(order.getOrderName());
//			table.addCell(String.valueOf(order.getOrderdDate()));
//			
//		}
//		
//	}
//	
//	private void addFooter(Document document) throws DocumentException {
//		Paragraph p2 = new Paragraph();
//		leaveEmptyLine(p2, 3);
//		p2.setAlignment(Element.ALIGN_MIDDLE);
//		p2.add(new Paragraph(
//				"------------------------End Of " +reportFileName+"------------------------",
//				COURIER_SMALL_FOOTER));
//		
//		document.add(p2);
//	}
//	private static void leaveEmptyLine(Paragraph paragraph, int number) {
//		for (int i = 0; i < number; i++) {
//			paragraph.add(new Paragraph(" "));
//		}
//	}
//	
//	
////	private String getPdfNameWithDate() {
////		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
////		return pdfDir+reportFileName+"-"+localDateString+".pdf";
////		
////	}
//}
