package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;

/**
 * @author dlasso
 * @apiNote Excepcion lanzada cuando una regla de negocio se rompe
 */
public class BusinessLogicException  extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	private String recommendation;

	
	public BusinessLogicException(String message, String recommendation) {
		super(message);
		this.recommendation = recommendation;
	}



	public String getRecommendation() {
		return recommendation;
	}

}
