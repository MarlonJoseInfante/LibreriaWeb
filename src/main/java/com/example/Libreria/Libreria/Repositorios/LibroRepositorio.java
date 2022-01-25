
package com.example.Libreria.Libreria.Repositorios;

import com.example.Libreria.Libreria.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
@Query("select l from Libro l where l.autor.nombre = :n")
List<Libro> findAllByAutor(@Param("n") String n);

@Query("select l from Libro l where l.editorial.nombre = :n")
List<Libro> findAllByEditorial(@Param("n") String n);
    
}
