package com.correa.workShop.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.correa.workShop.entities.Usuario;
import com.correa.workShop.repositories.UsuarioRepository;
import com.correa.workShop.services.exceptions.TratamentoErroDB;
import com.correa.workShop.services.exceptions.RecursoNãoEncontradoException;

@Service //registro da classse (camada de serviço) como componente do spring injetado automaticamente no Autowired 
public class UsarioService {

	//injeção de dependencia
	@Autowired
	private UsuarioRepository repository;

	//método(camada de serviço) findAll responsável por retornar os usuarios do db
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	//método para recuperar o usuário por id
	public Usuario findById(Long id) {
		//chamada do método get no objeto optional<usuario> onde será lançada exceção caso nao exista usuário
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new RecursoNãoEncontradoException(id));
	}
	
	//método para inserir no db um novo objeto do tipo usuario
	public Usuario inserir(Usuario obj) {
		return repository.save(obj);
	}
	//método para deletar no db um novo objeto do tipo usuario
	public void deletar(Long id) {
		//tratamento do erro 500 para 404 nao encontrado no http
		//chamada do método get no objeto optional<usuario> onde será lançada exceção caso nao exista usuário
		//exceção personalizada
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNãoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new TratamentoErroDB(e.getMessage());
		}
	}
	//método para atualizar no db um objeto do tipo usuario
	public Usuario update(Long id, Usuario obj) {	
		try {
			Usuario entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new RecursoNãoEncontradoException(id);
		}
	}
	//método para atualizar os dados do entity com base nos dados que vieram do obj no db um objeto do tipo usuario
	private void updateData(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setContato(obj.getContato());
	}
}
