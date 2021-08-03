package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;

public interface InvoicesAgencyDAO extends JpaRepository<InvoicesAgency, Long> {

	List<InvoicesAgency> findByCustomer(Customer customer);
}
