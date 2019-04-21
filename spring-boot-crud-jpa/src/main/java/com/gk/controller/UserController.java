package com.gk.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gk.app.service.FileService;
import com.gk.app.service.UserService;
import com.gk.entity.User;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*", allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	public FileService fileService;

	@PostMapping(value="/uploadPhoto",	headers = ("content-type=multipart/form-data"))
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestBody final User user) {
		String message = "";
		try {
			fileService.store(file);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			userService.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Fail to upload Profile Picture" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping(value="/signUp")
	public Map<String, String> signUp(@RequestBody  User user){
		Map<String, String> response = new HashMap<String, String>();
		boolean status = userService.save(user);
		String message = "message";
		if (status == true) {
			response.put(message, "User Added.\n Reg.Id: "+user.getUserId());
			response.put("status", "true");
		} else {
			response.put(message, "Error!");
			response.put("status", "false");
		}

		return response;
	}
	
	@GetMapping(value = "/login")
	public Map<String, String> validateUser(@RequestParam(name = "userName") String username,
			@RequestParam(name = "password") String password) {

		boolean status = userService.validateUser(username, password);
		Map<String, String> response = new HashMap<String, String>();
		String message = "message";
		if (status == true) {
			response.put(message, "user found");
			response.put("status", "true");
		} else {
			response.put(message, "user not found");
			response.put("status", "false");
		}

		return response;
	}

	@GetMapping(value = "/getUser")
	public Optional<User> getUser(@RequestParam(name = "userName") String userName) {

		return userService.getUser(userName);
	}

//	@PostMapping(value = "/addUser")
//	public String saveMotorBike(@RequestBody final User user, @RequestParam("file") MultipartFile file) {
//		boolean status = userService.save(user);
//
//		try {
//			fileService.store(file);
//			userService.save(user);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		if (status == true) {
//			return "User with id: " + user.getUserId() + " has been saved.!";
//		} else {
//			return "Not possible to save this User with id : " + user.getUserId();
//		}
//	}
}
