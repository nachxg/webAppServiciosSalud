package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Imagen;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.enumeraciones.Rol;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String dni, String password, String password2 ) throws Exception{

        validar(nombre, apellido, dni, password, password2);

        Usuario usuario = new Usuario();
        Imagen imagen = imagenServicio.guardar(archivo);

        usuario.setFechaAlta(LocalDateTime.now());
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setActivo(true);
        usuario.setRol(Rol.PACIENTE);
        usuario.setImagen(null);
        usuario.setEmail(null);
        usuario.setFechaNacimiento(null);
        usuario.setTelefono(null);
        usuario.setSexo(null);
        usuario.setPaciente(null);
        usuario.setProfesional(null);

        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void actualizar(MultipartFile archivo, Long id, String nombre, String apellido, String email, String password, String password2,
                           String dni, LocalDate fechaNacimiento, String telefono, String sexo) throws Exception{

        validar(nombre, apellido, dni, password, password2);

        Usuario usuario = usuarioRepositorio.buscarPorId(id);

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            usuario.setActivo(true);
            usuario.setRol(Rol.PACIENTE);
            usuario.setDni(dni);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setTelefono(telefono);
            usuario.setSexo(sexo);

        Long idImagen = null;

            if (usuario.getImagen() !=null){
                idImagen = usuario.getImagen().getId();

            }
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            usuario.setImagen(imagen);

            usuarioRepositorio.save(usuario);

    }

    public Usuario getOne(String id){
        return usuarioServicio.getOne(id);
    }

    public List<Usuario> listarUsuarios(){

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioRepositorio.findAll();

        return usuarios;
    }

    private void validar(String nombre, String apellido, String dni, String password, String password2) throws Exception {

        if (nombre.isEmpty() || nombre == null){
            throw new Exception("El nombre no puede ser nulo o estar vacio");
        }
        if (apellido.isEmpty() || apellido == null){
            throw new Exception("El apellidp no puede ser nulo o estar vacio");
        }
        if (dni.isEmpty() || dni == null){
            throw new Exception("El dni no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password
                == null || password.length()<=6 ){
            throw new Exception("El password no puede estar vacio y debe contener por lo menos 6 caracteres");
        }
        if (password.equals(password2)){
            throw new Exception("Los password ingresados deben ser iguales");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorDni(dni);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());//ROLE_USER

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getDni(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }

}
