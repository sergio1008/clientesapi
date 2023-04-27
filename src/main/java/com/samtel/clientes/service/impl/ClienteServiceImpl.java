package com.samtel.clientes.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samtel.clientes.dto.ClienteDTO;
import com.samtel.clientes.persistence.entity.ClienteEntity;
import com.samtel.clientes.persistence.repository.ClienteRepository;
import com.samtel.clientes.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository repository;
	
	@Override
	public List<ClienteDTO> getAll() {		
		return repository.findAll().stream().map(ent -> {
			ClienteDTO dto = new ClienteDTO();
			dto.setId(ent.getId());
			dto.setNombreCliente(ent.getNombre());
			dto.setCorreoElectronico(ent.getCorreo());
			dto.setDocumento(ent.getDni());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public ClienteDTO getClienteById(Long id) {
		ClienteEntity entity = repository.findById(id).orElseThrow();

		ClienteDTO dto = new ClienteDTO();
		dto.setId(entity.getId());
		dto.setNombreCliente(entity.getNombre());
		dto.setCorreoElectronico(entity.getCorreo());
		dto.setDocumento(entity.getDni());

		return dto;
	}

	@Override
	public ClienteDTO crearCliente(ClienteDTO cliente) {
		ClienteEntity entity = new ClienteEntity();
		
		entity.setNombre(cliente.getNombreCliente());
		entity.setDni(cliente.getDocumento());
		entity.setCorreo(cliente.getCorreoElectronico());
		
		repository.save(entity);
		
		cliente.setId(entity.getId());
		return cliente;
	}

	@Override
	public ClienteDTO actualizarCliente(Long id, ClienteDTO cliente) {
		ClienteEntity clienteEntity = repository.findById(id).orElseThrow();

		clienteEntity.setNombre(cliente.getNombreCliente());
		clienteEntity.setDni(cliente.getDocumento());
		clienteEntity.setCorreo(cliente.getCorreoElectronico());

		repository.save(clienteEntity);
		return cliente;
	}

	@Override
	public void borrarCliente(Long id) {
		if(repository.existsById(id)){
			repository.deleteById(id);
		} else {
			throw  new NoSuchElementException("Id no encontrado");
		}
	}

}
