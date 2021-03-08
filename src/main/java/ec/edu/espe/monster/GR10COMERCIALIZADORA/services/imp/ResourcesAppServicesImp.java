package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IResourceSystemDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Resource;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IResourcesAppServices;

@Service
@Primary
public class ResourcesAppServicesImp implements IResourcesAppServices{
	
	@Autowired
	private IResourceSystemDAO resourceSystemDAO;

	@Override
	public List<Resource> getAll() {
		return resourceSystemDAO.findAll();
	}

}
