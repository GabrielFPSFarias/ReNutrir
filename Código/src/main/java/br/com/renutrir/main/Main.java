package br.com.renutrir.main;

import br.com.renutrir.model.*;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.ControladorCadastro;
import br.com.renutrir.servicos.ControladorCertificado;
import br.com.renutrir.servicos.ControladorIntencaoDeDoacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

		// Criar certificado
		Certificado certificado = controladorCertificado.criarCertificado("Certificado de Agradecimento", 5);
		repositorioCertificado.adicionarCertificado(certificado);

		// Criar intenção de doação
		IntencaoDoacao intencao = new IntencaoDoacao("Alimento", "1", "Doação de alimentos", 50, LocalDate.now(), "Pendente", doador.getEmail());
		controladorIntencaoDeDoacao.adicionarIntencao(intencao);

		// Criar doação
		Doacao doacao = new Doacao("1", "Doação de alimentos", 50, doador.getEmail(), "InstituicaoX", doador, null, LocalDate.now());
		repositorioDoacoes.adicionarDoacao(doacao);

		// Criar evento
		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Instituição AlvinBR");
		instituicao.setNomeUsuario("instituicao1alvin");
		instituicao.setEmail("instituicaoalvin@hotmail.com");
		instituicao.setSenha("senha5678");
		instituicao.setTelefone("1187654321");
		instituicao.setEndereco(endereco);
		instituicao.setCnpj("12345678000195");
		instituicao.setDataFundacao(LocalDate.of(2000, 1, 1));
		instituicao.setDistanciaMaximaDeColeta(50.0);
		Evento evento = instituicao.criarEvento("Evento de Doação", LocalDate.now(), "Rua das Flores, 123");

		// Exibir informações
		System.out.println("Doador cadastrado: " + doador.getNome());
		System.out.println("Certificado: " + certificado.getDescricao());
		System.out.println("Intenção de Doação: " + intencao.getDescricao());
		System.out.println("Doação realizada: " + doacao.getDescricao());
		System.out.println("Evento criado: " + evento.getNome());
	}
}
