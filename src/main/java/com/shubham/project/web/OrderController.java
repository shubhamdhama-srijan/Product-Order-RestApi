package com.shubham.project.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
//import com.lowagie.text.DocumentException;
import com.shubham.project.entity.Order;
import com.shubham.project.pdfExport.OrdersPDFExporter;
import com.shubham.project.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
//	@Autowired
//	OrderRepository orderRepository;
	
	@Autowired
	OrderService orderService;
	
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }
    
    @GetMapping("/export")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Order> listOrders = orderService.getAllOrders();
         
        OrdersPDFExporter exporter = new OrdersPDFExporter(listOrders);
        exporter.export(response);
         
    }
    
}
