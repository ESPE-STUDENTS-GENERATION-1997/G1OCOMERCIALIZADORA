package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dlasso
 * @apiNote objeto que representa un item del menu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
	private Boolean isMenuCategory;
	private String name;
	private String url;
	private String keyword;
	private List<MenuItemDTO> subItems;
}
