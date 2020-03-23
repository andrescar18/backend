package com.youtube.demo.service;

import java.util.List;

import com.youtube.demo.model.User;

public interface UserService {
	
	/**
	 * Guarda usuarios en la tabla
	 * @param user
	 * @return usuario guardado
	 */
	User save(User user);
	
	/**
	 * Recupera la lista de usuarios
	 * @return lista de usuarios
	 */
	List<User> findAll();



}
