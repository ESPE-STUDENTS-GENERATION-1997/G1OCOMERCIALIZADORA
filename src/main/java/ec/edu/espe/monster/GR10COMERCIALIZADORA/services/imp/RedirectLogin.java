package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IRedirectLogin;

@Service
@Primary
public class RedirectLogin implements IRedirectLogin {

	@Override
	public String redirectByUserType(UserType userType) {
		String path = "/store/home";
		switch (userType) {
		case EXTERNO_CLIENTE:
			path = "redirect: /store/home";
			break;
		case INTERNO:
			path = "redirect:/internal/home";
			break;
		default:
			break;
		}
		return path;
	}

}
