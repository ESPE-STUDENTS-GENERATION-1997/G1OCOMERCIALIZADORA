package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IProfileUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.DataCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProfilesUserServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class ProfilesUserServicesImp implements IProfilesUserServices{
	@Autowired
	private IProfileUserDAO profileDAO;

	@Override
	public void asignamentProfilesToUser(List<SystemApp> permissons, UserApp user, ProfileUser profileUser) {
		List<ProfileUser> profiles = new ArrayList<ProfileUser>();
		for(SystemApp system: permissons) {
			ProfileUser newProfile = new ProfileUser();
			newProfile.setAssignmentDate(profileUser.getAssignmentDate());
			newProfile.setExpirationDate(profileUser.getExpirationDate());
			newProfile.setModifiedDate(profileUser.getModifiedDate());
			newProfile.setObservation(profileUser.getObservation());
			newProfile.setSystemProfile(system);
			newProfile.setUserProfile(user);
			profiles.add(newProfile);
		}
		
		try {
			profileDAO.saveAll(profiles);
		} catch (DataAccessException e) {
			log.error("[NOT SAVE] profiles: {}, user-nickname: {}", profiles.size(), user.getNickname());
			throw new DataCustomException("Ocurrio un error al registrar al usuario");
		}
	}

}
