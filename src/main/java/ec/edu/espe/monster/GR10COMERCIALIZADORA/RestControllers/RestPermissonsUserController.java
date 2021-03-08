package ec.edu.espe.monster.GR10COMERCIALIZADORA.RestControllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoadViewSystemsOfUserDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IPermissonsServices;

@RestController
public class RestPermissonsUserController {

	@Autowired
	private IPermissonsServices permissonsServices;
	
	@GetMapping(value = "/api/user/{username}/profiles")
	public List<LoadViewSystemsOfUserDTO> getAllProfiles(@PathVariable String username ){
		return permissonsServices.loadProfilesByUsername(username);
	}
}
