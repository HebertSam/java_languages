package com.project.languages.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.languages.models.Languages;
import com.project.languages.services.LangService;

@Controller
public class LangController{
	private LangService langService;

	public LangController(LangService langService){
		this.langService = langService;
	}
	
	@RequestMapping("")
	public String index(@ModelAttribute("Languages") Languages language, Model model){
		if (langService.getAll() != null){
			List<Languages> all = langService.getAll();
			model.addAttribute("languages", all);
			return "index";
		} else {
			return "index";
		}
	}
	@PostMapping("/language/new")
	public String create(@Valid @ModelAttribute("Languages") Languages language, BindingResult result, RedirectAttributes flash){
		if (result.hasErrors()){
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/";
		} else{
			langService.createLang(language);
			return "redirect:/";
		}
	}	
	@RequestMapping("/edit/{id}")
	public String thisLang(@PathVariable("id") long id, Model model){
		Languages lang = langService.getOne(id);
		model.addAttribute("language", lang);
		return "edit";
	}
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("Languages") Languages language, BindingResult result, @PathVariable("id") long id,RedirectAttributes flash){
		if (result.hasErrors()){
			flash.addAttribute("errors", result.getAllErrors());
			return "redirect:/edit/"+id;
		} else {
			langService.updateLang(language);
			return "redirect:/";
		}

	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id){
		langService.deleteLang(id);
		return "redirect:/";
	}
}
