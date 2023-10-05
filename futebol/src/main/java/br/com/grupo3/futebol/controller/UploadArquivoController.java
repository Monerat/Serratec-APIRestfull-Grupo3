package br.com.grupo3.futebol.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadArquivoController {
    
    private final String pathArquivos;

    public UploadArquivoController(@Value("${app.path.arquivos}") String pathArquivos){
        this.pathArquivos = pathArquivos;
    }

    @PostMapping("/arquivo")
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file){
        
        var caminho = pathArquivos + file.getOriginalFilename();
        System.out.println(caminho+"-------------------------------------String TESTE----------------------------------");
        try{
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"mensagem\": \"Arquivo carregado com successo!\"}",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo!\"}",HttpStatus.OK);
        }
    }

    // @PostMapping("/arquivo")
    // public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file){
    //     var path = "D:\\Programação\\SerraTEC\\API Restful\\Git\\Serratec-APIRestfull-Grupo3\\futebol\\src\\main\\java\\br\\com\\grupo3\\futebol\\base\\";
    //     var caminho = path + UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());

    //     try{
    //         Files.copy(file.getInputStream(), Path.of(caminho),  StandardCopyOption.REPLACE_EXISTING);
    //         return new ResponseEntity<>("{ \"mensagem\": \"Arquivo carregado com successo!\"}",HttpStatus.OK);
    //     } catch(Exception e){
    //         return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo!\"}",HttpStatus.OK);
    //     }
    // }

    // private String extrairExtensao(String nomeArquivo){
    //     int i = nomeArquivo.lastIndexOf(".");
    //     return nomeArquivo.substring(i+1);
    // }
}

