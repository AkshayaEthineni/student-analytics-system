package com.student.studentanalytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AnalyticsService {

    @Autowired
    private StudentRepository repo;

    // 1. PLACEMENT PROBABILITY
    public String getPlacementProbability(Student s) {
        int score = 0;

        if (s.getCgpa() >= 8.0) score += 30;
        else if (s.getCgpa() >= 7.0) score += 20;
        else if (s.getCgpa() >= 6.0) score += 10;

        if (s.getAptitudeScore() >= 80) score += 25;
        else if (s.getAptitudeScore() >= 60) score += 15;
        else if (s.getAptitudeScore() >= 40) score += 5;

        if (s.getAttendance() >= 85) score += 20;
        else if (s.getAttendance() >= 75) score += 10;

        if (s.getCertifications() >= 3) score += 15;
        else if (s.getCertifications() >= 1) score += 8;

        if (score >= 70) return "HIGH";
        else if (score >= 45) return "MEDIUM";
        else return "LOW";
    }

    // 2. CAREER DOMAIN SUGGESTION
    public String suggestCareerDomain(Student s) {
        String dept = s.getDepartment() != null ? s.getDepartment().toUpperCase() : "";
        double cgpa = s.getCgpa();

        if (dept.contains("CSE") || dept.contains("IT")) {
            if (cgpa >= 8.0) return "Software Developer / Full Stack";
            else if (cgpa >= 7.0) return "Backend Developer / QA Engineer";
            else return "Technical Support / Junior Developer";
        } else if (dept.contains("ECE") || dept.contains("EEE")) {
            if (cgpa >= 8.0) return "Embedded Systems / VLSI";
            else return "Core Electronics / Hardware Engineer";
        } else if (dept.contains("MECH")) {
            return "Mechanical Design / Manufacturing";
        } else if (dept.contains("CIVIL")) {
            return "Civil Engineering / Construction Management";
        } else {
            return "General Software / IT Services";
        }
    }

    // 3. DEPARTMENT WISE PLACEMENT STATS
    public Map<String, Object> getDepartmentStats() {
        List<Student> all = repo.findAll();
        Map<String, Integer> total = new HashMap<>();
        Map<String, Integer> placed = new HashMap<>();

        for (Student s : all) {
            String dept = s.getDepartment() != null ? s.getDepartment() : "Unknown";
            total.put(dept, total.getOrDefault(dept, 0) + 1);
            if (s.isPlaced()) {
                placed.put(dept, placed.getOrDefault(dept, 0) + 1);
            }
        }

        Map<String, Object> result = new HashMap<>();
        for (String dept : total.keySet()) {
            int t = total.get(dept);
            int p = placed.getOrDefault(dept, 0);
            double percentage = t > 0 ? (p * 100.0 / t) : 0;
            Map<String, Object> stats = new HashMap<>();
            stats.put("total", t);
            stats.put("placed", p);
            stats.put("placementPercentage", Math.round(percentage) + "%");
            result.put(dept, stats);
        }
        return result;
    }

    // 4. TOP STUDENTS
    public List<Student> getTopStudents() {
        List<Student> all = repo.findAll();
        all.sort((a, b) -> {
            double scoreA = a.getCgpa() * 10 + a.getAptitudeScore() * 0.3;
            double scoreB = b.getCgpa() * 10 + b.getAptitudeScore() * 0.3;
            return Double.compare(scoreB, scoreA);
        });
        return all.size() > 10 ? all.subList(0, 10) : all;
    }

    // 5. OVERALL SUMMARY
    public Map<String, Object> getSummary() {
        List<Student> all = repo.findAll();
        long totalStudents = all.size();
        long placedCount = all.stream().filter(Student::isPlaced).count();
        double avgCgpa = all.stream().mapToDouble(Student::getCgpa).average().orElse(0);
        double avgAptitude = all.stream().mapToDouble(Student::getAptitudeScore).average().orElse(0);

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalStudents", totalStudents);
        summary.put("placedStudents", placedCount);
        summary.put("unplacedStudents", totalStudents - placedCount);
        summary.put("placementRate", totalStudents > 0 ?
                Math.round(placedCount * 100.0 / totalStudents) + "%" : "0%");
        summary.put("averageCgpa", Math.round(avgCgpa * 10.0) / 10.0);
        summary.put("averageAptitudeScore", Math.round(avgAptitude));
        return summary;
    }
}