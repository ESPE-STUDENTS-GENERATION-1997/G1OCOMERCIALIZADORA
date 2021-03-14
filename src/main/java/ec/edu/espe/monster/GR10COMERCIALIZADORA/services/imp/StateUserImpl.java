package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IStateUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.DataCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.KeywordsApplication;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStateUserService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStatesServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class StateUserImpl implements IStateUserService {

	@Autowired
	private IStateUserDAO stateRepository;
	
	@Autowired
	private IStatesServices statesServices;
	
	@Override
	public void insertStateUserServ(StateUser state, UserApp user, KeywordsApplication keywordState) {
		LocalDateTime asignacion = state.getAssignmentDate();
		LocalDateTime expiracion = state.getExpirationDatta();
		String observacion = state.getObservation();
		Long codUsuario = user.getCode();
		States stateRegister = statesServices.getStateByKeyword(keywordState);
		
		Long codEstado = stateRegister.getCode();
		
		stateRepository.insertStateUser(asignacion, expiracion, observacion, codEstado,codUsuario);
		
	}

	@Override
	public void saveUserWithStateRegister(UserApp user) {
		StateUser stateUserRegister = new StateUser();
		stateUserRegister.setAssignmentDate(LocalDateTime.now());
		stateUserRegister.setExpirationDatta(null);
		stateUserRegister.setObservation(null);
		//Asiganacion estado Registrado: Debe cambiar la contraseña
		States stateRegister = statesServices.getStateByKeyword(KeywordsApplication.ENTITY_STATE_USER_REGISTER);
		stateUserRegister.setState(stateRegister);
		//Asignacion estado al usuario
		stateUserRegister.setUser(user);
		
		try {
			stateRepository.save(stateUserRegister);
		} catch (Exception e) {
			log.error("[NOT SAVE] state: {}, user-nickname: {}", stateRegister.getDescription(), user.getNickname());
			throw new DataCustomException("Ocurrio un error al registrar al usuario");
		}
	}
	


	@Override
	public void saveStateToUserByKeyword(StateUser state, UserApp user, KeywordsApplication keywordState) {
		States stateRegister = statesServices.getStateByKeyword(keywordState);
		state.setState(stateRegister);
		this.saveStateToUser(state, user);
		
	}

	@Override
	public void saveStateToUser(StateUser stateUser, UserApp user) {
		try {
			stateUser.setUser(user);
			stateRepository.save(stateUser);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}
	
}
