package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances;

/**
 * @author dlasso
 * @apiNote Permite categorizar a los usuarios
 */
public enum UserType {
	INTERNO("ut001","Interno","El usuario podrá ser asignado a Sistemas de Administración, Reportes y Generación de Comprobantes."),
	EXTERNO_CLIENTE("ut002","Cliente","El usuario será asignado a el Sistema: Tienda Virtual.");
	
	private String code;
	private String name;
	private String description;
	
	
	private UserType(String code, String name) {
		this.code = code;
		this.name = name;
	}


	private UserType(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}


	public String getCode() {
		return code;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}

	
}
