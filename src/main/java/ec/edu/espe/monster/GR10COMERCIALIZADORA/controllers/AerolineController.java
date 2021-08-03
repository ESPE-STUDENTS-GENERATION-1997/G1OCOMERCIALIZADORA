package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;

@Controller
public class AerolineController {

	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping("/aerolinea")
	public String indexAeroline(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		return "aerolinea/index";
	}
	
}
