package br.com.grupo3.futebol.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/upload")
public class UploadArquivoController {
    
    @ApiModelProperty(value = "Caminho para salvar as imagens dos escudos dos times")
    private final String pathArquivos;

    public UploadArquivoController(@Value("${app.path.arquivos}") String pathArquivos){
        this.pathArquivos = pathArquivos;
    }

    @PostMapping("/arquivo")
    @ApiOperation(value = "Adiciona a imagem do escudo do Time", notes = "utilizar uma imagem png com somente o nome do time.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adiciona qualquer arquivo, mas se puder bota um escudo.png por favor"),
            @ApiResponse(code = 400, message = "Erro por parte do usuário ao fazer a requisição"),
            @ApiResponse(code = 404, message = "Recurso não encontrado")
    })
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file){
        var caminho = pathArquivos + file.getOriginalFilename();
        try{
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"mensagem\": \"Arquivo carregado com successo!\"}",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo!\"}",HttpStatus.OK);
        }
    }
}


