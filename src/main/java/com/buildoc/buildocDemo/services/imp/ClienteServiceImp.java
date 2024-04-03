package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Cliente;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.ClienteRepository;
import com.buildoc.buildocDemo.services.ClienteServices;
import com.buildoc.buildocDemo.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClienteServiceImp implements ClienteServices, EntityService<Cliente> {
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void crearCliente(Cliente cliente) {
         clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.getById(id);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        cliente.setEstadoDato(estado);
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarEntidadesActivas() {
        return clienteRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
