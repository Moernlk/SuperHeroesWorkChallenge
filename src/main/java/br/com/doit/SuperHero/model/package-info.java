package br.com.doit.SuperHero.model;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSValidation;
import com.webobjects.foundation.NSValidation.ValidationException;

/*public class SuperHero extends _SuperHero {
	private static Logger log = Logger.getLogger(SuperHero.class);
	
	@Override
	public void validateForSave() throws ValidationException {
		super.validateForSave();
		if(name() == null || name().isEmpty()) {
			throw new NSValidation.ValidationException("Insira o super heroi");
		}
		if(name() == null || name().isEmpty() ) {
			throw new NSValidation.ValidationException("Insira o nível de força");
		}
		
	}
}*/