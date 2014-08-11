package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adevi on 8/9/2014.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/student/exam";
    }
}
