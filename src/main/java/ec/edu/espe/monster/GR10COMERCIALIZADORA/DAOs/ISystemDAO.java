package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;

public interface ISystemDAO extends JpaRepository<SystemApp, Long>{

	List<SystemApp> findByCurrent(Boolean current);
	
	Optional<SystemApp> findByKeyword(String keyword);
	
}
