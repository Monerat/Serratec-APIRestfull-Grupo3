package br.com.grupo3.futebol.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    public UploadArquivoController(@Value("${app.path.arquivos}") String pathArquivos) {
        this.pathArquivos = pathArquivos;
    }

    @PostMapping("/arquivo/img")
    @ApiOperation(value = "Adiciona a imagem do escudo do Time", notes = "utilizar uma imagem png com somente o nome do time.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adiciona qualquer arquivo, mas se puder bota um escudo.png por favor"),
            @ApiResponse(code = 400, message = "Erro por parte do usuário ao fazer a requisição"),
            @ApiResponse(code = 404, message = "Recurso não encontrado")
    })
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file) {
        var caminho = pathArquivos + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"mensagem\": \"Arquivo carregado com successo!\"}", HttpStatus.OK);
        } catch (Exception e) { 
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/arquivo/base64")
    @ApiOperation(value = "Adiciona a imagem do escudo do Time", notes = "utilizar uma imagem png com somente o nome do time.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adiciona qualquer arquivo, mas se puder bota um escudo.png por favor"),
            @ApiResponse(code = 400, message = "Erro por parte do usuário ao fazer a requisição"),
            @ApiResponse(code = 404, message = "Recurso não encontrado")
    })
    public ResponseEntity<String> salvarArquivo64(@RequestParam("file") MultipartFile file) {
        try {
            // Codifica a imagem em base64 -- agradecer ao Michael por essa logica
            String encodedString = Base64.getEncoder().encodeToString(file.getBytes());

            int dotIndex = file.getOriginalFilename().lastIndexOf('.');

            String fileName = "";
            // -1 é o retorno nulo do método lastindexOf, significa que não há extensão no
            // arquivo verificado.
            
            fileName = (dotIndex == -1) ? file.getOriginalFilename()
                    : file.getOriginalFilename().substring(0, dotIndex);

            // Salva a string codificada em um arquivo .txt
            var caminhoTxt = pathArquivos + fileName + ".txt";

            // Escrever a string acima codificada em base64 no arquivo .txt
            // StandardChar converte a string em um array de bytes
            // A codificação UTF-8 é usada pra garantir que a tudo vai ser convertido
            Files.write(Path.of(caminhoTxt), encodedString.getBytes(StandardCharsets.UTF_8));

            return new ResponseEntity<>("{ \"mensagem\": \"Arquivo e texto base64 carregados com sucesso!\"}",
                    HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo e o texto base64!\"}",
                    HttpStatus.OK);
        } catch (Exception e) {
            // Handle the NullPointerException here.
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo e o texto base64!\"}",
                    HttpStatus.OK);
        }
    }
}
