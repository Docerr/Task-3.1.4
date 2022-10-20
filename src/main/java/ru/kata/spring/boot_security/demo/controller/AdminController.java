package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("admin", userService.getUserByName(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", new User());
        return "index";
    }

//    @GetMapping(value = "/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "index";
//    }

    @PostMapping("/new")
    public String addUser(User user,@RequestParam("listRoles") long[] role_id) {
        userService.addUser(user, role_id);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/update")
//    public String editUser(@PathVariable Long id, Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("users", userService.getUserById(id));
//        model.addAttribute("roles", roleService.getRoles());
//        return "index";
//    }

    @PatchMapping(value = "/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam ("listRoles") long[] roleId) {
        userService.updateUser(user, roleId);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "index";
//    }
}
