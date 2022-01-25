
package com.example.Libreria.Libreria.Servicios;

import com.example.Libreria.Libreria.Entidades.Editorial;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Lazy
    @Autowired
    private LibroServicio libroServicio;
    
    @Transactional
    public Editorial save(Editorial editorial) throws WebException{
        if (editorial.getNombre()== null || editorial.getNombre().isEmpty()) {
            throw new WebException("En nombre de la editorial no puede ser nulo o estar vacio");
        }
        return editorialRepositorio.save(editorial);
    }
    
    public List<Editorial> listAll(){
        return editorialRepositorio.findAll();
    }
    
    public Optional<Editorial> findById(String id){
        return editorialRepositorio.findById(id);
    }
    
    public Editorial findById(Editorial editorial){
        Optional<Editorial> optional= editorialRepositorio.findById(editorial.getId());
        if (optional.isPresent()) {
            editorial= optional.get();
        }
        return editorial;
    }
    
    @Transactional
    public void delete(Editorial editorial){
        editorialRepositorio.delete(editorial);
    }
    
    @Transactional
    public void deleteById(String id){
        Optional<Editorial> optional=editorialRepositorio.findById(id);
        if (optional.isPresent()) {
            Editorial editorial= optional.get();
            libroServicio.deleteFieldEditorial(editorial);
            editorialRepositorio.delete(editorial);
        }
    }
}
