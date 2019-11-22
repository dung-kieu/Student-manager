package com.codegym.controller;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import com.codegym.service.ClassroomService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/classroom")
    public ModelAndView showListClassroom(){
        Iterable<Classroom> classrooms =classroomService.findAll();
        ModelAndView modelAndView = new ModelAndView("classroom/list");
        modelAndView.addObject("classroom",classrooms);
        return modelAndView;
    }

    @GetMapping("/create-classroom")
    public ModelAndView showCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("classroom/create");
        modelAndView.addObject("classroom", new Classroom());
        return modelAndView;
    }

    @PostMapping("/create-classroom")
    public ModelAndView saveCategory(@ModelAttribute("classroom") Classroom classroom) {
        classroomService.save(classroom);
        ModelAndView modelAndView = new ModelAndView("classroom/create");
        modelAndView.addObject("classroom", new Classroom());
        modelAndView.addObject("message", "Create classroom successfully");
        return modelAndView;
    }

    @GetMapping("/edit-classroom/{id}")
    public ModelAndView showEditCategory(@PathVariable Long id) {
        Classroom classroom = classroomService.findById(id);
        ModelAndView modelAndView = null;
        if (classroom != null) {
            modelAndView = new ModelAndView("classroom/edit");
            modelAndView.addObject("classroom", classroom);
        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-classroom")
    public ModelAndView updateCategory(@ModelAttribute("classroom") Classroom classroom){
        classroomService.save(classroom);
        ModelAndView modelAndView = new ModelAndView("classroom/edit");
        modelAndView.addObject("classroom", classroom);
        modelAndView.addObject("message", "Edit classroom successfully");
        return modelAndView;
    }

    @GetMapping("/delete-classroom/{id}")
    public ModelAndView deleteCategory(@PathVariable Long id){
        classroomService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/classroom");
        return modelAndView;
    }

    @GetMapping("/view-classroom/{id}")
    public ModelAndView viewCategory(@PathVariable Long id){
        Classroom classroom = classroomService.findById(id);
        if (classroom == null){
            return new ModelAndView("error.404");
        }

        Iterable<Student> students = studentService.findAllByClassroom(classroom);
        ModelAndView modelAndView = new ModelAndView("classroom/view");
        modelAndView.addObject("classroom", classroom);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
