package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdateCredentialDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICredentialServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IRedirectLogin;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class CredentialsServicesImp implements ICredentialServices {
	
	@Autowired
	private IUserAppDAO userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IRedirectLogin redirectLogin;

	@Override
	public String updateCredentials(UpdateCredentialDTO request) {
		UserApp user =  userDao.findByNickname(request.getUsername()).orElseThrow(()-> new AuthenticationCustomException("Nombre de usuario o credenciales incorrectas",
				"/onboarding/change-credentials", null));
		try {
			if(passwordEncoder.matches(request.getCurrentCredential(), user.getPassword())) {
				if(request.getNewCredential().equals(request.getConfirmNewCredential()) ) {
					user.setPassword(request.getNewCredential());
					userDao.save(user);
					log.info("[UPDATE USER CREDENTIALS] username: " + request.getUsername());
				}else {
					log.error("[NEW CREDENTIALS NOT MATCH]");
					throw new AuthenticationCustomException("La nueva contraseña no coincide con la confirmación de la misma.",
							"/onboarding/change-credentials", null);
				}
			}else {
				log.error("[OLD CREDENTIALS NOT MATCH]");
				throw new AuthenticationCustomException("Nombre de usuario o credenciales incorrectas",
						"/onboarding/change-credentials", null);
			}
		} catch (Exception e) {
			log.error("[INTERNAL ERROR]");
			throw e;
		}
		return redirectLogin.redirectByUserType(user.getType());
	}

	@Override
	public String forgetCredentials() {
		return null;
	}

	
}
