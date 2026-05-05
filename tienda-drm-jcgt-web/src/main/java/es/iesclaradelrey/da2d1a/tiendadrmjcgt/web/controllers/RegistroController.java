package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.UsuarioRepository;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.dtos.UsuarioRegistroDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioDto", new UsuarioRegistroDto());
        return "registro";
    }

    @PostMapping
    public String procesarRegistro(@Valid @ModelAttribute("usuarioDto") UsuarioRegistroDto dto,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(dto.getUsername());
            nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword())); // Encriptar
            nuevoUsuario.setNombre(dto.getNombre());
            nuevoUsuario.setApellidos(dto.getApellidos());
            nuevoUsuario.setEmail(dto.getEmail());
            nuevoUsuario.setTelefono(dto.getTelefono());
            nuevoUsuario.setFechaNacimiento(dto.getFechaNacimiento());
            nuevoUsuario.setActivo(true);

            usuarioRepository.save(nuevoUsuario);
            return "redirect:/login?registered";
        } catch (Exception e) {
            result.rejectValue("username", "error.usuarioDto", "El usuario o email ya existen");
            return "registro";
        }
    }
}