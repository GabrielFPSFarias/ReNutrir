package br.com.renutrir.sessao;

import br.com.renutrir.model.Doador;

public class SessaoDoador {
    private static SessaoDoador instancia;
    private Doador doadorLogado;

    private SessaoDoador() {}

    public static SessaoDoador getInstancia() {
        if (instancia == null) {
            instancia = new SessaoDoador();
        }
        return instancia;
    }

    public void setDoadorLogado(Doador doador) {
        this.doadorLogado = doador;
    }

    public Doador getDoadorLogado() {
        return doadorLogado;
    }

    public void limparSessao() {
        doadorLogado = null;
    }
}
