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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="FECLI_CLIENT")
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = -4846444598863013873L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_customer;
	private String names_customer;
	private String lastnames_customer;
	private String document_customer;
	private String email_customer;
	private String phone_customer;
	private String address_customer;
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Factura> facturas;
	public Customer()
	{
		facturas = new ArrayList<Factura>();
	}
	@JsonIgnore
	public void addFactura(Factura factura)
	{
		facturas.add(factura);
	}
}
