package com.example.englishdictionaryadminui.controller;

import com.example.englishdictionaryadminui.models.User;
import com.example.englishdictionaryadminui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public String users(
            Model model
    )
    {
        if(userService.getAllUser().getStatusCode().is2xxSuccessful())
        {
            List<User> list = userService.getAllUser().getBody();
            model.addAttribute("users",list);
            return "user/users";
        } else {
            return "error/500";
        }
    }
    @GetMapping("/edit/{id}")
    public String editUser(
            Model model,
            @RequestParam("id") String id
    )
    {
        if (userService.getUserById(id).getStatusCode().is2xxSuccessful())
        {
            User user = userService.getUserById(id).getBody();
            model.addAttribute("user",user);
            return "user/editUser";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/disable/{id}")
    public String disableUser(
            Model model,
            @PathVariable("id") String id
    )
    {
        if (userService.getUserById(id).getStatusCode().is2xxSuccessful()
                && userService.disableUserById(id).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/users";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/genders")
    public String getAllGender(
            Model model
    )
    {
        if (userService.getAllGender().getStatusCode().is2xxSuccessful())
        {
            model.addAttribute("genders",userService.getAllGender().getBody());
            return "gender/genders";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/gender/edit")
    public String editGender(
            @RequestParam("id") String id,
            @RequestParam("value") String value
    )
    {
        if (userService.editGenderById(id,value).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/genders";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/gender/delete/{id}")
    public String deleteGenderById(
            @PathVariable("id") String id
    )
    {
        if(userService.deleteGenderById(id).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/genders";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/levels")
    public String getAllLevel(
            Model model
    )
    {
        if (userService.getAllLevel().getStatusCode().is2xxSuccessful())
        {
            model.addAttribute("levels",userService.getAllLevel().getBody());
            return "level/levels";
        } else {
            return "error/404";
        }
    }
    @PostMapping("/level/edit")
    public String editLevel(
            @RequestParam("id") String id,
            @RequestParam("value") String value
    )
    {
        if (userService.editLevelById(id,value).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/levels";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/level/delete/{id}")
    public String deleteLevelById(
            @PathVariable("id") String id
    )
    {
        if(userService.deleteLevelById(id).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/levels";
        }
        else {
            return "error/404";
        }
    }

    @GetMapping("/occupations")
    public String getAllOccupation(
            Model model
    )
    {
        if (userService.getAllOccupation().getStatusCode().is2xxSuccessful())
        {
            model.addAttribute("occupations",userService.getAllOccupation().getBody());
            return "occupation/occupations";
        } else {
            return "error/404";
        }
    }
    @PostMapping("/occupation/edit")
    public String editOccupation(
            @RequestParam("id") String id,
            @RequestParam("value") String value
    )
    {
        if (userService.editOccupationById(id,value).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/occupations";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/occupation/delete/{id}")
    public String deleteOccupationById(
            @PathVariable("id") String id
    )
    {
        if(userService.deleteOccupationById(id).getStatusCode().is2xxSuccessful())
        {
            return "redirect:/user/occupations";
        }
        else {
            return "error/404";
        }
    }
}
