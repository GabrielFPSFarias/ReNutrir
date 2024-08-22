package br.com.renutrir.main;

import br.com.renutrir.model.*;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Criar repositórios
		RepositorioContas repositorioContas = new RepositorioContas();
		RepositorioCertificado repositorioCertificado = new RepositorioCertificado();
		RepositorioDoacoes repositorioDoacoes = new RepositorioDoacoes();
		RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();

		// Criar controlador
		ControladorCadastro controladorCadastro = new ControladorCadastro();
		ControladorCertificado controladorCertificado = new ControladorCertificado();
		ControladorIntencaoDeDoacao controladorIntencaoDeDoacao = new ControladorIntencaoDeDoacao();

		// Cadastrar endereço
		Endereco endereco = new Endereco("901", "Em frente a estação do metrô", "Centro", "Jaboatão dos Guararapes", "PE");

		// Cadastrar doador
		Doador doador = new Doador();
		doador.setNome("Gabriel Felipe Farias");
		doador.setNomeUsuario("gabrielfps");
		doador.setEmail("gabrielfpsfarias@gmail.com");
		doador.setSenha("elegostadeC");
		doador.setTelefone("81986109999");
		doador.setEndereco(endereco);
		doador.setCpf("26811458569");
		doador.setDataNascimento(LocalDate.of(2005, 9, 19));
		doador.setNivel(1);
		repositorioContas.adicionarUsuario(doador);

		// Criar e cadastrar uma instituição
		Endereco enderecoInstituicao = new Endereco("456", "Sala 2", "Bairro B", "Cidade B", "RJ");
		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Instituição Alvin");
		instituicao.setNomeUsuario("instituicaoAlvin24");
		instituicao.setEmail("instituicao@hotmail.com");
		instituicao.setSenha("54321");
		instituicao.setTelefone("987654321");
		instituicao.setEndereco(enderecoInstituicao);
		instituicao.setCnpj("12345678000195");
		instituicao.setDataFundacao(LocalDate.of(2000, 1, 1));
		instituicao.setDistanciaMaximaDeColeta(20.0);

		// Criar uma doação
		Doacao doacao = new Doacao("1", "Alimentos não perecíveis", 100, doador.getEmail(), instituicao.getEmail(), doador, instituicao, LocalDate.now());
		repositorioDoacoes.adicionarDoacao(doacao);

		// Criar uma intenção de doação
		IntencaoDoacao intencaoDoacao = new IntencaoDoacao("Alimentos", "2", "Doação de alimentos para a festa", 50, LocalDate.now(), "Pendente", doador.getEmail());
		repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

		// Criar um certificado
		Certificado certificado = controladorCertificado.criarCertificado("Certificado de Participação", 10);
		repositorioCertificado.adicionarCertificado(certificado);

		// Criar um evento
		Evento evento = instituicao.criarEvento("Evento de Doação", LocalDate.now().plusDays(10), "Local do Evento");
		evento.setDoacoes(repositorioDoacoes.listarDoacoes());

		// Listar doadores
		System.out.println("Doadores: " + doador.getNome());

		// Listar doações
		System.out.println("\nDoações:");
		List<Doacao> doacoesList = repositorioDoacoes.listarDoacoes();
		doacoesList.forEach(d -> System.out.println("Doação ID: " + d.getId() + ", Descrição: " + d.getDescricao() + ", Quantidade: " + d.getQuantidade()));

		// Listar intenções de doação
		System.out.println("\nIntenções de Doação:");
		List<IntencaoDoacao> intencoesList = repositorioIntencaoDoacao.listarIntencoes();
		intencoesList.forEach(i -> System.out.println("Intenção ID: " + i.getId() + ", Descrição: " + i.getDescricao() + ", Quantidade: " + i.getQuantidade() + ", Status: " + i.getStatus()));

		// Listar certificados
		System.out.println("\nCertificados:");
		List<Certificado> certificadosList = repositorioCertificado.listarCertificados();
		certificadosList.forEach(c -> System.out.println("Certificado Descrição: " + c.getDescricao() + ", Data Emissão: " + c.getDataEmissao() + ", Quantidade de Doações: " + c.getQuantDoacoes()));

		// Exibir informações
		System.out.println("Doador cadastrado: " + doador.getNome());
		System.out.println("Certificado: " + certificado.getDescricao());
		System.out.println("Intenção de doação: " + intencaoDoacao.getDescricao());
		System.out.println("Doação realizada: " + doacao.getDescricao());
		System.out.println("Evento criado: " + evento.getNome() + " pela " + instituicao.getNome());
		System.out.println("Nível: " + doador.getNivel());
		System.out.println(instituicao.getDistanciaMaximaDeColeta() + " km");
	}
}
