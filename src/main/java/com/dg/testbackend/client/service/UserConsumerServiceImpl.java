package com.dg.testbackend.client.service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dg.testbackend.client.dto.UserDTO;

@Service
public class UserConsumerServiceImpl implements IUserConsumerService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${resource.users}")
	private String resource;

	@Value("${resource.users}/{id}")
	private String idResource;

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;

	@Override
	public List<UserDTO> findAll() {
		return Arrays.stream(restTemplate.getForObject(
				resource, UserDTO[].class)).collect(Collectors.toList());
	}

	@Override
	public UserDTO create(UserDTO user) {
		return restTemplate.exchange(resource, HttpMethod.POST,
				new HttpEntity<>(user, createHeaders(username, password)),
				UserDTO.class).getBody();
	}

	@Override
	public UserDTO update(Long id, UserDTO user) {
		return restTemplate.exchange(idResource, HttpMethod.PUT,
				new HttpEntity<>(user), UserDTO.class, id).getBody();
	}

	@Override
	public void delete(Long id) {
		restTemplate.delete(idResource, id);
	}

	public HttpHeaders createHeaders(String username, String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64( 
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}

}
