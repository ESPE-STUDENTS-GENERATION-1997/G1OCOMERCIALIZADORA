package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IStatesDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.BusinessLogicException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.KeywordsApplication;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStatesServices;

@Service
@Primary
public class IStatesServicesImp implements IStatesServices{
	
	@Autowired
	private IStatesDAO statesDAO;

	@Override
	public States getStateByKeyword(KeywordsApplication keywordState) {
		States stateRegister = null;
		switch (keywordState) {
		
		case ENTITY_STATE_USER_REGISTER: case ENTITY_STATE_USER_ACTIVE: 
		case ENTITY_STATE_USER_BLOQUED: case ENTITY_STATE_USER_FORGOT_PASS:
		case ENTITY_STATE_USER_PASS_TEMPORAL:
			
			stateRegister = statesDAO
			.findByKeyword(keywordState.getCode())
			.orElseThrow(()-> new BusinessLogicException("'Estado Registro' no fue encontrado.", "Comunique a soporte el incidente"));
			
			return stateRegister;

		default:
			throw new BusinessLogicException("'Estado Registro' no fue encontrado.", "Comunique a soporte el incidente");
		}
	}

}
