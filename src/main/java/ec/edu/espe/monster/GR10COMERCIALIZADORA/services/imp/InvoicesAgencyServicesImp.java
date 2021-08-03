package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ICustomerDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.InvoicesAgencyDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ItemsInvoicesAgencyDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.InvoicesAgencySaveRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemsInvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.InvoicesAgencyServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class InvoicesAgencyServicesImp implements InvoicesAgencyServices {
	
	@Autowired
	private ICustomerDAO customerDAO;
	
	@Autowired
	private ItemsInvoicesAgencyDAO itemsDAO;
	
	@Autowired
	private InvoicesAgencyDAO invoiceDAO;
	
	@Override
	public void trySaveInvoice(InvoicesAgencySaveRequest request) {
		InvoicesAgency invoice = new InvoicesAgency();
		invoice.setEmision_invoice_ag(new Date());
		Customer cust = this.getCustomer(request.getId_customer());
		invoice.setCustomer(cust);
		
		invoice = this.saveInvoice(invoice);
		
		if(invoice != null) {
			log.info("Save INVOICE   num: " + invoice.getId_invoice_ag() );
			this.saveItems(invoice, request.getItems());
		}
	}
	
	@Override
	public InvoicesAgency saveInvoice(InvoicesAgency invoice) {
		try {
			return invoiceDAO.save(invoice);
		} catch (DataAccessException e) {
			log.error("NOT SAVE NEW INVOICE  [exception]:  " +e.getMessage());
		}
		return null;
	}

	@Override
	public Customer getCustomer(Long idCustomer) {
		Optional<Customer> cusOpt = customerDAO.findById(idCustomer);
		
		if(!cusOpt.isPresent()) {
			Customer cus = new Customer();
			cus.setId_customer(1L);
			cusOpt = Optional.of(cus);
		}
		
		return cusOpt.get();
	}


	private void saveItems(InvoicesAgency invoice, List<ItemsInvoicesAgency> items) {
		for(ItemsInvoicesAgency it : items) {
			it.setInvoice(invoice);
		}
		
		try {
			itemsDAO.saveAll(items);
			log.info("Save ITEMS INVOICE   num: " + items.size() );
		} catch (DataAccessException e) {
			log.error("NOT SAVE ITEMS INVOICE  [exception]:  " +e.getMessage());
		}
	}

	@Override
	public InvoicesAgency findInvoiceById(Long idInvoice) {
		Optional<InvoicesAgency> invOpt = invoiceDAO.findById(idInvoice);
		if(!invOpt.isPresent()) {
			InvoicesAgency invoice = new InvoicesAgency();
			invoice.setId_invoice_ag(1L);
			invOpt = Optional.of(invoice);
		}
		return invOpt.get();
	}


	@Override
	public InvoicesAgency saveInvoice(InvoicesAgencySaveRequest request) {
		Customer customer = this.getCustomer(request.getId_customer());
		InvoicesAgency invoice = new InvoicesAgency();
		invoice.setCustomer(customer);
		invoice = this.saveInvoice(invoice);
		if(invoice != null && request.getItems() != null ) {
			this.saveItems(invoice, request.getItems());	
		}
		return invoice;
	}


	@Override
	public ItemsInvoicesAgency buildNewItem(InvoicesAgencySaveRequest requestDTO) {
		if(requestDTO != null) {
			ItemsInvoicesAgency item = new ItemsInvoicesAgency();
			item.setAgency(requestDTO.getAgency());
			item.setDateFlight(requestDTO.getDateFlight());
			item.setDescription(requestDTO.getDescription());
			item.setFlight(requestDTO.getFlight());
			item.setIdCityDestiny(requestDTO.getIdCityOrigin());
			item.setIdCityOrigin(requestDTO.getIdCityOrigin());
			item.setNumTickets(requestDTO.getNumTickets());
			item.setPrice(requestDTO.getPrice());
			return item;
		}
		return null;
	}

	@Override
	public List<InvoicesAgency> getInvoicesByCustomer(Customer customer) {
		return invoiceDAO.findByCustomer(customer);
	}




	
}
