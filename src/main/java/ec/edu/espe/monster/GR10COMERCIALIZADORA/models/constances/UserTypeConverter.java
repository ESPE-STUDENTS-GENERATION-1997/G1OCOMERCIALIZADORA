package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author dlasso
 * @apiNote Convierte el tipo de usuario antes de registrar en la base y 
 * despues de extraer los datos
 */
@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

	@Override
	public String convertToDatabaseColumn(UserType attribute) {
		if(attribute == null) {
			return null;
		}
		return attribute.getCode();
	}

	@Override
	public UserType convertToEntityAttribute(String dbData) {
		if(dbData == null) {
			return null;
		}
		
		return Stream.of(UserType.values()).filter(type -> type.getCode().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
