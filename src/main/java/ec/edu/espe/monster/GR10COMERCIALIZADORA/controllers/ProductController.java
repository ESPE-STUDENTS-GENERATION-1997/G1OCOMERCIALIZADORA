package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Product;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping({"", "/", "/store", "/store/home"})
	public String indexStore() {
		return "/store/home";
	}
	
	@GetMapping(value="/store/products")
	public String listProducts(Model model)
	{
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
		flash.addFlashAttribute("success", "Producto añadido de forma exitosa");
		
		return "redirect:/store/products";
	}
}
