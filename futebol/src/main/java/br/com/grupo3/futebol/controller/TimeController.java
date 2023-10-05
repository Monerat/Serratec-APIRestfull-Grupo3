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

@RestController
@RequestMapping("/api/times")
public class TimeController {
    
    @Autowired
    private TimeService timeService;

    //Create
    @PostMapping
    public ResponseEntity<Time> add(@RequestBody Time time){
        Time timeAdd = timeService.add(time);
        return ResponseEntity.created(URI.create("/api/times/"+timeAdd.getId())).build();
        // return new ResponseEntity<>(timeAdd, HttpStatus.CREATED);

    }

    //Read
    @GetMapping
    public ResponseEntity<List<Time>> getAll(){
        return ResponseEntity.ok(timeService.getAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Time> get(@PathVariable long id){
        return ResponseEntity.ok(timeService.get(id));
    }

    //Update
    @PutMapping ("/{id}")
    public ResponseEntity<Time> update(@PathVariable long id, @RequestBody Time time){
        return ResponseEntity.ok(timeService.update(id, time));
    }

    //Delete
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        timeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
