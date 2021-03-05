package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Controller
public class FacturacionController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/store/factura")
	public String facturacion()
	{
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
		return "redirect:/store/products";
	}
}