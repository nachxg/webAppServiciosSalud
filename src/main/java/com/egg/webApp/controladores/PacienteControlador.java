package com.egg.webApp.controladores;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.enumeraciones.ObraSocial;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paciente")
public class PacienteControlador {
    private final PacienteServicio pacienteServicio;
    private final EnumServicio enumServicio;
    private final FamiliarServicio familiarServicio;
    private final TurnoServicio turnoServicio;

    public PacienteControlador(PacienteServicio pacienteServicio, EnumServicio enumServicio, FamiliarServicio familiarServicio, TurnoServicio turnoServicio) {
        this.pacienteServicio = pacienteServicio;
        this.enumServicio = enumServicio;
        this.familiarServicio = familiarServicio;
        this.turnoServicio = turnoServicio;
    }

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        return "registro.html";

    }

    @PostMapping("/registrar")
    public String registro(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, @RequestParam String dni, @RequestParam String sexo, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
        try {
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);
            modelo.put("exito", "Usuario creado con exito");
            return "redirect:/index";

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "redirect:/paciente/registrar";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_ADMIN')")
    @GetMapping("/perfil/{id}")
    public String perfilPaciente(ModelMap modelo, HttpSession session, @PathVariable Long id) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        List<ObraSocial> obraSociales = enumServicio.obtenerObraSocial();
        modelo.addAttribute("generos", generos);
        modelo.addAttribute("obraSociales", obraSociales);

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Paciente paciente = null;

        if (usuario.getRol().toString().equalsIgnoreCase("ADMIN")) {
            paciente = pacienteServicio.buscarPorId(id);
        } else {
            paciente = (Paciente) session.getAttribute("usuariosession");
        }

        modelo.put("paciente", paciente);
        return "editarPaciente.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizarPaciente(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String password, @RequestParam String password2,
                                     ModelMap modelo, @RequestParam String telefono, @RequestParam String sexo,
                                     @RequestParam String obraSocial, @RequestParam String numeroObraSocial) {
        try {
            pacienteServicio.actualizarPaciente(archivo, id, email, password, password2, telefono, sexo.toUpperCase(), obraSocial, numeroObraSocial);
            modelo.put("exito", "Usuario actualizado correctamente");
            return "redirect:/inicio";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "editarPaciente.html";
        }
    }

    @PostMapping("/tomarTurno/{idPaciente}/{idTurno}")
    public String tomarTurno(@PathVariable Long idPaciente, @PathVariable Long idTurno) {
        try {

            turnoServicio.tomarUnTurnoPaciente(idPaciente, idTurno);
            System.out.println("Turno tomado");
            return "redirect:/inicio";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error.html";
        }
    }

    @PostMapping("/cancelarTurno/{idTurno}")
    public String cancelarTurno(@PathVariable Long idTurno) {

        try {
            turnoServicio.cancelarTurnoPaciente(idTurno);
            System.out.println("Turno cancelado");
            return "redirect:/inicio";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error.html";
        }

    }
}