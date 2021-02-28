package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import lombok.Data;

@Data
public class UpdateCredentialDTO {
	private String currentCredential;
	private String username;
	private String newCredential;
	private String confirmNewCredential;
}
