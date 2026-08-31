package com.student.studentanalytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // GET all students
    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable int id) {
        return repo.findById(id);
    }

    // POST add new student
    @PostMapping
    public Student add(@RequestBody Student student) {
        return repo.save(student);
    }

    // PUT update student
    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student updated) {
        updated.setId(id);
        return repo.save(updated);
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "Student deleted successfully";
    }
}