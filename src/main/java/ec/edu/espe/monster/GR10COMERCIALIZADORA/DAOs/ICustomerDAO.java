package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;

public interface ICustomerDAO extends CrudRepository<Customer, Long>{
	
	@Query("select c from Customer c where c.document_customer like %?1%")
	public List<Customer> findByDocument(String term);
}
