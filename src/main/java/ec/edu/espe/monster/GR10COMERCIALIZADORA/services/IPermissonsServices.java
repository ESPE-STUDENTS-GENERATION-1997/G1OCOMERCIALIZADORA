package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;
import java.util.Map;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoadViewSystemsOfUserDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdatePermissonsDTO;

public interface IPermissonsServices {
	
	public List<LoadViewSystemsOfUserDTO> loadProfilesByUsername(String username);
	
	public String updatePermissons(UpdatePermissonsDTO request);
	
}
