package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="FEITE_ITEFAC")
@Data
public class ItemFactura implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_item;
	private Integer cantidad_item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//Se puede omitir: @JoinColumn(name="product_id"), porque es unidireccional no necesito listar los productos por items
	private Product product;
	
	public Double calcularImporte()
	{
		return cantidad_item.doubleValue()*product.getPrice_product();
	}
	
	private static final long serialVersionUID = 1L;
}
