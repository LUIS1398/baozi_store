package com.example.baozi_store.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baozi_store.model.Pedido;
import com.example.baozi_store.repository.PedidoRepository;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	private final PedidoRepository pedidoRepository;
	
	public PedidoController(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
		
	}
	
	// CADASTRAR PEDIDO
	@PostMapping
	public Pedido cadastrar(@RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	// LISTAR PEDIDO
	@GetMapping
	public List <Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	// CONSULTAR PEDIDO POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorID(@PathVariable Long id) {
		return pedidoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	// EXCLUIR PEDIDO
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		
		if(!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		pedidoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
