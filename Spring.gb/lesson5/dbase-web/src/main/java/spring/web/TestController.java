package spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method=RequestMethod.GET)
    public String message(Model uiModel){
        uiModel.addAttribute("message", "Web-level works!");
        return "test";
    }
}