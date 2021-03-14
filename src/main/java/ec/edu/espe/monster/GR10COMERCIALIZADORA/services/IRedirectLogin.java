package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;

public interface IRedirectLogin {

	public String redirectByUserType(UserType userType);
}
