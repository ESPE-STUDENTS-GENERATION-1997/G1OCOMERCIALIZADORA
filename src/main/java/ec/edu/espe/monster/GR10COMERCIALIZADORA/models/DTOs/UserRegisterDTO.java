package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import lombok.Data;

@Data
public class UserRegisterDTO {
	
	private String nickname;
	private String names;
	private String lastnames;
	private String email;
	private String phone;
	private String numDocument;
	private String disability;
	private UserType type;
	private String city;
	private String neighborhood;
	private String mainStreet;
	private String secondaryStreet;
	private String houseNumbering;
	private List<SystemApp> permissons;
}
