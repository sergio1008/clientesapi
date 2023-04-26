package com.samtel.clientes.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClienteDTO {
	private Long id;
	
	@NotBlank
	private String nombreCliente;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String correoElectronico;
}
