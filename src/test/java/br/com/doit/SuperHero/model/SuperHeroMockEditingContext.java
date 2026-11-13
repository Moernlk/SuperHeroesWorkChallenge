package br.com.doit.SuperHero.model;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import com.wounit.rules.MockEditingContext;


public class SuperHeroMockEditingContext extends MockEditingContext {

    private static final long serialVersionUID = 1L;

    public SuperHeroMockEditingContext(String modelNames) {
        super(modelNames);
    }

    @Override
    public NSArray objectsWithFetchSpecification(EOFetchSpecification fetchSpec, EOEditingContext ec) {
        NSMutableArray<EOEnterpriseObject> resultado = new NSMutableArray<EOEnterpriseObject>();
        EOQualifier qualifier = fetchSpec.qualifier();

        for (Object objeto : registeredObjects()) {
            EOEnterpriseObject eo = (EOEnterpriseObject) objeto;

            // primeiro descarta objetos de outra entidade 
            if (!eo.entityName().equals(fetchSpec.entityName())) {
                continue;
            }

            // só depois aplica o filtro 
            if (qualifier == null || qualifier.evaluateWithObject(eo)) {
                resultado.addObject(eo);
            }
        }

        return resultado;
    }
}