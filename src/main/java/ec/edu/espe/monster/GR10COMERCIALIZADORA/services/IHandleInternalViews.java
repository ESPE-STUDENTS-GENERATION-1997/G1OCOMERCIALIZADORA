package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.MenuItemDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserReportProfilesDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

/**
 * @author dlasso
 * @version 1.0.0 // maneja_datos_vistas despues del login
 * @apiNote carga el menu de opciones según el usuario en sesión
 */
public interface IHandleInternalViews {

	public  List<MenuItemDTO>  loadMenuByPrincipalUser(String userNickname);
	
	public List<ProfileUser> loadProfileUser(String userNickname);
	
	public List<ProfileUser> loadProfileUser(UserApp user);
	
	public UserReportProfilesDTO createReportUserProfiles(String userNickname);
	
	public UserReportProfilesDTO createReportUserProfiles(UserApp user);
}
