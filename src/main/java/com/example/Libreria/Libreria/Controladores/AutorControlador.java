
package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Autor;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/lista")
    public String listaAutores(Model model){
        model.addAttribute("autores", autorServicio.listAll());
        return "autor-lista";
    }
    
    @GetMapping("/form")
    public String crearAutor(Model model){
        model.addAttribute("autor", new Autor());
        return "autor-form";
    }
    
    @PostMapping("save")
    public String guardarAutor(Model model, @ModelAttribute Autor autor) {
        
        try {
             autorServicio.save(autor);
        } catch (WebException e) {
            e.getMessage();
        }
       return "redirect:/autor/lista";
    }
}
