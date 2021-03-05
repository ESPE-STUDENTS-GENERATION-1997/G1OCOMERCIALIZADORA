package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProductDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productRepository;
	
	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product findOneProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> findByName(String term) {
		return productRepository.findByName(term);
	}
	
}
