package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICustomerService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;

@Controller
public class CustomerController {
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping(value="/customers")
	public String listCustomers(Model model,Principal principal)
	{
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		List<Customer> listCustomers = customerService.listCustomers();
		Customer customer = new Customer();
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("customers", listCustomers);
		model.addAttribute("customer",customer);
		return "/store/customers";
	}
	
	@GetMapping(value="/fact-cliente/{id_costumer}")
	public String verFacturas(@PathVariable(value="id_costumer") Long id, Map<String, Object>model, RedirectAttributes flash)
	{
		Customer customer = customerService.findOneCostumer(id);
		model.put("customer", customer);
		model.put("titulo", "Facturas que corresponde al cliente: "+customer.getNames_customer()+" "+customer.getLastnames_customer());
		return "store/factura-cliente";
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Gestión de Clientes";
	}
	
	@PostMapping(value="/addCustomer")
	public String addCustomer(@ModelAttribute Customer customer,RedirectAttributes flash) {
		customerService.addCustomer(customer);
		flash.addFlashAttribute("success", "Cliente agregado de forma exitosa");
		return"redirect:/customers";
	}
}
