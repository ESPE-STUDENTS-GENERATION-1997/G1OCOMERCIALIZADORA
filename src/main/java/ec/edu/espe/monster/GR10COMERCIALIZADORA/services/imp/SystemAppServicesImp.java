package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ISystemDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ISystemAppServices;

@Service
@Primary
public class SystemAppServicesImp  implements ISystemAppServices{
	
	@Autowired
	private ISystemDAO systemsDAO;

	@Override
	public List<SystemApp> getAllSystems() {
		return  systemsDAO.findByCurrent(true);
	}
	
}
