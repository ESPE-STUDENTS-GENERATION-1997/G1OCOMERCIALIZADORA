package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.ArrayList;
import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemsInvoicesAgency;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoicesAgencySaveRequest {

	
	private String agency;
	private Long flight;
	private Long idCityOrigin;
	private Long idCityDestiny;
	private String description;
	private String dateFlight;
	private Double price;
	private Integer numTickets;
	private List<ItemsInvoicesAgency> items;
	
	
	//customer
	private Long id_customer;
	private String names_customer;
	private String document_customer;
	private String email_customer;
	private String phone_customer;
	private String address_customer;
	
	
	public Double getTotalInvoice() {
		if(!items.isEmpty()) {
			Double totalInv = 0D;
			for(ItemsInvoicesAgency item : items) {
				totalInv = totalInv + item.getTotal();
			}
			return Math.round(  totalInv * 100D) / 100D;
		}
		return 0D;
	}
	
	public InvoicesAgencySaveRequest(InvoicesAgency invoice) {
		Customer customer = invoice.getCustomer();
		if(customer == null) {
			customer = new Customer();
		}
		this.names_customer = customer.getNames_customer() + " " + customer.getLastnames_customer();
		this.email_customer = customer.getEmail_customer();
		this.document_customer = customer.getDocument_customer();
		this.phone_customer = customer.getPhone_customer();
		this.address_customer = customer.getAddress_customer();
		
		this.items = invoice.getItems();
		if(items == null) {
			this.items = new ArrayList<ItemsInvoicesAgency>();
		}
	}
}
