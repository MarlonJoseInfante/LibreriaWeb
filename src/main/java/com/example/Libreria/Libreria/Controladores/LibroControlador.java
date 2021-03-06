package com.example.Libreria.Libreria.Controladores;

import com.example.Libreria.Libreria.Entidades.Libro;
import com.example.Libreria.Libreria.Excepciones.WebException;
import com.example.Libreria.Libreria.Servicios.AutorServicio;
import com.example.Libreria.Libreria.Servicios.EditorialServicio;
import com.example.Libreria.Libreria.Servicios.LibroServicio;
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
@RequestMapping("libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/lista")
    public String listaLibros(Model model, @RequestParam(required = false) String n, Libro libro) {
        if (n != null) {
            try {
                Integer numero=Integer.parseInt(n);
                model.addAttribute("libro", libroServicio.findByAnio(numero));
            } catch (Exception e) {
                model.addAttribute("libro", libroServicio.findAllByN(n));
            }
        } else {
        model.addAttribute("libro", libroServicio.listAll());
        }
        return "libro-lista";
    }

    @GetMapping("/form")
    public String crearLibro(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Libro> optional = libroServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("libro", optional.get());
            } else {
                return "redirect:/libro/lista";
            }
        } else {
            model.addAttribute("libro",new Libro());
        }
        model.addAttribute("autor", autorServicio.listAll());
        model.addAttribute("editorial", editorialServicio.listAll());
        return "libro-form";
    }

    @PostMapping("/save")
    public String guardarLibro(Model model, @ModelAttribute Libro libro, RedirectAttributes redirectAttributes) {
        if (libroServicio.existenciaLibro(libro.getId())== true) {
             try {
            libroServicio.save(libro);
            redirectAttributes.addFlashAttribute("success", "Libro registrado con exito");

        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("libro", libro);
            model.addAttribute("autor", autorServicio.listAll());
            model.addAttribute("editorial", editorialServicio.listAll());
            return "libro-form";
        }
        } else {
            try{
            libroServicio.saveFirst(libro);
            redirectAttributes.addFlashAttribute("success", "Libro registrado con exito");

        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("libro", libro);
            model.addAttribute("autor", autorServicio.listAll());
            model.addAttribute("editorial", editorialServicio.listAll());
            return "libro-form";
        }
        }
       
        return "redirect:/libro/lista";
    }

    @GetMapping("/delete")
    public String eliminarLibro(@RequestParam(required = true) String id) {
        libroServicio.deleteById(id);
        return "redirect:/libro/lista";
    }
    
    @GetMapping("prestar")
    public String prestarLibro(@RequestParam(required = true) String id, RedirectAttributes redirectAttributes) throws WebException{
        try {
        libroServicio.librosPrestados(id);
        redirectAttributes.addFlashAttribute("success1", "Libro prestado con exito");
            
        } catch (WebException e) {
           redirectAttributes.addFlashAttribute("error1", e.getMessage());
        }
       return "redirect:/libro/lista";
    }
    
    @GetMapping("devolver")
    public String devolverLibro(@RequestParam(required = true) String id, RedirectAttributes redirectAttributes){
        try {
            libroServicio.librosDisponibles(id);
            redirectAttributes.addFlashAttribute("success2", "Libro devuelto con exito");
        } catch (WebException e) {
            redirectAttributes.addFlashAttribute("error2", e.getMessage());
        }
        return "redirect:/libro/lista";
    }
}
