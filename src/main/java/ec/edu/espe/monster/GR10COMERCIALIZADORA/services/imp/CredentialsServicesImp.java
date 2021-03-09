package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IStateUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.GenerateTempCredentialRequestDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdateCredentialDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.KeywordsApplication;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICredentialServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IRedirectLogin;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStateUserService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;
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
	
	@Autowired
	private IStateUserService stateUserServices;
	
	@Autowired
	private IStateUserDAO stateUserDAO;
	
	@Autowired
	private IUserService userServices;
	
	@Autowired
	private SendMailServiceImpl sendMailService;

	@Override
	public String updateCredentials(UpdateCredentialDTO request) {
		UserApp user =  userDao.findByNickname(request.getUsername()).orElseThrow(()-> new AuthenticationCustomException("Nombre de usuario o credenciales incorrectas",
				"/onboarding/change-credentials", null));
		try {
			if(passwordEncoder.matches(request.getCurrentCredential(), user.getPassword())) {
				if(request.getNewCredential().equals(request.getConfirmNewCredential()) ) {
					user.setPassword(passwordEncoder.encode(request.getNewCredential()));
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
		this.changeStateUser(user);
		return redirectLogin.redirectByUserType(user.getType());
	}
	
	private void changeStateUser(UserApp user) {
		
		StateUser state = stateUserDAO.findByUser(user).orElseThrow(()->  new AuthenticationCustomException("Usuario no coincide con un estado registrado.",
				"/onboarding/change-credentials", null));
		
		state.setAssignmentDate(LocalDateTime.now());
		stateUserServices.saveStateToUserByKeyword(state, user, KeywordsApplication.ENTITY_STATE_USER_ACTIVE);
	}

	@Override
	public String forgetCredentials() {
		return null;
	}

	@Override
	public void generateTempCredentials(GenerateTempCredentialRequestDTO request) {
		UserApp user =  userDao.findByNickname(request.getNickname()).orElseThrow(()-> new AuthenticationCustomException("Nombre de usuario o credenciales incorrectas",
				"/onboarding/change-credentials", null));
		
		String password = userServices.generatePassword();
		user.setPassword(passwordEncoder.encode(password));
		
		userDao.save(user);
		
		StateUser state = stateUserDAO.findByUser(user).orElseThrow(()->  new AuthenticationCustomException("Usuario no coincide con un estado registrado.",
				"/onboarding/change-credentials", null));
		state.setAssignmentDate(LocalDateTime.now());
		state.setObservation(request.getObservations());
		LocalDateTime expirationCred = null;
		if(request.getExpirationDatta() != null) {
			expirationCred = request.getExpirationDatta()
			.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
		}
		state.setAssignmentDate(expirationCred);
		stateUserServices.saveStateToUserByKeyword(state, user, KeywordsApplication.ENTITY_STATE_USER_PASS_TEMPORAL);
		
		String message = String.format(
				"\n\nBienvenido su username es: %s" + " \n\n Su password para ingresar por primera vez es: %s",
				request.getNickname(), password);

		sendMailService.sendMail("comercializadorachinitos@gmail.com", user.getEmail(), "Acceso por primera vez",
				message);
	}

	
}
