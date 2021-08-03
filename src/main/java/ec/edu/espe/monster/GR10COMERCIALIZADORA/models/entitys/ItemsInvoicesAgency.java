package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="FEIT_ITEFAC")
@Data
public class ItemsInvoicesAgency {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_item_ag;
	
	
	private String agency;
	private Long flight;
	private Long idCityOrigin;
	private Long idCityDestiny;
	private String description;
	private String dateFlight;
	private Double price;
	private Integer numTickets;

	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private InvoicesAgency invoice;
	
	public Double getTotal() {
		if(this.price != null && this.numTickets != null) {
			return Math.round( (this.price * this.numTickets)* 100D) /100.0D;
		}
		return 0D;
	}
}
