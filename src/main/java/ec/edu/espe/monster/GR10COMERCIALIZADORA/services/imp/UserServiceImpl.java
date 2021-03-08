package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IAddressHomeDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.DataCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.UserRegisterDTO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.AddressHome;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.SystemApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IProfilesUserServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStateUserService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserAppDAO userRepository;

	@Autowired
	private IAddressHomeDAO addressHomeDAO;

	@Autowired
	private IStateUserService stateUserService;

	@Autowired
	private SendMailServiceImpl sendMailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IProfilesUserServices profilesUserServices;

	@Override
	public List<UserApp> listUsers() {
		return (List<UserApp>) userRepository.findAll();
	}

	@Override
	public void newUser(UserApp user) {
		userRepository.save(user);
	}

	public UserApp findByCedula(String cedula) {
		return userRepository.findByDocument(cedula);
	}

	@Override
	public UserApp findUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String generatePassword() {
		int leftLimit = 48; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	@Override
	public boolean saveUserWithPermisson(UserRegisterDTO request) {
		AddressHome address = this.registerAddressHome(request);

		String password = this.generatePassword();

		UserApp newUser = this.registerNewUser(request, address, password);

		stateUserService.saveUserWithStateRegister(newUser);

		this.savePermissonsToUserRegister(newUser, request.getPermissons());

		String message = String.format(
				"\n\nBienvenido su username es: %s" + " \n\n Su password para ingresar por primera vez es: %s",
				request.getNickname(), password);

		sendMailService.sendMail("comercializadorachinitos@gmail.com", newUser.getEmail(), "Acceso por primera vez",
				message);

		return false;
	}

	private AddressHome registerAddressHome(UserRegisterDTO request) {
		AddressHome address = new AddressHome();
		address.setCity(request.getCity());
		address.setProvince(request.getCity());
		address.setNeighborhood(request.getNeighborhood());
		address.setMainStreet(request.getMainStreet());
		address.setSecondaryStreet(request.getSecondaryStreet());
		address.setHouseNumbering(request.getHouseNumbering());
		try {
			address = addressHomeDAO.save(address);
		} catch (DataAccessException e) {
			log.error("[ERROR SAVE] address-home: " + request.getCity() + " / " + request.getMainStreet());
			throw new DataCustomException("Error al registrar el domicilio del usuario", null, null, null);
		}
		return address;
	}

	private UserApp registerNewUser(UserRegisterDTO request, AddressHome addressHome, String password) {
		UserApp newUser = new UserApp();

		// Domicilio Usuario
		newUser.setAddressHome(addressHome);

		// Cuenta de Usuario
		newUser.setNickname(request.getNickname());
		newUser.setPassword(passwordEncoder.encode(password));

		// Datos personales
		newUser.setNames(request.getNames());
		newUser.setLastnames(request.getLastnames());
		newUser.setEmail(request.getEmail());
		newUser.setPhone(request.getPhone());
		newUser.setNumDocument(request.getNumDocument());
		newUser.setDisability(request.getDisability());
		newUser.setDateOfBirth(LocalDateTime.now());
		newUser.setDateCreated(LocalDateTime.now());
		newUser.setType((request.getType() != null) ? request.getType() : UserType.EXTERNO_CLIENTE);

		try {
			newUser = userRepository.save(newUser);
		} catch (DataAccessException e) {
			log.error("[NOT SAVE] user: {}  {}, email: {}", request.getNames(), request.getLastnames(),
					request.getEmail());
			throw new DataCustomException("Ocurrio un error al registrar al usuario");
		}
		return newUser;
	}

	private void savePermissonsToUserRegister(UserApp user, List<SystemApp> permissons) {
		ProfileUser profileUser = new ProfileUser();
		profileUser.setAssignmentDate(LocalDateTime.now());
		profileUser.setExpirationDate(null);
		profileUser.setObservation(null);
		profilesUserServices.asignamentProfilesToUser(permissons, user, profileUser);
	}

}
