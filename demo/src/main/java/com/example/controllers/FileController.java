package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.handlers.FileHandler;
import com.example.model.Meta;

@Controller
public class FileController {
	
	@Autowired
	FileHandler fileHandler;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String login(){
		return "fileUpload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String saveFile(@RequestParam(name = "file") MultipartFile inputFile){
		
		fileHandler.saveFile(inputFile);
		return "success";
	}
	
	
	@RequestMapping(value = "/listMeta", method = RequestMethod.GET)
	@ResponseBody
	public List<Meta> listMeta(){
		
		return fileHandler.listAllMeta();
	}
}
