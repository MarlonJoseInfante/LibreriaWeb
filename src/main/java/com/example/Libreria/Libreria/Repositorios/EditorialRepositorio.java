package com.example.Libreria.Libreria.Repositorios;

import com.example.Libreria.Libreria.Entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
}
