package com.samtel.clientes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samtel.clientes.dto.ClienteDTO;
import com.samtel.clientes.service.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteRestController {
	
	/*
	 * get @GetMapping
	 * post @PostMapping
	 * put @PutMapping
	 * delete @DeleteMapping
	 */
	
	private final ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAll() {
		
		try {
			List<ClienteDTO> reponseData = service.getAll();
			
			if (reponseData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(reponseData, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO body){
		try {
			service.crearCliente(body);
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/*
	 * 
	 * 
	 * @GetMapping("{id}")    http://localhost:9090/clientes/12
	 *   metodo(@PathVariable(name ="id") Long id);
	 *   
	 * @GetMapping("")    http://localhost:9090/clientes?id=12
	 *    metodo(@RequestParam(name ="id") Long id);
	 */
}
