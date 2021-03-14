package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.name_product like %?1%")
	public List<Product> findByName(String term);
	
}
