package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    @RequestMapping("/subjects")
    public class SubjectController {

        @Autowired
        private SubjectRepository repo;

        @GetMapping
        public String list(Model model){
            model.addAttribute("subjects", repo.findAll());
            return "subjects";
        }

        @PostMapping("/save")
        public String save(Subject subject){
            repo.save(subject);
            return "redirect:/subjects";
        }
    }

