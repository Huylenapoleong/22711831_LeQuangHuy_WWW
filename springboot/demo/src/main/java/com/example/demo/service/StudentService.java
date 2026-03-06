package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    @Autowired
    private SubjectRepository subjectRepo;

    public void enrollSubject(Integer studentId, Integer subjectId){

        Student student = repository.findById(studentId).get();
        Subject subject = subjectRepo.findById(subjectId).get();

        student.getSubjects().add(subject);

        repository.save(student);
    }
    public List<Student> getAll() {
        return repository.findAll();
    }

    public  Student save(Student student) {
        return repository.save(student);
    }

    public  Student getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public List<Student> search(String keyword){
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}

