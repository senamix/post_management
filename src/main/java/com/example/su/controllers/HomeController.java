package com.example.su.controllers;

import com.example.su.dtos.UserDto;
import com.example.su.services.IPostService;
import com.example.su.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    /*
    - Model is a interface
    - ModelMap is class extending from Model
    - ModelAndView is a container holding a model and a view
     */

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("HomeSite");
    }

    @GetMapping("/notify")
    public ModelAndView denyAccess(){
        return new ModelAndView("NotifySite");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("LoginSite");
    }

    @GetMapping("/register")
    public ModelAndView registerForm(Model model) {
        model.addAttribute("user",new UserDto());
        return new ModelAndView("RegisterSite");
    }

    @PostMapping("/register")
    public ModelAndView register(@Validated @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("RegisterSite");
        }
        UserDto user = userService.save(userDto);
        if(user!= null){
            return new ModelAndView("redirect:/login?register_success");
        }else{
            return new ModelAndView("redirect:/register?account_existed");
        }
    }
}
