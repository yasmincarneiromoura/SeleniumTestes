package br.com.alura.leiloes;

import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;

	@BeforeEach
	public void beforeEach() {
	LoginPage paginaDeLogin = new LoginPage();
	paginaDeLogin.preencheFormulario("fulano","pass");
	this.paginaDeLeiloes = paginaDeLogin.enter();        
	this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
	
	}
	
    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
    }
    
    @Test
    public void deveriaCadastrarLeilao() { 
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do Dia"+hoje;
		String valor = "500.00";
				
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
	    Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	    
    }
    
    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("","","");

        Assert.assertFalse(this.paginaDeCadastro.isPaginaAtualCadastro());
        Assert.assertTrue(this.paginaDeLeiloes.isPaginaAtualLeiloes());
        Assert.assertTrue(this.paginaDeCadastro.isPaginaDeValidacaoVisiveis());
    }
    
}


