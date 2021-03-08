package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ICustomerDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IFacturaDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProductDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private ICustomerDAO customerRepository;

	@Autowired
	private IFacturaDAO facturaRepository;
	
	@Autowired
	private IProductDAO productRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Customer> findByDocument(String term) {
		return customerRepository.findByDocument(term);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaRepository.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Customer> listCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findOneCostumer(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
