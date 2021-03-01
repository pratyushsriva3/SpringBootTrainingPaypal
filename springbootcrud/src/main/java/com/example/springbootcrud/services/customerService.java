package com.example.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootcrud.models.Customer;
import com.example.springbootcrud.repositories.customerrepo;
@Service
public class customerService {

	@Autowired
	customerrepo customerrepository;

	public List<Customer> getallcustomers() {
		List<Customer> customers = new ArrayList<>();
		customerrepository.findAll().forEach(customers::add);
	    return customers;
	}

	public Customer createcustomerservice(Customer customerObj) {
		
		Customer customer = customerrepository.save(customerObj);
      	return customer;
	}
	
    public Customer updateCustomerById(int id) {
		
		Customer customer = customerrepository.findCustomerById(id);
      	return customer;
	}

	public Customer updateCustomerByObj(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerobj = customerrepository.save(customer);
      	return customerobj;
	}

	public Boolean deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		if(customerrepository.existsById(id)) {
			Customer customer = customerrepository.findCustomerById(id);
			customerrepository.delete(customer);
			return true;
		}
		else return false;
	}

	public Optional<List<Customer>> getTheUsersByQuery() {
		// TODO Auto-generated method stub
		Optional<List<Customer>> cuslist = customerrepository.getTheUsersByQuery();
		return cuslist;
	}
}
