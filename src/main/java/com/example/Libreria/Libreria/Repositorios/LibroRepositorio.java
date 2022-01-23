
package com.example.Libreria.Libreria.Repositorios;

import com.example.Libreria.Libreria.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
    
}
