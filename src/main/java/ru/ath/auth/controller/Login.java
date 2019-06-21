package ru.ath.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {
    private static final String USER_ID_SESSION_KEY = "user_id";
    private static final String USER_ACCESS_TOKEN = "access_token";

    @GetMapping(path = "/login")
    public String registerGet(Model model) {
        return "EnterPage";
    }

    @GetMapping(path = "/logout")
    public String logoutGet(HttpServletRequest request, HttpSession httpSession, Model model) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
        httpSession.removeAttribute(USER_ACCESS_TOKEN);
        return "redirect:/";
    }


    @PostMapping(path = "/login")
    public String registerPost(HttpSession httpSession) {
        return "redirect:/";
    }
}
