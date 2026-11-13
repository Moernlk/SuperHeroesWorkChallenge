package br.com.doit.SuperHero.model;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXFetchSpecification;
import er.extensions.eof.ERXQ;

public class SuperHero extends _SuperHero {
    private static final long serialVersionUID = 1L;

  // private static Logger log = Logger.getLogger(SuperHero.class);
    @Override
    public void validateForSave() throws NSValidation.ValidationException {
        super.validateForSave();

        NSArray<SuperHero> mesmoNome = new ERXFetchSpecification<SuperHero>(
                ENTITY_NAME, ERXQ.equals(NAME_KEY, name()), null)
                .fetchObjects(editingContext());

        for (SuperHero outro : mesmoNome) {
            if (!outro.equals(this)) {
                throw new NSValidation.ValidationException("Duplicated");
            }
        }
    }
}
