package com.example.ex2.controller;

import com.example.ex2.model.Employee;
import com.example.ex2.repository.EmployeeRepository;
import com.example.ex2.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class HomeController {
    private final EmployeeServiceImpl service;

    public HomeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    public String showEmployee(Model model, @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 3);
        Page<Employee> p = service.findAll(pageable);
        model.addAttribute("employees", p);
        model.addAttribute("startPage", 0);
        return "index";
    }

    @GetMapping("/add")
    public String addEmployeeView(Model model) {
        model.addAttribute("title", "Add Employee");
        model.addAttribute("service", "Add");
        return "add";
    }

    @PostMapping("/add")
    public String addEmployeeService(@RequestParam("name") String name,
                                     @RequestParam("email") String email,
                                     @RequestParam("address") String address,
                                     @RequestParam("phone") String phone) {

        service.addNewEmployee(new Employee(name, email, address, phone));
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeView(@PathVariable("id") int id, Model model) {
        Employee employee = service.getByID(id);
        model.addAttribute("employee", employee);
        model.addAttribute("title", "Update Employee");
        model.addAttribute("service", "Add");
        return "add";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("address") String address,
                               @RequestParam("phone") String phone,
                               @PathVariable("id") int id) {

        service.updateEmployee(new Employee(id, name, email, address, phone));

        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String removeEmployee(@PathVariable("id") String id) {
        String[] temp = id.split(",");
        List<Integer> arr = new ArrayList<>();
        for (String s : temp) {
            arr.add(Integer.parseInt(s));
        }
        service.removeAll(arr);
        return "redirect:/employees";
    }
}
