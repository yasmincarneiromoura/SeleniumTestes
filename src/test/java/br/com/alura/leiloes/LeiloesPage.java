package br.com.alura.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;


public class LeiloesPage extends PageObject {
	private static final String URL_CADASTRO_DE_LEILOES = "http://localhost:8081/leiloes/new";
	private static final String URL_LEILOES = "http://localhost:8081/leiloes";
	   
	    public LeiloesPage(WebDriver browser) {
	        super(browser);
	        	    
	    }

		public CadastroLeilaoPage carregarFormulario() {
			this.browser.navigate().to(URL_CADASTRO_DE_LEILOES);
			return new CadastroLeilaoPage(browser);

 			
		}

		public boolean isLeilaoCadastrado(String nome, String valor, String data) {
		    WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		    WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		    WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		    WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
		    
		    return colunaNome.getText().equals(nome) 
		    		&& colunaDataAbertura.getText().equals(data) 
		    		&& colunaValorInicial.getText().equals(valor);
		}

		public boolean isPaginaAtualLeiloes() {
			return browser.getCurrentUrl().equals(URL_LEILOES);
		}

}
