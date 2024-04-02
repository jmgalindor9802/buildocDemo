package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.dto.AuthResponseDto;
import com.buildoc.buildocDemo.dto.LoginDto;
import com.buildoc.buildocDemo.dto.LoginResponseDto;
import com.buildoc.buildocDemo.dto.RegisterDto;
import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.repositories.RolRepository;
import com.buildoc.buildocDemo.repositories.UsuarioRepository;
import com.buildoc.buildocDemo.security.JWTGenerator;
import com.buildoc.buildocDemo.services.PersonaServices;
import com.buildoc.buildocDemo.services.UsuarioServices;
import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buildoc/auth")

public class AuthController {
    @Autowired
    private PersonaServiceImp personaServiceImp;
    private AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private UsuarioServices usuarioService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, UsuarioServices usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator= jwtGenerator;
        this.usuarioService = usuarioService;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        // Obtener información adicional del usuario autenticado
        String nombre = usuarioService.obtenerUsuarioPorUsername(loginDto.getUsername());// Obtener el nombre del usuario
        Long idUsuario= usuarioService.obtenerIdUsuarioPorUsername(loginDto.getUsername()) ;
        List<String> roles = authentication.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        // Crear un objeto que contenga el token JWT, el nombre, el username y los roles del usuario
        LoginResponseDto loginResponse = new LoginResponseDto(token, nombre, loginDto.getUsername(), roles,idUsuario);
        // Agregar un console.log
        System.out.println("Login Response: " + loginResponse.toString());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(usuarioRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);

        }
        Usuario user = new Usuario();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setNombre(registerDto.getNombre());


        List<Rol> roles = new ArrayList<>();
        for (Long roleId : registerDto.getRoles()) {
            Optional<Rol> optionalRol = rolRepository.findById(roleId);
            optionalRol.ifPresent(roles::add);
        }
        user.setRoles(roles);

        Long idPersona = Long.parseLong(registerDto.getPersonaId().toString());
        Persona persona = personaServiceImp.obtenerPersonaPorId(idPersona);
        if (persona == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setPersona(persona);

        usuarioRepository.save(user);
        return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
    }




}
