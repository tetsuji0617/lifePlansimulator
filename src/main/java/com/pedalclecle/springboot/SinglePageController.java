package com.pedalclecle.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Hello Springboot controller
 * @author tetsuji0617
 *
 */
@Controller
public class SinglePageController {
    @GetMapping("/**/{path:[^.]*}")
    public String any() {
        return "forward:/index.html";
    }
}