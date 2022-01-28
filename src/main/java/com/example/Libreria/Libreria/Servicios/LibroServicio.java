package com.example.Libreria.Libreria.Servicios;

import com.example.Libreria.Libreria.Entidades.Autor;
import com.example.Libreria.Libreria.Entidades.Editorial;
import com.example.Libreria.Libreria.Entidades.Libro;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import jdk.nashorn.internal.objects.NativeJSAdapter;
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
    public Libro save(Libro libro) throws WebException {

        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new WebException("El titulo del libro no puede ser nulo o estar vacio");
        }
        if (libro.getAutor() == null) {
            throw new WebException("El autor del libro no puede ser nulo");
        } else {
            libro.setAutor(autorServicio.findById(libro.getAutor()));
        }
        if (libro.getEditorial() == null) {
            throw new WebException("La editorial del libro no puede ser nulo");
        } else {
            libro.setEditorial(editorialServicio.findById(libro.getEditorial()));
        }
        if (libro.getISBN() == null) {
            throw new WebException("El ISBN del libro no puede ser nulo");
        }
        if (libro.getAnio() == null) {
            throw new WebException("El año de publicacion del libro no puede ser nulo");
        }
        if (libro.getEjemplares() == null || libro.getEjemplares() < 0) {
            throw new WebException("La cantidad de libros no debe ser nula ni negativa");
        }
        if (libro.getTitulo() != null && !libro.getTitulo().isEmpty() && libro.getAutor() != null && libro.getEditorial() != null && libro.getISBN() != null && libro.getAnio() != null) {
            libro.setAlta(Boolean.TRUE);
        }
        return libroRepositorio.save(libro);
    }

    public List<Libro> listAll() {
        return libroRepositorio.findAll();
    }

    public List<Libro> findAllByAutor(String nombre) {
        return libroRepositorio.findAllByAutor(nombre);
    }

    public List<Libro> findAllByEditorial(String nombre) {
        return libroRepositorio.findAllByEditorial(nombre);
    }
 
    public Optional<Libro> findById(String id) {
        return libroRepositorio.findById(id);
    }

    public List<Libro> findAllByN(String n){
        return libroRepositorio.findAllByN("%"+n+"%");
    }
    public List<Libro> findByAnio(Integer n){
        return libroRepositorio.findAllByAnio(n);
    }
    @Transactional
    public void delete(Libro libro) {
        libroRepositorio.delete(libro);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Libro> optional = libroRepositorio.findById(id);
        if (optional.isPresent()) {
            libroRepositorio.delete(optional.get());
        }
    }

    @Transactional
    public void deleteFieldAutor(Autor autor) {
        List<Libro> libros = findAllByAutor(autor.getNombre());
        for (Libro libro : libros) {
            libro.setAutor(null);
        }
        libroRepositorio.saveAll(libros);
    }

    @Transactional
    public void deleteFieldEditorial(Editorial editorial) {
        List<Libro> libros = findAllByEditorial(editorial.getNombre());
        for (Libro libro : libros) {
            libro.setEditorial(null);
        }
        libroRepositorio.saveAll(libros);
    }

//    public Integer librosDisponibles(Libro libro){
//        Integer disponibles= libro.getEjemplares();
//        
//        
//    }
//    
//    public Integer librosPrestados(Libro libro){
//        Integer prestados= librosDisponibles(libro);
//        prestados--;
//        libro.setEjemplaresPrestados(prestados);
//        return libro.getEjemplaresPrestados();
//    }
//    
//    public Integer solicitarLibro(Libro libro){
//            
//        Integer disponible= libro.getEjemplares();
//        
//    }
//    
//    public void cantidadLibrosDisponibles(String n){
//        if () {
//            
//        }
//    }
}
