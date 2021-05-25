package com.hostel.controller.Watchman;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hostel.Constants;
import com.hostel.dao.WatchmanDao;
import com.hostel.model.Watchman;
import com.hostel.service.WatchmanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/watchman")
@Slf4j
public class LoginController {

    private final WatchmanService watchmanService;

    @RequestMapping(path = "/login", method = POST)
    public ModelAndView doLogin(LoginForm form, HttpSession session) {
        log.debug("FORM: {}", form);
        Watchman watchman = watchmanService
                .findCurrentWatchman(Watchman.builder().username(form.getUsername())
                        .password(form.getPassword()).build());
        log.debug("FOUND WATCHMAN: {}", watchman);
        if (watchman == null) {
            ModelAndView modelAndView = new ModelAndView("watchman/login");
            modelAndView.addObject("errorMessage", "Login or password is incorrect");
            return modelAndView;
        }
        session.setAttribute(Constants.CURRENT_DOCTOR, watchman);
        return new ModelAndView("redirect:/watchman/index");
    }

    @RequestMapping(path = "/login", method = GET)
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("watchman/login");
        LoginForm form = new LoginForm();
        modelAndView.addObject("loginForm", form);
        return modelAndView;
    }

}
