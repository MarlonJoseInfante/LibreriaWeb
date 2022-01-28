package com.example.Libreria.Libreria.Repositorios;

import com.example.Libreria.Libreria.Entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
@Query("select e from Editorial e where e.nombre LIKE :n")
List<Editorial> findAllByN(@Param("n") String n);
}
