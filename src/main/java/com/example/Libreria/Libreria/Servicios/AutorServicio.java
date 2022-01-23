
package com.example.Libreria.Libreria.Servicios;

import com.example.Libreria.Libreria.Entidades.Autor;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.ReactiveAuditingHandler;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    public Autor save(Autor autor) throws WebException{
        if (autor.getNombre()==null || autor.getNombre().isEmpty()) {
            throw new WebException("El nombre no puede ser nulo");
        }
        return autorRepositorio.save(autor);
    }
    
    public List<Autor> listAll(){
        return autorRepositorio.findAll();
    }
    
    public Autor findById(Autor autor){
        Optional<Autor> optional= autorRepositorio.findById(autor.getId());
        if (optional.isPresent()) {
            autor=optional.get();
        }
        return autor;
    }
}
