
package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Editorial;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/form")
    public String crearAutor(Model model){
        model.addAttribute("editorial", new Editorial());
        return "editorial-form";
    }
    
    @PostMapping("save")
    public String guardarEditorial(Model model, @ModelAttribute Editorial editorial){
        try {
            editorialServicio.save(editorial);
        } catch (WebException e) {
            e.getMessage();
        }
        return "redirect:/editorial/lista";
    }
    
    @GetMapping("/lista")
    public String listaEditorial(Model model){
        model.addAttribute("editorial", editorialServicio.listAll());
        return "editorial-lista";
    }
}
