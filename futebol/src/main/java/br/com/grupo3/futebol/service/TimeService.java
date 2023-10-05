package br.com.grupo3.futebol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo3.futebol.model.Time;
import br.com.grupo3.futebol.model.exceptions.ResourceNotFound;
import br.com.grupo3.futebol.repository.TimeRepository;

@Service
public class TimeService {
    
    @Autowired
    private TimeRepository timeRepository;

    //Create
    public Time add(Time time){
        time.isValid();
        return timeRepository.add(time);
    }

    //Read
    public List<Time> getAll(){
        return timeRepository.getAll();
    }

    public Time get(long id){
        Time time = timeRepository.get(id);
        if(time == null){
            throw new ResourceNotFound("Time não encontrado na base com o ID: "+id);
        }
        return time;
    }

    //Update
    public Time update(long id, Time time){
        Time timeFound = timeRepository.get(id);
        if(timeFound == null){
            throw new ResourceNotFound("Time não encontrado na base com o ID: "+id);
        }
        time.setId(id);
        return timeRepository.update(time);
    }

    //Delete
    public void delete(long id){
        timeRepository.delete(id);
    }
}
