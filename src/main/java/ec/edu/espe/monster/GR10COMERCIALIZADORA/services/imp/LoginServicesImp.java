package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginGetRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginPostRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ILoginServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class LoginServicesImp implements ILoginServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String loginUser(LoginGetRequest request) {

		if (request != null) {
			Model model = request.getModel();
			RedirectAttributes flash = request.getFlash();

			if (request.getPrincipal() != null) {
				flash.addFlashAttribute("info", "El usuario ya inicio sesión.");
				return "redirect:/";
			}

			if (request.getError() != null) {
				model.addAttribute("error",
						"Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.");
			}
		} else {
			// TODO: lazar un 500
		}
		return "/onboarding/login";
	}

	@Override
	public String authUser(LoginPostRequest loginRequest, Model model) {
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (BadCredentialsException e) {
			log.error("[BAD-CREDENCIAL] user: {}", loginRequest.getUsername());
		}catch (LockedException e) {
			log.error("[LOCKED ] user: {}", loginRequest.getUsername());
		} catch (DisabledException e) {
			log.error("[DISABLE USER] user: {}", loginRequest.getUsername());
		} catch (AccountExpiredException e) {
			log.error("[Expired-account]");
		} catch (CredentialsExpiredException e) {
			log.error("[Expired-credencial]");
		} catch (AuthenticationException e) {
			log.error("[ultim]   " + e.getMessage());
		}

		return "/store/home";
	}

}
