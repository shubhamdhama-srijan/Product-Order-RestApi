package com.shubham.project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.shubham.project.entity.Order;
import com.shubham.project.entity.Product;
import com.shubham.project.repository.OrderRepository;
import com.shubham.project.service.OrderService;
import com.shubham.project.util.OrdersPDFExporter;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//Route for getting all the orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    
    }
    
    //Route for creating a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }
    
    //Route for updating a order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id,@RequestBody Order order) {
    	order.setOrdId(id);
		return orderService.updateProduct(order);
    }
    
    //To delete a order by order id
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
    	orderService.deleteOrder(id);
    }
    
  //To get order by orderId
  	@GetMapping("/{id}")
  	public Optional<Order> getOrderByOrdId(@PathVariable int id) {
  		return orderService.getOrderByOrdId(id);
  	}
  	
  	
  //Add route to export a pdf for all the order details
  	@GetMapping("/export")
  	public void exportOrderDetailsToPDF(HttpServletResponse response) throws DocumentException, IOException {
  		
  		response.setContentType("application/pdf");
  		
  		DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  		
  		String currentDateTime=dateFormatter.format(new Date());
  		String headerKey="Content-Disposition";
  		String headerValue="attachment; filename=orders_"+ currentDateTime+".pdf";
  		
  		response.setHeader(headerKey, headerValue);
  		
  		List<Order> allOrders=orderService.getAllOrders();
  		
  		OrdersPDFExporter exporter=new OrdersPDFExporter(allOrders);
  		exporter.export(response);
  	}
    
}
