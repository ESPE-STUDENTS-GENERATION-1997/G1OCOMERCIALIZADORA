package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProfileUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ISystemDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationExceptionCodes;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.DataCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoadViewSystemsOfUserDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UpdatePermissonsDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IPermissonsServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class PermissonsServicesImp implements IPermissonsServices{
	
	@Autowired
	private IUserAppDAO userAppDAO;
	
	@Autowired
	private IProfileUserDAO profileUserDAO;
	
	@Autowired
	private ISystemDAO systemDAO;

	@Override
	public List<LoadViewSystemsOfUserDTO> loadProfilesByUsername(String username) {
		UserApp user = this.getUserByNickname(username);
		List<ProfileUser> profiles = profileUserDAO.findByUserProfile(user);
		List<LoadViewSystemsOfUserDTO> systemsOfUser = new ArrayList<LoadViewSystemsOfUserDTO>();		
		
		for(ProfileUser prof: profiles) {
			LoadViewSystemsOfUserDTO syst = new LoadViewSystemsOfUserDTO();
			syst.setAssignmentDate(prof.getAssignmentDate());
			syst.setCodeAssignamet(prof.getCode());
			SystemApp systemAssignament  = prof.getSystemProfile();
			syst.setDescriptionSystem(systemAssignament.getDescription());
			syst.setKeywordSystem(systemAssignament.getKeyword());
			syst.setNameSystem(systemAssignament.getName());
			syst.setIsSelected(true);
			systemsOfUser.add(syst);
		}
		
		List<SystemApp> allSystems = systemDAO.findAll();
		for(SystemApp sysApp: allSystems) {
			Boolean systemFound = false;
			for(LoadViewSystemsOfUserDTO sysOfUser: systemsOfUser) {
				if(sysOfUser.getKeywordSystem().equals(sysApp.getKeyword())) {
					systemFound = true;
				}
			}
			
			if(!systemFound) {
				LoadViewSystemsOfUserDTO syst = new LoadViewSystemsOfUserDTO();
				syst.setDescriptionSystem(sysApp.getDescription());
				syst.setKeywordSystem(sysApp.getKeyword());
				syst.setNameSystem(sysApp.getName());
				syst.setIsSelected(false);
				systemsOfUser.add(syst);
			}
		}
		
		return systemsOfUser;
	}

	
	private UserApp getUserByNickname(String userNickname) {
		return userAppDAO.findByNickname(userNickname).orElseThrow(()->  new AuthenticationCustomException(
				"Error en las credenciales del usuario:  " + userNickname, "/onboarding/login",
				AuthenticationExceptionCodes.USER_NOT_FOUND) );
	}


	@Override
	public String updatePermissons(UpdatePermissonsDTO request) {
		System.out.println(request.getNickname());
		UserApp user = this.getUserByNickname(request.getNickname());
		List<ProfileUser> profiles = profileUserDAO.findByUserProfile(user);
		List<ProfileUser> newProfiles = new ArrayList<ProfileUser>();
		for(String itemChecked : request.getNewPermisons() ) {
			if(this.isNumber(itemChecked)) {
				newProfiles.add(this.searchProfileByCode(itemChecked, profiles));
			}else {
				ProfileUser createdProf = this.createProfileFromSystem(itemChecked, user);
				if(createdProf != null) {
					newProfiles.add(createdProf);
				}
			}
		}
		
		try {
			profileUserDAO.deleteAll(profiles);
			profileUserDAO.saveAll(newProfiles);
		} catch (DataAccessException e) {
			log.error("[ERROR SAVE] new profiles: " + request.getNewPermisons().size() + ". user-email:  " + user.getNickname());
			throw new DataCustomException("Error actualizar los permisos del usuario", null, null, null);
		}
		
		return null;
	}
	
	private ProfileUser searchProfileByCode(String code, List<ProfileUser> profiles) {
		
		Long codeProfile = 0L;
		try {
			codeProfile = Long.parseLong(code);
		} catch (NumberFormatException e) {
			codeProfile = null;
			log.error("Error parse to Long:  " + code);
			e.printStackTrace();
		}
		
		Optional<ProfileUser> profileFound =  profileUserDAO.findById(codeProfile);
		
		
		return profileFound.isPresent()? profileFound.get() : null;
	}
	
	private ProfileUser createProfileFromSystem(String KeywordSystem, UserApp user) {
		ProfileUser newProf = new ProfileUser();
		Optional<SystemApp> systemFound = systemDAO.findByKeyword(KeywordSystem);
		if(systemFound.isPresent()) {
			newProf.setAssignmentDate(LocalDateTime.now());
			newProf.setSystemProfile(systemFound.get());
			newProf.setUserProfile(user);
		}else {
			newProf = null;
		}
		return newProf;
	}
	
	private boolean isNumber(String text) {
		return (text != null)?  Character.isDigit(text.charAt(0)) : false;
	}
}
