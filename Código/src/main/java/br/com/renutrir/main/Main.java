package br.com.renutrir.main;

import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.*;
import br.com.renutrir.model.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

       Doador d = new Doador();
       Instituicao i = new Instituicao();

    	d.setEmail("doador@email.com");
    	i.setEmail("inst@email.com");
    	d.setNome("Doador");
    	i.setNome("Inst");
    	d.setNomeUsuario("Doador01");
    	i.setNomeUsuario("Inst01");
    	d.setSenha("password");
    	i.setSenha("senha");
    	d.setTelefone("12345678");
    	i.setTelefone("87654321");

    	Certificado c = new Certificado();
    	c.setDescricao("Doador bacana");
    	c.setDataEmissao(LocalDate.of(2020, 12, 10));
    	d.setCertificado(c);
    	d.setDataNascimento(LocalDate.of(2000, 8, 1));
    }
}
