package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

/**
 * @author dlasso
 * @apiNote Cada perfil equivale a un permiso asignado a un determinado Sistema
 */
public interface IProfilesUserServices {
	
	public void asignamentProfilesToUser(List<SystemApp> permissons, UserApp user,ProfileUser profileUser );
}
