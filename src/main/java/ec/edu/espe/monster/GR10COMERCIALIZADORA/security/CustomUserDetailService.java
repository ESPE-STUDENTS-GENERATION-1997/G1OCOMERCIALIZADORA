package ec.edu.espe.monster.GR10COMERCIALIZADORA.security;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationExceptionCodes;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import lombok.extern.slf4j.Slf4j;

/**
 * Este servicio es utilizado para buscar un usuario por el username(nickname) y
 * autenticarlo
 */
@Service
@Primary
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private IUserAppDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserApp user = userDAO.findByNickname(username).orElseThrow(() -> {
			log.error("[ERROR USER NOT FOUND]");
			return new UsernameNotFoundException("Credenciales invalidas");
		});
		this.validationStateOfUser(user.getCurrentState());

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// TODO: cargar roles del usuario
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		return new User(user.getNickname(), user.getPassword(), true, true, true, true, authorities);
	}

	private void validationStateOfUser(StateUser stateUser) {
		if (stateUser == null || stateUser.getState() == null) {
			throw new InternalAuthenticationServiceException(
					AuthenticationExceptionCodes.STATE_USER_NOT_REGISTER.getCode());
		} else if (stateUser.getState() != null) {
			States state = stateUser.getState();

			switch (state.getKeyword()) {
			case "E001":
				throw new InternalAuthenticationServiceException(
						AuthenticationExceptionCodes.STATE_USER_UPDATE_CREDENTIAL.getCode());
			case "E003":
				throw new InternalAuthenticationServiceException(
						AuthenticationExceptionCodes.STATE_USER_NO_AUTORIZED.getCode());
			case "E004":
			case "E005":
				if (stateUser.getExpirationDatta() != null) {
					if (stateUser.getExpirationDatta().isBefore(LocalDateTime.now())) {
						throw new InternalAuthenticationServiceException(
								AuthenticationExceptionCodes.STATE_USER_EXPIRED_CREDENTIAL.getCode());
					} else {
						throw new InternalAuthenticationServiceException(
								AuthenticationExceptionCodes.STATE_USER_UPDATE_TEMP_CREDENTIAL.getCode());
					}
				} else {
					throw new InternalAuthenticationServiceException(
							AuthenticationExceptionCodes.STATE_USER_EXPIRED_CREDENTIAL.getCode());
				}

			default:
				throw new InternalAuthenticationServiceException(
						AuthenticationExceptionCodes.STATE_USER_NOT_REGISTER.getCode());
			}

		}

	}

}
