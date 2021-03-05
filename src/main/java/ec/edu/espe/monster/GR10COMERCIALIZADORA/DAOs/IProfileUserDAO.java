package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

@Repository
public interface IProfileUserDAO extends JpaRepository<ProfileUser, Long>{

	public List<ProfileUser> findByUserProfile(UserApp userProfile);
}
