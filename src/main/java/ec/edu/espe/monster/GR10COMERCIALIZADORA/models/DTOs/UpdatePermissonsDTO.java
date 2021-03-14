package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class UpdatePermissonsDTO {
	private String nickname;
	private List<String> newPermisons;
}
