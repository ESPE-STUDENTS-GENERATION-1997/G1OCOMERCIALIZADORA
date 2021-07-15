package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProductDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.TableAmortizationResponse;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Customer;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ItemFactura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICustomerService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IGenerateTableAmortization;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FacturacionController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@Autowired
	private IUserAppDAO userDAO;
	
	@Autowired
	private IProductDAO productoDAO;
	
	@Autowired
	private IGenerateTableAmortization iTableAmortization;
	
	@GetMapping("/store/factura")
	public String facturacion(Model model,Principal principal)
	{
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()) );
		return "store/factura";
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
		List<Product> products = new ArrayList<Product>();
		for(int i = 0; i < itemId.length; i++)
		{
			Product producto = customerService.findProductById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setCantidad_item(cantidad[i]);
			linea.setProduct(producto);
			factura.addItemFactura(linea);
			//Menorar el stock
			Integer stock = producto.getStock_product() - cantidad[i];
			producto.setStock_product((stock < 0) ? 0 : stock);
			products.add(producto);
		}
		
		try {
			customerService.saveFactura(factura);
			productoDAO.saveAll(products);
			flash.addFlashAttribute("success", "Factura creada con éxito");
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error("No se puedo actualizar los registros al generar la factura");
		}
		
		
		return "redirect:/fact-cliente/"+ factura.getCustomer().getId_customer();
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Generar una factura";
	}
	
	@GetMapping("/factura/{id_factura}")
	public String getFactura(@PathVariable(value="id_factura") Long id_factura,Model model, Principal principal, RedirectAttributes flash)
	{
		Factura factura = customerService.findFacturaById(id_factura);
		model.addAttribute("factura", factura);
		model.addAttribute("titulo","Factura:  001-001-000000"+factura.getId_factura());
		model.addAttribute("seller", userDAO.findByNickname(principal.getName()).orElse(new UserApp()) );
		
		Double subtotal = factura.getTotal();
		Double discount = 0D;
		List<TableAmortizationResponse> tableAmortization = new ArrayList<TableAmortizationResponse>();
		
		if(factura.getType_paid().equals("1")) {
			discount = subtotal * 0.33D;
		}else if(factura.getType_paid().equals("2")) {
			tableAmortization = iTableAmortization.generate(factura);
		}
		
		Double total = subtotal - discount;
		
		model.addAttribute("subtotal",  String.format("%.2f", subtotal) );
		model.addAttribute("descuento",  String.format("%.2f", discount) );
		model.addAttribute("total", String.format("%.2f", total) );
		
		
		model.addAttribute("tableAmortization", tableAmortization );
		return"store/detalle-factura";
	}
	
}
