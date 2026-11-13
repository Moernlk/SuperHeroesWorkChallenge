package br.com.doit.SuperHero.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import br.com.doit.SuperHero.model.Ability;
import br.com.doit.SuperHero.model.SuperHero;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXS;

import com.webobjects.appserver.WOActionResults;

public class SuperHerosListPage extends ERXComponent {
    private static final long serialVersionUID = 1L;


	public SuperHerosListPage(WOContext context) {
        super(context);
    }

    private NSArray<SuperHero> superHeroes;
    public SuperHero superHeroAtual;
    public SuperHero superHeroNovo;

    private boolean showForm;

    private NSArray<Ability> powers;
    public Ability powerAtual;
    public Ability powerLocal;


    public boolean temErro;
    public String erro;
    public String sucesso;

    public   EOEditingContext ec() {
    	return session().defaultEditingContext();
    }





    public NSArray<Ability> powers(){
    	if (powers == null) {
    		powers = Ability.fetchAllAbilities(ec(), ERXS.ascs(Ability.DESCRIPTION_KEY));
    	}
    	return powers;
    }



    public NSArray<SuperHero> superHeroes(){
    	powers();
    	if(superHeroes == null || superHeroes.isEmpty()) {
    		superHeroes = SuperHero.fetchAllSuperHeros(
    				ec(), ERXS.ascs(SuperHero.NAME_KEY));
    		System.out.println(superHeroes);
    	}
    	return superHeroes;
    }

    public boolean listaVazia(){
    	superHeroes();
    	powers();
    	return (superHeroes.isEmpty());
    }




    public WOActionResults delete(){
    	try {
    		ec().deleteObject(superHeroAtual);
			superHeroAtual.editingContext().saveChanges();
			superHeroes = null;
			sucesso = "The line was deleted succesfuly";

		} catch (Exception e) {
			erro = "An error happend when trying to delete the line.";
			System.out.println(e.getMessage());
			ec().revert();
			superHeroes = null;
		}
    	return null;
    }

    public WOActionResults edit() {
    	EOEditingContext ecEdicao = ERXEC.newEditingContext();
    	superHeroNovo = superHeroAtual.localInstanceIn(ecEdicao);
    	erro = null;
    	showForm = true;
    	superHeroes = null;
    	return null;
    }







    public boolean showForm() {
    	return showForm;
    }

    public WOActionResults openForm() {
    	if(showForm == false) {
    		showForm = true;
    		EOEditingContext ecNovo = ERXEC.newEditingContext();
        	superHeroNovo = ERXEOControlUtilities.createAndInsertObject(ecNovo, SuperHero.class);
            erro = null;
            sucesso = null;       	
            showForm = true;

            return null;

    	}
    	else {
    		erro = "O formulario já está aberto.";
    		return null;
    	}

    }




    public WOActionResults save(){
    	Ability power = powerAtual == null
				? null
				: Ability.localInstanceIn(superHeroNovo.editingContext(), powerAtual);
    	superHeroNovo.setAbilityRelationship(powerLocal);
    	superHeroNovo.setAbilityRelationship(power);
    	

    	if(superHeroNovo.name() == null || superHeroNovo.name().trim().isEmpty()) {
    		erro = "Fill in all the requested information";
    		temErro = true;
    		return null;
    		
    	}
    	if(superHeroNovo.ability() == null || superHeroNovo.ability().description().isEmpty()) {
    		erro = "Fill in all the requested informationr";
    		temErro = true;
    		return null;
    		
    	}
    	
    	superHeroNovo.setName(superHeroNovo.name().trim().toUpperCase());
    	
		System.out.println("nome: " + superHeroNovo.name());		
		System.out.println("power: " + superHeroNovo.ability());		
    	
		superHeroes = null;
    	showForm = false;
    	erro = null;
    	temErro = false;
    	//System.exit(0);
		superHeroNovo.editingContext().saveChanges();
    	
		sucesso = "The hero has been saved in the database.";

    	return null;
    }

    public WOActionResults cancel() {
    	showForm = false;
    	return null;
    }

   
   
    
    public boolean temSucesso() {
    	return (sucesso != null);
    }
    public String sucesso(){
    	return sucesso;
    }



    public void teste() {
    	int x = 0;
    	System.out.println( "teste " + x + " rodou");
    }
}