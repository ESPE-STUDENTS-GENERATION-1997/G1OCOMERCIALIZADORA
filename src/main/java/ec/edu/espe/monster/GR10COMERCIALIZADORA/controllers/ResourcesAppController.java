package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IResourcesAppServices;

@Controller
public class ResourcesAppController {
	
	@Autowired
	private IResourcesAppServices resourcesAppServices;
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping("/config/pages")
	public String index(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("pages", resourcesAppServices.getAll());
		return "/config/resources";
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Configuración de Páginas de la Aplicación";
	}
	
}
