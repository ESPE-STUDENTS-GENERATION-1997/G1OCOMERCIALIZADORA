package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ISystemAppServices;

@Controller
public class SystemsAppController {

	@Autowired
	private ISystemAppServices systemAppServices;
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping("/config/systems")
	public String index(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("systemsApp", systemAppServices.getAllSystems());
		return "/config/systems";
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Configuración de los Sistemas del Aplicativo";
	}
}
