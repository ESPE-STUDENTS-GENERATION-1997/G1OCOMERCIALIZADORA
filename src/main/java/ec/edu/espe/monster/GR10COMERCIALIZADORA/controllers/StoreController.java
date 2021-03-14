package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
	
	@GetMapping({"", "/"})
	public String indexStore() {
		return "/store/home";
	}
	
	
}
