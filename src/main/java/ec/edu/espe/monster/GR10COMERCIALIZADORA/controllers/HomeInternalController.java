package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.MenuItemDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserReportProfilesDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;

/**
 * @author dlasso
 * 
 */
@Controller
@RequestMapping("/internal")
public class HomeInternalController {
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@GetMapping("/home")
	public String index(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("reportProfiles", handlerInternalViews.createReportUserProfiles(principal.getName()));
		return "/management/index";
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Comercializador Monster - Internos";
	}
	
	
}
