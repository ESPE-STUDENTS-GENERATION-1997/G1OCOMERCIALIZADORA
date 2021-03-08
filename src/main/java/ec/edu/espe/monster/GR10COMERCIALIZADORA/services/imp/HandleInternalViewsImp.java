package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProfileUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IResourcesOfSystemDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationExceptionCodes;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.BusinessLogicException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.MenuItemDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserReportProfilesDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Resource;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ResourcesOfSystem;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IHandleInternalViews;

/**
 * @author dlasso
 * 
 */
@Service
@Primary
public class HandleInternalViewsImp implements IHandleInternalViews{
	
	@Autowired
	private IUserAppDAO userDAO;
	
	@Autowired
	private IProfileUserDAO iprofileUserDAO;
	
	@Autowired
	private IResourcesOfSystemDAO resourcesOfSystemDAO; 

	@Override
	public List<MenuItemDTO> loadMenuByPrincipalUser(String userNickname) {
		
		UserApp user = this.getUserByNickname(userNickname);
		
		List<ProfileUser> profiles = this.loadProfileUser(user);
		
		return this.buildMenu(profiles);
		
	}
	
	private List<MenuItemDTO> buildMenu(List<ProfileUser> profiles){
		List<MenuItemDTO> menu = new ArrayList<MenuItemDTO>();

		for (ProfileUser profile : profiles) {
			
			SystemApp  systemProfile =  profile.getSystemProfile();
			
			if(systemProfile == null) {
				throw new BusinessLogicException("El perfil asignado no tiene ningun Sistema asociado.", "Notifique a soporte el inconveniente.");
			}
			
			MenuItemDTO item = new MenuItemDTO();
			item.setIsMenuCategory(true);
			item.setKeyword(systemProfile.getKeyword());
			item.setName(systemProfile.getName());
			item.setUrl(null);
			item.setSubItems(buildSubMenu(systemProfile));
			
			menu.add(item);
		}
		return menu;
	}
	
	private List<MenuItemDTO> buildSubMenu(SystemApp  systemProfile){
		List<MenuItemDTO> subMenu = new ArrayList<MenuItemDTO>();
		
		List<ResourcesOfSystem> resourcesOfSystem =  resourcesOfSystemDAO.findBySystem(systemProfile);
		
		for(ResourcesOfSystem resourceOfSys: resourcesOfSystem ) {
			MenuItemDTO item = new MenuItemDTO();
			Resource resource = resourceOfSys.getResource();
			item.setIsMenuCategory(false);
			item.setName(resource.getNameMenu());
			item.setUrl(resource.getUrl());
			subMenu.add(item);
		}
		
		return subMenu;
	}

	@Override
	public List<ProfileUser> loadProfileUser(String userNickname) {
		return this.loadProfileUser(this.getUserByNickname(userNickname));
	}

	@Override
	public List<ProfileUser> loadProfileUser(UserApp user) {
		return  iprofileUserDAO.findByUserProfile(user);
	}

	@Override
	public UserReportProfilesDTO createReportUserProfiles(String userNickname) {
		return this.createReportUserProfiles(this.getUserByNickname(userNickname));
	}
	
	@Override
	public UserReportProfilesDTO createReportUserProfiles(UserApp user) {
		List<ProfileUser> profiles = this.loadProfileUser(user);
		UserReportProfilesDTO report = new UserReportProfilesDTO();
		
		LocalDateTime timeView = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		report.setObservations(String.format("Ingreso al Sistema: %s", timeView.format(formatter) ));
		
		List<String> profilesDescription = new ArrayList<String>();
		for(ProfileUser profile: profiles) {
			String description = String.format("Sistma: %s, fecha de asignación: %s", profile.getSystemProfile().getName(), profile.getAssignmentDate().format(formatter) );
			description = description + ((profile.getModifiedDate() != null)? ".<br/> Fecha de Modificación: "+ profile.getModifiedDate().format(formatter) : "");
			description = description + ((profile.getExpirationDate() != null)? ".<br/> Fecha de Expiración: "+ profile.getExpirationDate().format(formatter) : "");
			profilesDescription.add(description);
		}
		report.setProfiles(profilesDescription);
		
		return report;
	}
	
	private UserApp getUserByNickname(String userNickname) {
		return userDAO.findByNickname(userNickname).orElseThrow(()->  new AuthenticationCustomException(
				"Error en las credenciales del usuario:  " + userNickname, "/onboarding/login",
				AuthenticationExceptionCodes.USER_NOT_FOUND) );
	}

}
