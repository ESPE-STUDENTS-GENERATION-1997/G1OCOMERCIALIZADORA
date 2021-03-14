package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemFactura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICustomerService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Controller
public class FacturacionController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping("/store/factura")
	public String facturacion(Model model,Principal principal)
	{
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		return "/store/factura";
	}
	
	@GetMapping(value="/cargar-cliente/{term}", produces= {"application/json"})
	public @ResponseBody List<Customer> cargarClientes(@PathVariable String term)
	{
		return customerService.findByDocument(term);
	}
	
	@GetMapping(value="/cargar-producto/{term}", produces= {"application/json"})
	public @ResponseBody List<Product> cargarProductos(@PathVariable String term)
	{
		return productService.findByName(term);
	}
	
	@PostMapping("/form")
	public String guardar(Factura factura, 
						  @RequestParam(name="item_code_product[]",required=false) Long[] itemId, 
						  @RequestParam (name="cantidad[]", required=false) Integer[] cantidad,
						  RedirectAttributes flash)
	{
		for(int i = 0; i < itemId.length; i++)
		{
			Product producto = customerService.findProductById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setCantidad_item(cantidad[i]);
			linea.setProduct(producto);
			factura.addItemFactura(linea);
		}
		customerService.saveFactura(factura);
		flash.addFlashAttribute("success", "Factura creada con éxito");
		return "redirect:/fact-cliente/"+factura.getCustomer().getId_customer();
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Generar una factura";
	}
	
	@GetMapping("/factura/{id_factura}")
	public String getFactura(@PathVariable(value="id_factura") Long id_factura,Model model,RedirectAttributes flash)
	{
		Factura factura = customerService.findFacturaById(id_factura);
		model.addAttribute("factura", factura);
		model.addAttribute("titulo","Factura: "+factura.getDescripcion_factura()+" con el código: "+factura.getId_factura());
		return"/store/detalle-factura";
	}
	
}
