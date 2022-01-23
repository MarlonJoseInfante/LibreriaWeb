
package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Libro;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.AutorServicio;
import com.example.Libreria.Libreria.Servicios.EditorialServicio;
import com.example.Libreria.Libreria.Servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/lista")
    public String listaLibros(Model model){
        model.addAttribute("libro", libroServicio.listAll());
        return "libro-lista";
    }
    
    @GetMapping("/form")
    public String crearLibro(Model model){
        model.addAttribute("libro", new Libro());
        model.addAttribute("autor", autorServicio.listAll());
        model.addAttribute("editorial", editorialServicio.listAll());
        return "libro-form";
    }
    
    @PostMapping("/save")
    public String guardarLibro(Model model, @ModelAttribute Libro libro){
        try {
            libroServicio.save(libro);
        } catch (WebException e) {
            e.getMessage();
        }
        return "redirect:/libro/lista";
    }
}
