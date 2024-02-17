package ba.sum.fsre.studentpraksa.controller;

import ba.sum.fsre.studentpraksa.model.Student;
import ba.sum.fsre.studentpraksa.model.User;
import ba.sum.fsre.studentpraksa.model.UserDetails;
import ba.sum.fsre.studentpraksa.repositories.UserRepository;
import ba.sum.fsre.studentpraksa.services.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @Autowired
    private UserRepository userRepo;

    @PersistenceContext
    EntityManager entityManager;


    @GetMapping("/student")
    public ModelAndView getAllstudent() {
        List<Student> list = service.getAllstudent();
        return new ModelAndView("studentList", "student", list);
    }
    @GetMapping("/new_student")
    public String newstudent(){
        return "new_student";
    }

    @PostMapping("/save")
    public String addstudent(@ModelAttribute Student m){
        service.save(m);
        return "redirect:/student";
    }
    @GetMapping("/my_student")
    public String getMystudent(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        model.addAttribute("student", u.getMystudent());
        return "my_student";
    }

    @RequestMapping("/deleteMyList/{id}")
    @Transactional
    public String deleteMyList(@PathVariable("id") int id){
        Student m=service.getstudentById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        u.removeMystudent(m);
        entityManager.persist(u);
        return  "redirect:/my_student";
    }

    @RequestMapping("/mylist/{id}")
    @Transactional
    public String getMyList(@PathVariable("id")int id){
        Student m=service.getstudentById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        u.addMystudent(m);
        entityManager.persist(u);
        return "redirect:/student";
    }
    @RequestMapping("/editstudent/{id}")
    public String editstudent(@PathVariable("id") int id, Model model){
       Student m=service.getstudentById(id);
       model.addAttribute("student", m);
       return "student_edit";
    }
    @RequestMapping("/deletestudent/{id}")
    public String deletestudent(@PathVariable("id") int id){
        service.deletestudentById(id);
        return "redirect:/student";
    }
}

