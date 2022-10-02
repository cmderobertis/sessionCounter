package net.cmderobertis.sessioncounter.controllers;

import net.cmderobertis.sessioncounter.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MainController {
    @RequestMapping("/")
    String index(HttpSession session) {
        if (session.getAttribute("counter") == null) {
            session.setAttribute("counter", 1);
        } else {
            session.setAttribute("counter", (Integer)session.getAttribute("counter") + 1);
        }
        return "index.jsp";
    }

    @RequestMapping("/counter")
    String counter(HttpSession session, Model model) {
        if (session.getAttribute("counter") == null) {
            model.addAttribute("counter", 0);
        } else {
            model.addAttribute("counter", session.getAttribute("counter"));
        }
        return "counter.jsp";
    }

    @RequestMapping("/increment2")
    String increment2(HttpSession session, Model model) {
        session.setAttribute("counter", (Integer)session.getAttribute("counter") + 2);
        return "increment2.jsp";
    }

    @RequestMapping("/reset")
    RedirectView reset(HttpSession session, Model model) {
        session.setAttribute("counter", 0);
        model.addAttribute("counter", session.getAttribute("counter"));
        return new RedirectView("/counter");
    }
}
