package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.InvoicesAgencySaveRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemsInvoicesAgency;

public interface InvoicesAgencyServices {
	
	public void trySaveInvoice(InvoicesAgencySaveRequest request);
	
	public InvoicesAgency saveInvoice(InvoicesAgencySaveRequest request);
	
	public InvoicesAgency saveInvoice(InvoicesAgency invoice);
	
	public InvoicesAgency findInvoiceById(Long idInvoice);
	
	public ItemsInvoicesAgency buildNewItem(InvoicesAgencySaveRequest requestDTO);
	
	public Customer getCustomer(Long idCustomer);
	
	public List<InvoicesAgency> getInvoicesByCustomer(Customer customer);

}
