package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;


import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.KeywordsApplication;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

public interface IStateUserService {
	public void insertStateUserServ (StateUser state, UserApp user, KeywordsApplication keywordState);
	
	public void saveUserWithStateRegister(UserApp user);
	
	public void saveStateToUserByKeyword(StateUser state, UserApp user, KeywordsApplication keywordState);
	
	public void saveStateToUser(StateUser stateUser, UserApp user);
}
