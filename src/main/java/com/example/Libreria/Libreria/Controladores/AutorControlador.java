package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Autor;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.AutorServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/lista")
    public String listaAutores(Model model) {

        model.addAttribute("autor", autorServicio.listAll());
        return "autor-lista";
    }

    @GetMapping("/form")
    public String crearAutor(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Autor> optional = autorServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("autor", optional.get());
            } else {
                return "redirect:/autor/lista";
            }
        } else {
            model.addAttribute("autor", new Autor());
        }
        return "autor-form";
    }

    @PostMapping("save")
    public String guardarAutor(Model model, @ModelAttribute Autor autor, RedirectAttributes redirectAttributes) {

        try {
            autorServicio.save(autor);
            redirectAttributes.addFlashAttribute("success", "Autor guardado con exito");
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            return  "autor-form";
        }
        return "redirect:/autor/lista";
    }

    @GetMapping("delete")
    public String eliminarAutor(@RequestParam(required = true) String id) {
        autorServicio.deleteById(id);
        return "redirect:/autor/lista";
    }
}
