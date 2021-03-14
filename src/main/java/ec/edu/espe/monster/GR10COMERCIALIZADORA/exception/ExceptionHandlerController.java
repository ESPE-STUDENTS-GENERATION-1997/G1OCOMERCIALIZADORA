package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Maneja los errores, captura las excepciones
 * mapea una excepcion.
 */
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(AuthenticationCustomException.class)
	public String authenticationCustomExceptionHandler(AuthenticationCustomException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return ex.getViewResolver();
	}
	
	@ExceptionHandler(BusinessLogicException.class)
	public String  bussinessLogicExceptionHandler(BusinessLogicException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		model.addAttribute("recommendation", ex.getRecommendation());
		return "/error/500";
	}
	
	@ExceptionHandler(DataCustomException.class)
	public String dataCustomExceptionHandler(DataCustomException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("entity", ex.getEntity() );
		model.addAttribute("descriptionAccionFail", ex.getDescriptionAccionFail());
		return "/error/409";
	}
}
