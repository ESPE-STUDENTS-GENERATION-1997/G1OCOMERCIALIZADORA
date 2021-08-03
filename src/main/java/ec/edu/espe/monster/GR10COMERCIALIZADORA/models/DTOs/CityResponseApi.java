package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import lombok.Data;

@Data
public class CityResponseApi {

	private Long id;
	
	private String code;
	
	private String name;
	
	private String country;
	
	private String type;
}
