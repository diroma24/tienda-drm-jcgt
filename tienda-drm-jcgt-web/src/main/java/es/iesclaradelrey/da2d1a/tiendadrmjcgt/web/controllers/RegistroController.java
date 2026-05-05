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

/**
 * Controlador encargado de gestionar el proceso de registro de nuevos usuarios.
 * Maneja la visualización del formulario y la validación y persistencia de los datos.
 */
@Controller
@RequestMapping("/register")
public class RegistroController {

    /**
     * Repositorio para la gestión directa de la persistencia de usuarios.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Componente para el cifrado de contraseñas antes de su almacenamiento.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Muestra el formulario de registro al usuario.
     * @param model El modelo de la vista para añadir el DTO de registro.
     * @return El nombre de la vista del formulario de registro.
     */
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioDto", new UsuarioRegistroDto());
        return "registro";
    }

    /**
     * Procesa el envío del formulario de registro.
     * Valida los datos recibidos, cifra la contraseña y crea el nuevo usuario.
     * @param dto Objeto de transferencia de datos con la información del registro.
     * @param result Resultado de la validación de los campos del DTO.
     * @return Redirección al login si el registro es exitoso, o la vista de registro si hay errores.
     */
    @PostMapping
    public String procesarRegistro(@Valid @ModelAttribute("usuarioDto") UsuarioRegistroDto dto,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(dto.getUsername());
            nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
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