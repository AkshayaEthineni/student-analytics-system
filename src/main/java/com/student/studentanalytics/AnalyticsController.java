package com.student.studentanalytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private StudentRepository repo;

    // GET placement probability for a student
    @GetMapping("/placement-probability/{id}")
    public Map<String, String> getPlacementProbability(@PathVariable int id) {
        Student s = repo.findById(id).orElse(null);
        Map<String, String> result = new HashMap<>();
        if (s == null) {
            result.put("error", "Student not found");
            return result;
        }
        result.put("studentName", s.getName());
        result.put("placementProbability", analyticsService.getPlacementProbability(s));
        return result;
    }

    // GET career suggestion for a student
    @GetMapping("/career-suggestion/{id}")
    public Map<String, String> getCareerSuggestion(@PathVariable int id) {
        Student s = repo.findById(id).orElse(null);
        Map<String, String> result = new HashMap<>();
        if (s == null) {
            result.put("error", "Student not found");
            return result;
        }
        result.put("studentName", s.getName());
        result.put("department", s.getDepartment());
        result.put("careerSuggestion", analyticsService.suggestCareerDomain(s));
        return result;
    }

    // GET department wise placement stats
    @GetMapping("/department-stats")
    public Map<String, Object> getDepartmentStats() {
        return analyticsService.getDepartmentStats();
    }

    // GET top 10 students
    @GetMapping("/top-students")
    public List<Student> getTopStudents() {
        return analyticsService.getTopStudents();
    }

    // GET overall summary
    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        return analyticsService.getSummary();
    }
}