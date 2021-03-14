package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.GenerateTempCredentialRequestDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdateCredentialDTO;

/**
 * @author dlasso
 * @apiNote - Gestiona: Olvido de credenciales
 * @apiNote - Gestiona: Actualizar las contraseñas
 */
public interface ICredentialServices {

	public String updateCredentials(UpdateCredentialDTO request);
	
	public String forgetCredentials();
	
	public void generateTempCredentials(GenerateTempCredentialRequestDTO request);
	
}
