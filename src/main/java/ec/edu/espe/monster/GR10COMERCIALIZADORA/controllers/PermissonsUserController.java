package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdatePermissonsDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IPermissonsServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;

@Controller
public class PermissonsUserController {
	
	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPermissonsServices permissonsServices;

	@GetMapping("/users/permissons")
	public String index(Model model, Principal principal) {
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("usuarios",userService.listUsers());
		return "/users/permissons";
	}
	
	@PostMapping("/users/permissons/update")
	public String updatePermissons(UpdatePermissonsDTO request) {
		permissonsServices.updatePermissons(request);
		return "redirect:/users/permissons";
	}
	
	
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Permisos de Usuarios";
	}
}
