package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="FEFAG_FACAGE")
@Data
public class InvoicesAgency {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_invoice_ag;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date emision_invoice_ag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Customer customer;
	
	@OneToMany(mappedBy = "invoice",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ItemsInvoicesAgency> items;
	
	@PrePersist
	public void prePesiste()
	{
		emision_invoice_ag = new Date();
	}
	
	public Double getTotalInvoice() {
		Double total = 0D;
		if(items != null && !items.isEmpty()) {
			for(ItemsInvoicesAgency it : items) {
				total = total +it.getTotal();
			}
		}
		return total;
	}
}
