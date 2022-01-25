
package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Editorial;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.EditorialServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/form")
    public String crearEditorial(Model model, @RequestParam(required = false) String id){
        if (id!=null) {
            Optional<Editorial> optional= editorialServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("editorial", optional.get());
            } else {
                return "redirect:/editorial/lista";
            }
        } else {
        model.addAttribute("editorial", new Editorial());
        }
        return "editorial-form";
    }
    
    @PostMapping("save")
    public String guardarEditorial(Model model, @ModelAttribute Editorial editorial, RedirectAttributes redirectAttributes){
        try {
            editorialServicio.save(editorial);
            redirectAttributes.addFlashAttribute("success", "Editorial creada con exito");
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            return "editorial-form";
        }
        return "redirect:/editorial/lista";
    }
    
    @GetMapping("/lista")
    public String listaEditorial(Model model){
        model.addAttribute("editorial", editorialServicio.listAll());
        return "editorial-lista";
    }
    
    @GetMapping("/delete")
    public String eliminarEditorial(@RequestParam(required = true) String id){
        editorialServicio.deleteById(id);
        return "redirect:/editorial/lista";
    }
}
