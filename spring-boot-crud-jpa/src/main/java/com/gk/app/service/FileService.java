package com.gk.app.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private final Path rootLocation = Paths.get("C:\\Users\\Kasturi's\\Desktop\\uploads");

	public void store(MultipartFile file) {
		try {
			System.out.println(file.getOriginalFilename());
			System.out.println(rootLocation.toUri());
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
}