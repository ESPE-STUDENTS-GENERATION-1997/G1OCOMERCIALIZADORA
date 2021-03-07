package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;


import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserRegisterDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

public interface IUserService {
	
	public boolean saveUserWithPermisson(UserRegisterDTO request);
	
	public List<UserApp> listUsers();
	
	public void newUser (UserApp user);
	
	public UserApp findByCedula(String cedula);
	
	public UserApp findUserById(Long id);
	
	public void deleteUser (Long id);
	
	
	public String generatePassword();
}
