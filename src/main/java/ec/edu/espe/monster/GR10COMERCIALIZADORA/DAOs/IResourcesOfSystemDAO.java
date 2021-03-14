package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ResourcesOfSystem;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;

public interface IResourcesOfSystemDAO extends JpaRepository<ResourcesOfSystem,Long> {
	
	public List<ResourcesOfSystem> findBySystem(SystemApp system);
}
