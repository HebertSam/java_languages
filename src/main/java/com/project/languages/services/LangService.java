package com.project.languages.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.languages.languagesApplication;
import com.project.languages.models.Languages;
import com.project.languages.repositories.LangRepository;

@Service
public class LangService {
	public LangRepository langRepository;
		
	public LangService(LangRepository langRepository){
		this.langRepository = langRepository;
	}

	public List<Languages> getAll(){
		return (List<Languages>) langRepository.findAll();
	}
	public Languages getOne(long id){
		return langRepository.findOne(id);
	}
	public void createLang(Languages language){
		langRepository.save(language);
	}
	public void updateLang(Languages language){
		langRepository.save(language);
	}
	public void deleteLang(long id){
		langRepository.delete(id);
	}
	
	// Crud methods to act on services go here.
}
