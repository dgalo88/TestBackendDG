package com.dg.testbackend.client.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.dg.testbackend.client.dto.MessageDTO;
import com.dg.testbackend.client.dto.UserDTO;
import com.dg.testbackend.client.service.IUserConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class UserConsumerController {

	@Autowired
	private IUserConsumerService service;

	@Autowired
	private ObjectMapper mapper;

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("users", service.findAll());
		model.addAttribute("newUser", new UserDTO());
		return "users";
	}

	@PostMapping
	public String create(@ModelAttribute("newUser") UserDTO User) {
		service.create(User);
		return "redirect:/";
	}

	@PutMapping
	public String update(@RequestParam Long id, UserDTO user) {
		service.update(id, user);
		return "redirect:/";
	}

	@DeleteMapping
	public String delete(@RequestParam Long id) {
		service.delete(id);
		return "redirect:/";
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
		MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
		model.addAttribute("error", dto.getMessage());
		return findAll(model);
	}

}
