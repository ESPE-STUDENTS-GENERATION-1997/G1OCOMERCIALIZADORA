package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ResourceSystem;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;

public interface IResourceSystemDAO extends JpaRepository<ResourceSystem, Long>{

	public List<ResourceSystem> findBySystem(SystemApp system);
}
