package com.youtube.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.demo.model.User;
import com.youtube.demo.service.UserService;
import com.youtube.demo.util.RestResponse;
	

@RestController
public class UserController {
	
	@Autowired
	protected UserService userService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String userJson) 
			throws JsonMappingException, JsonProcessingException {
		
		this.mapper = new ObjectMapper();
		
		User user = this.mapper.readValue(userJson, User.class);
		
		if(!this.validate(user)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value() ,
					"No están diligenciados los campos obligatorios");
		}
		
		this.userService.save(user);
		
		return new RestResponse(HttpStatus.OK.value() ,
				"Operacion exitosa");
	}
	
	// metodo que busca los usuarios en las listas
	@RequestMapping(value="/getUsers", method = RequestMethod.GET)
	public List<User> getUsers() {
		return this.userService.findAll();
	}
	
	private boolean validate(User user) {
		boolean isValid = true;
		if (user.getFirstName() == "" || user.getFirstName() == null) {
			isValid = false;
		}
		if (user.getFirstSurname() == "" || user.getFirstSurname() == null) {
			isValid = false;
		}
		if (user.getAddress() == "" || user.getAddress() == null) {
			isValid = false;
		}
		return isValid;
		
	}
	
}
