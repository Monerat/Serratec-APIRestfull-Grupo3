package br.com.grupo3.futebol.model;

import br.com.grupo3.futebol.model.exceptions.ResourceBadRequest;
import io.swagger.annotations.ApiModelProperty;

public class Time {

    @ApiModelProperty(value="Identificador unico do time, autoincremental")
    private long id;
    @ApiModelProperty(value="Nome do time", required = true)
    private String nome;
    @ApiModelProperty(value="Estado de origem do time", required = true)
    private String estado;
    @ApiModelProperty(value="Data de fundação do time", required = true)
    private String dataFundacao;
    @ApiModelProperty(value="URI do escudo do time em formato .png", required = true)
    private String escudo;
    @ApiModelProperty(value = "URI da imagem do escudo do time em formato base64")
    private String base64img;
    
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
    
    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public void isValid(){
        if(nome.equals("") || estado.equals("") || dataFundacao.equals("")){
            throw new ResourceBadRequest("Tá escrevendo errado a requisição, se vira pra acertar ai");
        }
    }
    public String getBase64img() {
        return base64img;
    }

    public void setBase64img(String base64img) {
        this.base64img = base64img;
    }
    
}