package br.com.doit.SuperHero.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;


public class Main extends ERXComponent {

	private static final long serialVersionUID = 1L;
	private int x = 0;
	public Main(WOContext context) {
        super(context);
    }

    public WOActionResults abrirPower() {
    	return pageWithName(PowerListPage.class);
    }
    
    public WOActionResults abrirSuperHeros() {
    	return pageWithName(SuperHerosListPage.class);
    }
    public ERXComponent openPowers() {
        // Cria e retorna a página PowerListPage
        PowerListPage nextPage = pageWithName(PowerListPage.class);
        return nextPage;
    }
    
    public ERXComponent openSuperHeros() {
    	SuperHerosListPage nextPage = pageWithName(SuperHerosListPage.class);
    	return nextPage;
    }
    
    /*public boolean temMensagem() {
    	return mensagem != null && !mensagem.isEmpty();
    }
    
    public String mensagem() {
    	return mensagem;
    }
    
    public void setMensagem(String mensagem) {
    	this.mensagem = mensagem;
    }*/

}