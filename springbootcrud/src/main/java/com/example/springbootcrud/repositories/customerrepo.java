package com.example.springbootcrud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootcrud.models.Customer;
@Repository
public interface customerrepo extends JpaRepository<Customer, Integer>  {

	
	public Customer findCustomerById(int id);
	
	@Query(value = "select * from User", nativeQuery = true)
	Optional<List<Customer>> getTheUsersByQuery();
	
	/*
	 * @Query(value = "from Customer", nativeQuery = false)
	   Optional<List<Customer>> getTheUsersByQuery();
	  @Query(value = "update Users u set u.status = ? where u.name = ?", nativeQuery = true)
       int updateUserSetStatusForNameNative(Integer status, String name);
	 */
}
