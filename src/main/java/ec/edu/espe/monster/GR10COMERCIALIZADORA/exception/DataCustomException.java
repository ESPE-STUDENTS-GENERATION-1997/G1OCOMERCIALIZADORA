package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;

import java.util.Map;

/**
 * @author dlasso
 * @apiNote Excepción lanzada cuando el acceso a la base de datos falle
 */
public class DataCustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String entity;
	private String descriptionAccionFail;
	private Map<String, Object> moreInfo;
	
	
	
	public DataCustomException(String message) {
		super(message);
	}
	
	

	public DataCustomException(String message,String entity, String descriptionAccionFail, Map<String, Object> moreInfo) {
		super(message);
		this.entity = entity;
		this.descriptionAccionFail = descriptionAccionFail;
		this.moreInfo = moreInfo;
	}



	public String getEntity() {
		return entity;
	}

	public String getDescriptionAccionFail() {
		return descriptionAccionFail;
	}
	public Map<String, Object> getMoreInfo() {
		return moreInfo;
	}
	
}
