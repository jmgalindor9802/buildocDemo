package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Cliente;

import java.util.List;

public interface ClienteServices {
    public List<Cliente> listarClientes();

  Cliente crearCliente(Cliente cliente);

    Cliente obtenerClientePorId(Long id);

    Cliente actualizarCliente(Cliente cliente);

    void eliminarCliente(Long id);
}
