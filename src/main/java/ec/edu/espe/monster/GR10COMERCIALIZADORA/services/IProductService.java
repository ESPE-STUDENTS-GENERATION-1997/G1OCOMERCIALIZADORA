package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;
import java.util.Optional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;

public interface IProductService {
	public List<Product> listProducts();
	public void addProduct(Product product);
	public Product findOneProduct(Long id);
	public List<Product> findByName(String term);
	public void deleteProduct(Long id);
}
