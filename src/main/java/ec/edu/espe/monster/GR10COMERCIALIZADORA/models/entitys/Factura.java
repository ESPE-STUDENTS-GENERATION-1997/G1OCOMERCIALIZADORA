package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="FEFAC_FACTUR")
@Data
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_factura;
	private String descripcion_factura;
	private String observacion_factura;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date emision_factura;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura> items;
	
	public Factura()
	{
		this.items = new ArrayList<ItemFactura>();
	}
	
	@PrePersist
	public void prePesiste()
	{
		emision_factura = new Date();
	}
	
	public void addItemFactura(ItemFactura items)
	{
		this.items.add(items);
	}
	
	public Double getTotal()
	{
		Double total = 0.0;
		int size = items.size();
		for(int i = 0; i<size; i++)
		{
			total += items.get(i).calcularImporte();
		}
		return total;
	}
	
	private static final long serialVersionUID = 1L;

}
