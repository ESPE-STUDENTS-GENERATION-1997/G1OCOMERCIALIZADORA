package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

public interface IUserAppDAO extends JpaRepository<UserApp, Long>{
	
	public Optional<UserApp> findByNickname(String nickname);
	

}
