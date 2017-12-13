package dbase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dbase.domain.Article;
import dbase.domain.Author;
import dbase.service.ArticleService;
import dbase.service.AuthorService;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method=RequestMethod.GET)
    public String message(Model uiModel){
        uiModel.addAttribute("message", "Web-level works!");
        return "test";
    }
}