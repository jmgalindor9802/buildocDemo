package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Cliente;
import com.buildoc.buildocDemo.repositories.ClienteRepository;
import com.buildoc.buildocDemo.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public void eliminarCliente(Long id) {

    }
}
