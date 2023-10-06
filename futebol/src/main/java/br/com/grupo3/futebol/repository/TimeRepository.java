package br.com.grupo3.futebol.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.grupo3.futebol.model.Time;
import br.com.grupo3.futebol.model.exceptions.ResourceNotFound;

@Repository
public class TimeRepository {
    private List<Time> times = new ArrayList<>();
    private long ultimoID = 0;

    //Crud

    //Create
    public Time add(Time time) {
        ultimoID++;
        time.setId(ultimoID);
        time.setEscudo(verifEscudo(time.getNome() + ".png"));
        times.add(time);

        return time;
    }


    public String verifEscudo(String fileName) {
        String folderPath = "src/main/java/br/com/grupo3/futebol/base/";
        File file = new File(folderPath + fileName);
        
        if (file.exists()) {
            return file.getAbsolutePath();
        } throw new ResourceNotFound("Arquivo não encontrado na base com o nome: "+ fileName);
    
    }

    //Read
    //Read all
    public List<Time> getAll (){
        return times;
    }

    //Read id
    public Time get(long id){
        Time timeFound = null;
        for (Time time : times) {
            if (time.getId()==id){
                timeFound = time;
            }
        }
        return timeFound;
    }

    //Update
    public Time update(Time time){
        time.setEscudo(verifEscudo(time.getNome() + ".png"));
        times.removeIf(t -> t.getId()==time.getId());
        time.setEscudo(verifEscudo(time.getNome() + ".png"));
        times.add(time);

        return time;
    }

    //Delete
    public void delete(long id){
        times.removeIf(t -> t.getId()==id);
    }
}