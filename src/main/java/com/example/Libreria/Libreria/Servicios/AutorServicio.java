
package com.example.Libreria.Libreria.Servicios;

import com.example.Libreria.Libreria.Entidades.Autor;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.auditing.ReactiveAuditingHandler;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Lazy
    @Autowired
    private LibroServicio libroServicio;
    
    public Autor save(Autor autor) throws WebException{
        if (autor.getNombre()==null || autor.getNombre().isEmpty()) {
            throw new WebException("El nombre no puede ser nulo");
        }
        return autorRepositorio.save(autor);
    }
    
    public List<Autor> listAll(){
        return autorRepositorio.findAll();
    }
    
    public Optional<Autor> findById(String id){
        return autorRepositorio.findById(id);
    }
    
    public Autor findById(Autor autor){
        Optional<Autor> optional= autorRepositorio.findById(autor.getId());
        if (optional.isPresent()) {
            autor=optional.get();
        }
        return autor;
    }
    
    @Transactional
    public void delete(Autor autor){
       autorRepositorio.delete(autor);
    }
    
    @Transactional
    public void deleteById(String id){
        Optional<Autor> optional= autorRepositorio.findById(id);
        if (optional.isPresent()) {
            Autor autor= optional.get();
            libroServicio.deleteFieldAutor(autor);
            autorRepositorio.delete(autor);
        }
    }
}
