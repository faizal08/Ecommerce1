package org.glamgaze.customer.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.glamgaze.library.dto.CustomerDto;
import org.glamgaze.library.model.Customer;
import org.glamgaze.library.repository.CustomerRepository;
import org.glamgaze.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginRegisterController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public LoginRegisterController(CustomerService customerService,
                                   CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model, HttpSession session) {
        model.addAttribute("title", "Login Page");
        Object attribute = session.getAttribute("userLoggedIn");
        if (attribute != null) {
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/login-register/dashboard")
    public String getDashboard(@RequestParam(required = false) String tab, Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            Customer customer = customerService.findByEmail(principal.getName());
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("username", customer.getFirstName() + " " + customer.getLastName());
            if (tab != null && !tab.isEmpty()) {
                model.addAttribute("openTab", tab);
                System.out.println(tab);
            } else {
                model.addAttribute("openTab", "");
            }
            model.addAttribute("customer", customer);
            model.addAttribute("title", "index");
            return "dashboard";
        }
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    @PostMapping("/do-register")
    public String registerCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                   BindingResult result,
                                   Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            String username = customerDto.getEmail();
            Customer customer = customerService.findByEmail(username);
            if (customer != null) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("error", "This Email is already Registered!");
                return "register";
            } else {
                // Save the customer only if it's not already registered
                customerService.save(customerDto);
                model.addAttribute("success", "Registration successful!");
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
            return "register";
        }
    }
}
