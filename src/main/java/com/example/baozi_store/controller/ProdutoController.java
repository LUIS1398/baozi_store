package com.example.baozi_store.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baozi_store.model.Produto;
import com.example.baozi_store.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
		
	}
	
	// CADASTRAR PRODUTO
	@PostMapping
	public Produto cadastrar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
		
	}
	
	//LISTAR PRODUTO
	@GetMapping
	public List<Produto> Listar() {
		return produtoRepository.findAll();
	}
	
	// CONSULTAR PRODUTO POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorID(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	// EXCLUIR PRODUTO
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		produtoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	

}
