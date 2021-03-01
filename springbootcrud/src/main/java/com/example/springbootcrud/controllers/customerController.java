package com.example.springbootcrud.controllers;
import com.example.springbootcrud.services.customerService;
import com.example.springbootcrud.models.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class customerController {

	@Autowired
	customerService customerService;
	
	@GetMapping("/customerslist")
	public ResponseEntity<List<Customer>> getAllIpo() {
		
		List<Customer> customerslist = customerService.getallcustomers();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    return new ResponseEntity<>(customerslist, headers, HttpStatus.OK);
	}
	@GetMapping("/nativequery")
	public Optional<List<Customer>> getTheUsersByQuery(){
		Optional<List<Customer>> customersquerylist = customerService.getTheUsersByQuery();
		return customersquerylist;
	}
	@PostMapping("/createcustomer")
	public ResponseEntity<Object> createcustomer(@RequestBody Customer customerObj) {
		try {
			Customer customer = customerService.createcustomerservice(customerObj);
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Custom-Header", "foo");
			return new ResponseEntity<Object>(customer, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e) {
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Custom-Header", "foo");
			return new ResponseEntity<Object>("Username Exists", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@PutMapping("/updatecustomer/{id}")
	public Customer updatecustomer(@PathVariable("id") int Id, @RequestBody Customer cust) {
		Customer customer = customerService.updateCustomerById(Id);
		customer.setName(cust.getName());
		customer.setEmail(cust.getEmail());
		return customerService.updateCustomerByObj(customer);
	}
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<Boolean> deletecustomer(@PathVariable("id") int Id) {
		if (customerService.deleteCustomerById(Id)) {
			return ResponseEntity.ok(true);
		}
		else {
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Custom-Header", "foo");
		    return new ResponseEntity<>(false, headers, HttpStatus.BAD_REQUEST);
		}
	}
}
