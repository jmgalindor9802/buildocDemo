package com.buildoc.buildocDemo.security;


import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository){

        this.usuarioRepository=usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
        List<Rol> rolesList = Collections.singletonList(usuario.getRol());
        return new User(usuario.getUsername(), usuario.getPassword(), mapRolesToAuthorities(rolesList)) {
        };

    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }
}
