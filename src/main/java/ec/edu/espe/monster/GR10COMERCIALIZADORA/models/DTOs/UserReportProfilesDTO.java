package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReportProfilesDTO {
	private String  observations;
	private List<String> profiles;
}
