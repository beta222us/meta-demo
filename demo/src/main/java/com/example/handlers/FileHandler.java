package com.example.handlers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Meta;
import com.example.model.MetaRepository;

@Component
public class FileHandler {

	@Autowired
	MetaRepository metaRepository;
	
	public void saveFile(MultipartFile inputFile){
		

		byte[] bytes = null;
        BufferedOutputStream stream;
		try {
			
			final String fileName = inputFile.getOriginalFilename();
			bytes = inputFile.getBytes();
			
			//saving the file
			stream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
	        stream.write(bytes);
	        stream.close();
	        
	        final List<String> contents = FileUtils.readLines(new File(fileName));
	        
	        final List<Meta> metaList = new ArrayList<Meta>();
	        Meta meta = null;
	        
	        //saving key:value meta data pair
	        if(contents != null && !contents.isEmpty()){
	        	String [] split = null;
	        	for(String content : contents){
	        		split = content.split(":");
	        		meta = new Meta();
	        		meta.setKey(split[0]);
	        		if(split.length > 0){
	               		meta.setValue(split[1]);
	        		}
	        		metaList.add(meta);
	        	}
	        }
	        
			metaRepository.save(metaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Meta> listAllMeta() {
		return metaRepository.findAll();
		
	}
}
