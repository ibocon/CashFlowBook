package com.ibocon.cashflowbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {    

    @GetMapping("/index")
    public String index() {
        return "/index.html";
    }

    @GetMapping("/hello")
    public String hello(){
        return "/hello.html";
    }

    @PostMapping("/perform_login")
    public String performLogin() {
        return "Login";
    }

    @PostMapping("perform_logout")
    public String performLogout() {
        return "Logout";
    }
}