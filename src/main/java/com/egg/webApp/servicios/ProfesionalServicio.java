package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Imagen;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.enumeraciones.Especialidad;
import com.egg.webApp.enumeraciones.Rol;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.repositorios.PacienteRepositorio;
import com.egg.webApp.repositorios.ProfesionalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesionalServicio {

    ProfesionalServicio profesionalServicio;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    UsuarioServicio usuarioServicio;


    @Transactional
    public void registrarProfesional(String nombre, String apellido, String dni, String password, String password2, String sexo, String matricula, String especialidad, String fechaNacimiento) throws Exception {
        System.out.println("llego aqui1");
        validar(nombre, apellido, dni, password, password2,matricula, especialidad);
        System.out.println("llego aqui2");
        Profesional profesional = new Profesional();
        profesional.setMatricula(matricula);
        profesional.setEspecialidad(Especialidad.valueOf(especialidad));
        profesional.setRol(Rol.PROFESIONAL);
        profesionalRepositorio.save(profesional);
        System.out.println("llego aqui3");
        usuarioServicio.registrar(nombre, apellido, dni, password, password2, profesional.getId(), sexo, fechaNacimiento);
        System.out.println("llego aqui6");
    }

    @Transactional
    public void actualizarProfesional(MultipartFile archivo, Long id, String email, String password, String password2, String telefono, String sexo) throws Exception {

      validarActualizacion(password, password2, sexo, telefono, email);

        Profesional profesional = profesionalRepositorio.buscarPorId(id);

        profesional.setEmail(email);
        profesional.setPassword(new BCryptPasswordEncoder().encode(password));
        profesional.setTelefono(telefono);
        profesional.setSexo(Sexo.valueOf(sexo));


        Long idImagen = null;

        if (profesional.getImagen() != null) {
            idImagen = profesional.getImagen().getId();

        }
        Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
        profesional.setImagen(imagen);

        profesionalRepositorio.save(profesional);

    }

    public Profesional getOne(Long id) {
        return profesionalRepositorio.getOne(id);
    }

    public List<Profesional> listarProfesionales() {

        List<Profesional> Profesionales = new ArrayList<>();
        Profesionales = profesionalRepositorio.findAll();

        return Profesionales;
    }



    private void validar(String nombre, String apellido, String dni, String password, String password2, String matricula, String especialidad) throws Exception {

        if (nombre.isEmpty() || nombre == null) {
            throw new Exception("El nombre no puede ser nulo o estar vacio");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new Exception("El apellido no puede ser nulo o estar vacio");
        }
        if (dni.isEmpty() || dni == null) {
            throw new Exception("El dni no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 6) {
            throw new Exception("El password no puede estar vacio y debe contener por lo menos 6 caracteres");
        }
        if (!password.equals(password2)) {
            throw new Exception("Los password ingresados deben ser iguales");
        }
        if (matricula.isEmpty() || matricula == null){
            throw new Exception("La matricula no puede ser nulo o estar vacio");
        }
        if (especialidad.isEmpty() || especialidad == null){
            throw new Exception("La especialidad no puede ser nulo o estar vacio");
        }
    }

    private void validarActualizacion(String password, String password2, String sexo, String telefono, String email) throws Exception {

        if (sexo.isEmpty() || sexo == null) {
            throw new Exception("El sexo no puede ser nulo o estar vacio");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new Exception("El telefono no puede ser nulo o estar vacio");
        }
        if (email.isEmpty() || email == null) {
            throw new Exception("El email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 6) {
            throw new Exception("El password no puede estar vacio y debe contener por lo menos 6 caracteres");
        }
        if (!password.equals(password2)) {
            throw new Exception("Los password ingresados deben ser iguales");
        }

    }

    public LocalDate convertirStringALocalDate(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fechaNacimiento, formatter);
    }
}