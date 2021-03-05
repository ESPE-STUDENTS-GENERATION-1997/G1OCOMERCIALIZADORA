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
}
