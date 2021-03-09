package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GenerateTempCredentialRequestDTO {
	private String nickname;
	private String observations;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private Date expirationDatta;
}
