package com.blog.Services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.Services.fileService;

import lombok.Setter;
@Service
public class fileImpl implements fileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name=file.getOriginalFilename();
		
		
		String randonId=java.util.UUID.randomUUID().toString();	
		String filename1=randonId.concat(name.substring(name.lastIndexOf(".")));
		
		
		String filepath=path+File.separator+filename1;
		
		File f=new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filepath));
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream inputStream=new java.io.FileInputStream(fullPath);
		return inputStream;
	}

}
