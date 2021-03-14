package ec.edu.espe.monster.GR10COMERCIALIZADORA.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.ISystemDAO;

@Component
public class SystemAppEditorByKeyword extends PropertyEditorSupport {

	@Autowired
	private ISystemDAO systemDAO;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(systemDAO.findByKeyword(text).orElse(null));
	}

}
