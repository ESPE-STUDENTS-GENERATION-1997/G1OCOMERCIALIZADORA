package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.editors.SystemAppEditorByKeyword;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.editors.UserTypeEditor;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.GenerateTempCredentialRequestDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserRegisterDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IAddressHomeService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICitysServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICredentialServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ISystemAppServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;



@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressHomeService addressService;
	

	@Autowired
	private IHandleInternalViews handlerInternalViews;
	
	@Autowired
	private ISystemAppServices systemAppServices;
	
	@Autowired
	private ICitysServices cityServices;
	
	@Autowired
	private UserTypeEditor userTypeEditor;
	
	@Autowired
	private SystemAppEditorByKeyword systemEditor;
	
	
	@Autowired
	private ICredentialServices credentialServices;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserType.class, "type", userTypeEditor);
		binder.registerCustomEditor(SystemApp.class, "permissons", systemEditor);
	}
	
	@GetMapping("/users/index")
	public String usersController(Model model, Principal principal){
		model.addAttribute("titulo","Lista de Usuarios");
		model.addAttribute("usuarios",userService.listUsers());
		model.addAttribute("direcciones", addressService.listAddressHome());
		model.addAttribute("usuario",new UserRegisterDTO());
		model.addAttribute("menu", handlerInternalViews.loadMenuByPrincipalUser(principal.getName()));
		model.addAttribute("systemsApp",systemAppServices.getAllSystems());
		model.addAttribute("types",UserType.values());
		model.addAttribute("citys",cityServices.getAllCitys());
		return "/users/users-view";
	}
	
	@PostMapping("/user/temp-credential/generation")
	public String generateTempCredentials(GenerateTempCredentialRequestDTO request) {
		credentialServices.generateTempCredentials(request);
		return "redirect:/users/index";
	}
	
	@PostMapping("/newUser")
	public String newUser(@Valid UserRegisterDTO userRegisterRequest, RedirectAttributes flash)
	{
		userService.saveUserWithPermisson(userRegisterRequest);
		flash.addFlashAttribute("success", "Usuario agregado correctamente");
		return "redirect:/users/index";
	}
	
	@ModelAttribute(name = "titlePage")
	public String addTittlePage() {
		return "Administración de Usuarios";
	}
	
	
}
