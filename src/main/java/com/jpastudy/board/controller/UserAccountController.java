package com.jpastudy.board.controller;

import com.jpastudy.board.Service.UserAccountService;
import com.jpastudy.board.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/userJoinForm")
    public String addForm(){
        return "/userJoinForm";
    }

    @PostMapping("/userJoinForm")
    public String createUser(@ModelAttribute UserAccountDto user){
        userAccountService.joinUser(user);
        return "redirect:/board";
    }



/*
    @GetMapping("/userLoginForm")
    public String longin(){
        return "/userLoginForm";
    }
*/


/*
    @GetMapping("/userList")
    public String findAllUser(Model model){
        List<UserAccountDto> userAccountDtos = userAccountService.findAll();
        model.addAttribute("users", userAccountDtos);
        return "/userList";
    }
*/
}
