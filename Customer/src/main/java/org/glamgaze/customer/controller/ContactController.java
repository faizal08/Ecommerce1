package org.glamgaze.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController
{
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
