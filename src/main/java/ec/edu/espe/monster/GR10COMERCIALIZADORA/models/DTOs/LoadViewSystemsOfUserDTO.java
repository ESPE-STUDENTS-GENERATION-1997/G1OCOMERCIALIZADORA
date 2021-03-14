package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author dlasso
 * 
 */
@Data
public class LoadViewSystemsOfUserDTO {
	private String keywordSystem;
	private String nameSystem;
	private String descriptionSystem;
	private Long codeAssignamet;
	private LocalDateTime assignmentDate;
	private Boolean isSelected;
}
