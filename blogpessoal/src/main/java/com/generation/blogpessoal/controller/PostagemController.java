package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController // define a class como REST controladora, onde controlamos métodos HTTP
@RequestMapping("/postagens") //+- URL
@CrossOrigin(origins = "*", allowedHeaders = "*") //@CrossOrigin é usada no controlador de uma aplicação web para permitir solicitações de recursos de domínios ou servidores diferentes do domínio onde a aplicação está hospedada. Os atributos origins e allowedHeaders especificam as origens permitidas e os cabeçalhos HTTP que são permitidos nas solicitações.
	public class PostagemController {
		
	@Autowired // INJEÇÃO DE INDEPENDÊNCIA // define quais Classes serão instanciadas e em quais lugares serão Injetadas quando houver necessidade.:
	private PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){ //SELECT * FROM tb_postagens; // características do método vêem antes igual no inglês
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) { //SELECT * FROM tb_postagens where id = id; //@PathVariable Long id: Esta anotação insere o valor enviado no endereço do endpoint, na Variável de Caminho {id}, no parâmetro do Método getById( Long id );
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}") // se tá entre {} é porque é atributo do banco de dados
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){ //SELECT * FROM tb_postagens where titulo like "%titulo%";.
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); //sempre retornará ok, pois irá exibir a lista vazia ou não
	}
	
	@PostMapping 
	public ResponseEntity<Postagem> post(@Valid  @RequestBody Postagem postagem){ //INSERT INTO tb_postagens (titulo, texto, data) VALUES ("Título", "Texto", CURRENT_TIMESTAMP());.
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}	
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) { //UPDATE tb_postagens SET titulo = "titulo", texto = "texto", data = CURRENT_TIMESTAMP() WHERE id = id;
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") 
	public void delete(@PathVariable Long id) { //DELETE FROM tb_postagens WHERE id = id;
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		postagemRepository.deleteById(id);				
	}
	
}
