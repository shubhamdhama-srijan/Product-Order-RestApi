package com.shubham.project;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.shubham.project.util.PDFGenerator;

//import com.shubham.project.util.PDFGenerator;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shubham.project"})
public class ProductRestApiApplication {

	public static void main(String[] args) throws FileNotFoundException {
	  ApplicationContext ac	=SpringApplication.run(ProductRestApiApplication.class, args);
	  
//	  PDFGenerator pDFGenerator = ac.getBean("pdfGenerator",PDFGenerator.class);
//		
//		pDFGenerator.generatePdfReport();
		 
       
		
	}

}
