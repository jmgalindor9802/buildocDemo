package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClienteServices {
    public List<Cliente> listarClientes();

    public void crearCliente(Cliente cliente);

    public  Cliente obtenerClientePorId(Long id);

    public void actualizarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
