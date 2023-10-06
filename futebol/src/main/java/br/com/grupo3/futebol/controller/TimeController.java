package br.com.grupo3.futebol.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo3.futebol.model.Time;
import br.com.grupo3.futebol.service.TimeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/times")
public class TimeController {
    
    @Autowired
    private TimeService timeService;

    //Create
    @PostMapping
    @ApiOperation(value="Adiciona o Time", notes="O requerimento para adicionar um time é efetuar o upload do escudo deve ser feito previamente, utilizando uma imagem png com somente o nome do time.")
    @ApiResponses(value= {
        @ApiResponse(code=200, message="Adiciona um time"),
        @ApiResponse(code=400, message="Erro por parte do usuário ao fazer a requisição"),
        @ApiResponse(code=404, message="Recurso não encontrado")
    })
    public ResponseEntity<Time> add(@RequestBody Time time){
        Time timeAdd = timeService.add(time);
        return ResponseEntity.created(URI.create("/api/times/"+timeAdd.getId())).build();
        // return new ResponseEntity<>(timeAdd, HttpStatus.CREATED);

    }

    //Read
    @GetMapping
    @ApiOperation(value="Lista todos os times", notes="Listagem de todos os times")
    @ApiResponses(value= {
        @ApiResponse(code=200, message="Retorna todos os times"),
        @ApiResponse(code=404, message="Recurso não encontrado")
    })
    public ResponseEntity<List<Time>> getAll(){
        return ResponseEntity.ok(timeService.getAll());
    }

    @GetMapping ("/{id}")
    @ApiOperation(value="Lista o time com o ID correspondente a requisição", notes="")
    @ApiResponses(value= {
        @ApiResponse(code=200, message="Retorna todos os times"),
        @ApiResponse(code=404, message="Recurso não encontrado")
    })
    public ResponseEntity<Time> get(@PathVariable long id){
        return ResponseEntity.ok(timeService.get(id));
    }

    //Update
    @PutMapping ("/{id}")
    @ApiOperation(value="Atualiza as informações do time com o ID correspondente a requisição", notes="")
    @ApiResponses(value= {
        @ApiResponse(code=200, message="Atualização feita com sucesso"),
        @ApiResponse(code=400, message="Erro por parte do usuário ao fazer a requisição"),
        @ApiResponse(code=404, message="Recurso não encontrado")
    })
    public ResponseEntity<Time> update(@PathVariable long id, @RequestBody Time time){
        return ResponseEntity.ok(timeService.update(id, time));
    }

    //Delete
    @DeleteMapping ("/{id}")
    @ApiOperation(value="Deleta o time com o ID correspondente a requisição", notes="")
    @ApiResponses(value= {
        @ApiResponse(code=204, message="No content"),
        
    })
    public ResponseEntity<?> delete(@PathVariable long id){
        timeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
