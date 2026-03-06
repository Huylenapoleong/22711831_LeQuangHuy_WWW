package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private StudentRepository  repository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.getAll());
        return "students";
    }

    @PostMapping("/save")
    public String save(Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/students";
    }
    @PostMapping("/enroll")
    public String enroll(Integer studentId, Integer subjectId){

        service.enrollSubject(studentId, subjectId);

        return "redirect:/students/edit/" + studentId;
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Integer id, Model model){

        Student student = repository.findById(id).get();
        List<Subject> subjects = subjectRepository.findAll();

        model.addAttribute("student", student);
        model.addAttribute("subjects", subjects);

        return "edit";
    }
    @PostMapping("/update")
    public String updateStudent(Student student){
        service.save(student);
        return "redirect:/students";
    }
    @GetMapping("/search")
    public String search(String keyword, Model model){

        if(keyword != null && !keyword.isEmpty()){
            model.addAttribute("students", service.search(keyword));
        }else{
            model.addAttribute("students", service.getAll());
        }

        return "students";
    }
}
