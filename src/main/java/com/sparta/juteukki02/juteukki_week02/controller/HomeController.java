package com.sparta.juteukki02.juteukki_week02.controller;


//import com.sparta.juteukki02.juteukki_week02.security.UserDetailsImpl;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        return "index";
//    }
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
