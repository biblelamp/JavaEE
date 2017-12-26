package dbase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dbase.domain.Category;
import dbase.service.CategoryService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method=RequestMethod.GET)
    public String home(Model uiModel) {
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("categories", categories);
        return "home/main";
    }    
}