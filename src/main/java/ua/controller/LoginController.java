package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.service.UserService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Adevi on 8/8/2014.
 */
@Controller
@RequestMapping("anon")
public class LoginController {

    @Autowired
    private UserService userService;

    // Spring Security see this :
    @RequestMapping(value = "/login", method = GET)
    public ModelAndView getLoginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "register", required = false) String register) {

        ModelAndView model = new ModelAndView();
        if (error != null)
            model.addObject("error", "Invalid username and password!");

        if (logout != null)
            model.addObject("msg", "You've been logged out successfully.");
        else if (register != null)
            model.addObject("msg", "Registered successfully, now you can login");

        model.setViewName("login");
        return model;
    }


}
