package com.samtel.clientes.service;

import java.util.List;

import com.samtel.clientes.dto.ClienteDTO;

public interface ClienteService {
	
	List<ClienteDTO> getAll();
	
	ClienteDTO getClienteById(Long id);
	
	ClienteDTO crearCliente(ClienteDTO cliente);
	
	ClienteDTO actualizarCliente(Long id, ClienteDTO cliente);
	
	void borrarCliente(Long id);
}
