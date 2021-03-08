package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XEREC_RECUR")
@Data
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_recurso")
	private Long id;

	@NotEmpty(message = "La url del recurso es necesaria")
	@Column(nullable = false, name = "url_recurso", unique = true)
	private String url;
	
	@NotEmpty(message = "El nombre del recurso para el menú es necesario.")
	@Column(nullable = false, length = 100, name = "nombre_menu_recurso")
	private String nameMenu;
	
	@NotEmpty(message = "El título de página es necesario.")
	@Column(nullable = false, length = 100, name = "titulo_pag_recurso")
	private String titlePage;
	
	@Column( length = 300, name = "descripcion_recurso")
	private String description;
	
	@OneToMany(mappedBy = "resource", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ResourcesOfSystem> resourcesOfSystem;
	
}
