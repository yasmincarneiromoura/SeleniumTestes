package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest {

	private LoginPage paginaDeLogin;

	@BeforeEach
	public void BeforeEach() {
		this.paginaDeLogin = new LoginPage();
	}

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormulario("fulano","pass");
        paginaDeLogin.enter();
    	        
        Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preencheFormulario("invalido","123123");
    	paginaDeLogin.enter();
    	       
        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginError());
        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }
    
    @Test
    public void naoDeveriaAcessarAreaRestritaSemEstarLogado() {
    	paginaDeLogin.navegaParaPaginaRestritaDeLeiloes();
       	
    	Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
    	Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
    
    }
