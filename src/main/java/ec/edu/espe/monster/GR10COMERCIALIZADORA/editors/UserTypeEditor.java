package ec.edu.espe.monster.GR10COMERCIALIZADORA.editors;

import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.UserType;

@Component
public class UserTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.isBlank() || text.isEmpty()) {
			this.setValue(null);
		} else {

			this.setValue(
					Stream.of(UserType.values()).filter(type -> type.getCode().equals(text)).findFirst().orElse(null));
		}

	}

}
