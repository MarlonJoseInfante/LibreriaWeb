
package com.example.Libreria.Libreria.Servicios;

import com.example.Libreria.Libreria.Entidades.Libro;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Repositorios.LibroRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
 
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @Transactional
    public Libro save(Libro libro) throws WebException{
        
        if(libro.getTitulo()==null|| libro.getTitulo().isEmpty()){
            throw  new WebException("El titulo del libro no puede ser nulo o estar vacio");
        }
        if(libro.getAutor()==null){
            throw  new WebException("El autor del libro no puede ser nulo");
        }else{
            libro.setAutor(autorServicio.findById(libro.getAutor()));
        }
        if(libro.getEditorial()==null){
            throw  new WebException("La editorial del libro no puede ser nulo");
        }else{
            libro.setEditorial(editorialServicio.findById(libro.getEditorial()));
        }
        if(libro.getISBN()==null){
            throw  new WebException("El ISBN del libro no puede ser nulo");
        }
        if(libro.getAnio()==null){
            throw  new WebException("El año de publicacion del libro no puede ser nulo");
        }
        if (libro.getTitulo()!=null && !libro.getTitulo().isEmpty()&& libro.getAutor()!=null && libro.getEditorial()!=null && libro.getISBN()!=null && libro.getAnio()!=null) {
            libro.setAlta(Boolean.TRUE);
        }
        return libroRepositorio.save(libro);
    }
    
    public List<Libro> listAll(){
        return libroRepositorio.findAll();
    }
}