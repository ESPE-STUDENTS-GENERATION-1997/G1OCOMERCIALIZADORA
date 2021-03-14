package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="FEPRO_PRODUC")
@Data
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code_product;
	@Column( length = 100, nullable = false)
	private String name_product;
	@Column( length = 300)
	private String description_product;
	private Double price_product;
	private Long stock_product;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date created_product;
	
	private String img_product;
	
	private static final long serialVersionUID = 1L;
	

}
