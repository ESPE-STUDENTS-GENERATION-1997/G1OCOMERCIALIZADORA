package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;

public interface IStatesDAO extends JpaRepository<States, Long>{

	Optional<States> findByKeyword(String keyword);
}
