package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping({"", "/", "/store", "/store/home"})
	public String indexStore() {
		return "/store/home";
	}
	
	@GetMapping(value="/store/products")
	public String listProducts(Model model,Principal principal)
	{
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		List<Product> listProducts = productService.listProducts();
		Product product = new Product();
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", listProducts);
		model.addAttribute("product",product);
		return "/store/products";
	}
	
	@PostMapping(value="/addProduct")
	public String addProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile img_product ,RedirectAttributes flash)
	{
		if(!img_product.isEmpty()) 
		{
			String uniqueFileName = UUID.randomUUID().toString() + "_" + img_product.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			try {
				Files.copy(img_product.getInputStream(), rootAbsolutePath);
				product.setImg_product(uniqueFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		productService.addProduct(product);
		flash.addFlashAttribute("success", "Operación exitosa");
		
		return "redirect:/store/products";
	}
	
	@RequestMapping(value="/getOneProduct/{code_product}")
	public @ResponseBody Product getOneProduct (@PathVariable(value="code_product")Long id)
	{
		Product product = null;
		product = productService.findOneProduct(id);
		return product;
	}
	
	@PostMapping(value="/editProduct")
	public String editProduct(@ModelAttribute Product product , @ModelAttribute MultipartFile img_product_edit , RedirectAttributes flash)
	{
		if(img_product_edit.isEmpty())
		{
			productService.addProduct(product);
			flash.addFlashAttribute("success", "Operación exitosa");
		}
		else
		{
			String uniqueFileName = UUID.randomUUID().toString() + "_" + img_product_edit.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			try {
				Files.copy(img_product_edit.getInputStream(), rootAbsolutePath);
				product.setImg_product(uniqueFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			productService.addProduct(product);
			flash.addFlashAttribute("success", "Operación exitosa");
		}
		
		/**
		productService.addProduct(product);
		flash.addFlashAttribute("success", "Operación exitosa");
		**/
		return "redirect:/store/products";
	}
	
	
	@RequestMapping(value="/delete/{code_product}")
	public String eliminar (@PathVariable(value="code_product") Long code_product,RedirectAttributes flash)
	{
		if(code_product > 0)
		{
			productService.deleteProduct(code_product);
		}
		flash.addFlashAttribute("error", "Producto eliminado correctamente");
		return("redirect:/store/products");
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Gestión de Productos";
	}
	
}
