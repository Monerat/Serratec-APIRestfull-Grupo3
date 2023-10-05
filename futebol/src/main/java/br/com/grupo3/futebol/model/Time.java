package br.com.grupo3.futebol.model;

import br.com.grupo3.futebol.model.exceptions.ResourceBadRequest;

public class Time {
    private long id;
    private String nome;
    private String estado;
    private String dataFundacao;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    public void isValid(){
        if(nome.equals("") || estado.equals("") || dataFundacao.equals("")){
            throw new ResourceBadRequest("Tá escrevendo errado a requisição, se vira pra acertar ai");
        }
    }
}