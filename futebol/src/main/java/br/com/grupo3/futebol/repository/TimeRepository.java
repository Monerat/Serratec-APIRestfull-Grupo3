package br.com.grupo3.futebol.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.grupo3.futebol.model.Time;

@Repository
public class TimeRepository {
    private List<Time> times = new ArrayList<>();
    private long ultimoID = 0;

    //Crud

    //Create
    public Time add(Time time){
        ultimoID++;
        time.setId(ultimoID);
        times.add(time);

        return time;
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
        times.removeIf(t -> t.getId()==time.getId());
        times.add(time);

        return time;
    }

    //Delete
    public void delete(long id){
        times.removeIf(t -> t.getId()==id);
    }
}