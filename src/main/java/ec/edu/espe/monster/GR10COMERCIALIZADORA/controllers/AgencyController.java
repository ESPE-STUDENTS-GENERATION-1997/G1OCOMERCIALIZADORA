package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.clients.WebClientRestAeroline;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.CityResponseApi;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.InvoicesAgencySaveRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemsInvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICustomerService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.InvoicesAgencyServices;

@Controller
public class AgencyController {

	@Autowired
	private IHandleInternalViews handlerInternalViews;

	@Autowired
	private WebClientRestAeroline webClientRestAeroline;

	@Autowired
	private InvoicesAgencyServices invoiceServices;

	@Autowired
	private IUserAppDAO userDAO;
	

	@Autowired
	private ICustomerService customerService;

	@GetMapping({ "/agencia", "/agencia/index" })
	public String indexAgency(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()));
		List<CityResponseApi> cities = webClientRestAeroline.getAllCitys();
		model.addAttribute("cities", cities);
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		model.addAttribute("date", datetime.format(formatter));
		model.addAttribute("time", datetime.getHour() + ":" + datetime.getMinute());
		InvoicesAgencySaveRequest invoice = new InvoicesAgencySaveRequest();
		List<ItemsInvoicesAgency> items = new ArrayList<ItemsInvoicesAgency>();
		invoice.setItems(items);
		model.addAttribute("invoice", invoice);
		model.addAttribute("invoiceItem", invoice);
		model.addAttribute("items", items);
		return "agencia/index";
	}

	@PostMapping("/agencia/update-items")
	public String addItemInvoiceAgency(@ModelAttribute(value = "invoiceItem") InvoicesAgencySaveRequest requestDTO,
			Model model, Principal principal) {
		List<ItemsInvoicesAgency> items = new ArrayList<ItemsInvoicesAgency>();
		
		if (requestDTO != null) {
			items = requestDTO.getItems();
			if (items == null) {
				items = new ArrayList<ItemsInvoicesAgency>();
			}
			
			ItemsInvoicesAgency item = invoiceServices.buildNewItem(requestDTO);
			items.add(item);

		}
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()));
		List<CityResponseApi> cities = webClientRestAeroline.getAllCitys();
		model.addAttribute("cities", cities);
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		model.addAttribute("date", datetime.format(formatter));
		model.addAttribute("time", datetime.getHour() + ":" + datetime.getMinute());

		InvoicesAgencySaveRequest invoice = requestDTO;
		invoice.setItems(items);
		model.addAttribute("invoice", invoice);
		model.addAttribute("invoiceItem", invoice);
		return "agencia/index";
	}

	@PostMapping("/agencia")
	public String saveInvoiceAgency(@ModelAttribute(value = "invoice") InvoicesAgencySaveRequest requestDTO,
			Model model, Principal principal) {
		invoiceServices.trySaveInvoice(requestDTO);
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()));
		model.addAttribute("invoice", requestDTO);
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		model.addAttribute("date", datetime.format(formatter));
		model.addAttribute("time", datetime.getHour() + ":" + datetime.getMinute());
		return "agencia/factura";
	}

	@GetMapping("/agencia/ventas")
	public String sellTickets(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		List<Customer> listCustomers = customerService.listCustomers();
		Customer customer = new Customer();
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("customers", listCustomers);
		model.addAttribute("customer",customer);
		return "agencia/customers-agency";
	}
	
	@GetMapping("/agencia/customer/{idCustomer}/detail")
	public String reportInvoice(@PathVariable Long idCustomer,Model model, Principal principal) {
		Customer customer = invoiceServices.getCustomer(idCustomer);
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("titulo", "Ventas a nombre de: "+  customer.getNames_customer() + " " + customer.getLastnames_customer());
		model.addAttribute("customer", customer);
		model.addAttribute("invoices",invoiceServices.getInvoicesByCustomer(customer));
		return "agencia/invoices-agency-customer";
	}

	@GetMapping("/agencia/ventas/{idFactura}/factura")
	public String getInvoice(@PathVariable Long idFactura, Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()));
		LocalDateTime datetime = LocalDateTime.now();
		InvoicesAgency invoice = invoiceServices.findInvoiceById(idFactura);
		InvoicesAgencySaveRequest invoiceDTO = new InvoicesAgencySaveRequest(invoice);
		model.addAttribute("invoice", invoiceDTO);
		model.addAttribute("items", invoiceDTO.getItems());
		model.addAttribute("date",
				datetime.getDayOfMonth() + " - " + datetime.getMonth() + " - " + datetime.getDayOfYear());
		model.addAttribute("time", datetime.getHour() + ":" + datetime.getMinute());
		return "agencia/factura";
	}
	
	@GetMapping("/agencia/ventas/{flight}/idVuelo/{dateFlight}/fecha-vuelo/{price}/precio/{asiento}/asiento")
	public String getTicket(@PathVariable Long flight,@PathVariable String  dateFlight, @PathVariable String  asiento, @PathVariable String price,  Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()));
		model.addAttribute("flight", flight);
		model.addAttribute("dateFlight", dateFlight);
		model.addAttribute("price", price);
		model.addAttribute("asiento", asiento);
		return "agencia/ticket";
	}

}
