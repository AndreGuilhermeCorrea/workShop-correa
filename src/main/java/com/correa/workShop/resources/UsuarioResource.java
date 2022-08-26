package com.correa.workShop.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.correa.workShop.entities.Usuario;
import com.correa.workShop.services.UsarioService;

//UsuarioResource com controlador rest
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	//injecao de dependencia para o service
	@Autowired 
	private UsarioService service;

	//método findAll para acessar todos objetos do tipo usuario 
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	//entrepoint para acessar o objeto do tipo usuário por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	//método para inserir novo objeto do tipo usuario no db 
	//anotação do spring responsável por fazer um pre processamento na compilação definindo portanto que esse método post do http
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	//método para deletar objeto do tipo id no db
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
