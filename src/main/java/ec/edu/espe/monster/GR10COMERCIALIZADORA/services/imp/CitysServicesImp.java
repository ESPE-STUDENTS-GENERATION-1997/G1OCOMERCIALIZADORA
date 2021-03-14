package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ICityDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.City;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ICitysServices;

@Service
@Primary
public class CitysServicesImp implements ICitysServices {
	
	@Autowired
	private ICityDAO cityDAO;

	@Override
	public List<City> getAllCitys() {
		return cityDAO.findAll();
	}

}
