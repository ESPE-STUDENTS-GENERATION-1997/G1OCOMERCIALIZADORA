package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ICustomerDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;

public interface ICustomerService {
	public List<Customer> listCustomers();
	public List<Customer> findByDocument(String term);
	public void saveFactura(Factura factura);
	public Product findProductById(Long id);
	public Customer findOneCostumer(Long id);
	public Factura findFacturaById(Long id);
	public void addCustomer(Customer customer);
}
