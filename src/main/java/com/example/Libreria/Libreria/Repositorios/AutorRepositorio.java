
package com.example.Libreria.Libreria.Repositorios;

import com.example.Libreria.Libreria.Entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    /*@Query("select a from Autor where a.Autor LIKE :b ")
    List<Autor> findAll(@Param("b") String b);*/
}
