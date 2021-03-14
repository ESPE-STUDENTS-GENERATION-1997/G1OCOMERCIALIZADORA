package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProductDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		if(product.getCode_product() != null && product.getCode_product() > 0)
		{
			em.merge(product);
		}
		else
		{
			productRepository.save(product);
		}
	}

	@Override
	public Product findOneProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> findByName(String term) {
		return productRepository.findByName(term);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.delete(findOneProduct(id));
	}
	
}
