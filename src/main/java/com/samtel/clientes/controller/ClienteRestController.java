package com.samtel.clientes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



	@GetMapping("{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id){
		try {
			ClienteDTO response = service.getClienteById(id);
			return  ResponseEntity.ok(response);
		}catch (NoSuchElementException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e){
			return  ResponseEntity.internalServerError().build();
		}

	}

	@PutMapping("{id}")
	public ResponseEntity<ClienteDTO>  updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO cliente){
		try {
			ClienteDTO response = service.actualizarCliente(id, cliente);
			return ResponseEntity.ok(response);
		}catch (NoSuchElementException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e){
			return  ResponseEntity.internalServerError().build();
		}
	}


	@DeleteMapping("{idCliente}")
	public ResponseEntity<Void> deleteCliente(@PathVariable(name = "idCliente") Long id){
		try {
			service.borrarCliente(id);
			return ResponseEntity.noContent().build();
		}catch (NoSuchElementException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e){
			return  ResponseEntity.internalServerError().build();
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
