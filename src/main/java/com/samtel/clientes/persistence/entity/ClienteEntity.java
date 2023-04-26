package com.samtel.clientes.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class ClienteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	
	@Column(name = "nombre_cliente", length = 255 )
	private String nombre;
	
	@Column(name = "documento", length = 20, unique = true)
	private String dni;
	
	@Column(nullable = false)
	private String correo;
}
