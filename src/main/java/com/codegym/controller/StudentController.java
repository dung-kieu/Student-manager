package com.codegym.controller;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import com.codegym.service.ClassroomService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @ModelAttribute("Classrooms")
    public Iterable<Classroom> classrooms(){
        return classroomService.findAll();
    }

    @GetMapping("/student")
    public ModelAndView showListStudent(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5)Pageable pageable){
        Page<Student> students;
        if(s.isPresent()){
        students = studentService.findAllByFirstName(s.get(), pageable);
    } else {
        students = studentService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/create-student")
    public ModelAndView showCreateStudent() {
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("message", "Create student successfully");
        return modelAndView;
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        ModelAndView modelAndView = null;
        if (student != null) {
            modelAndView = new ModelAndView("student/edit");
            modelAndView.addObject("student", student);
        } else {
            modelAndView = new ModelAndView("error.404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-student")
    public ModelAndView editStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("student/edit");
        modelAndView.addObject("student", student);
        modelAndView.addObject("message", "Edit student successfully");
        return modelAndView;
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteStudent(@PathVariable Long id) {
        studentService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/student");
        return modelAndView;
    }
}
