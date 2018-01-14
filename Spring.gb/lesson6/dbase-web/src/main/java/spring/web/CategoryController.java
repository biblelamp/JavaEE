package spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import spring.domain.Article;
import spring.domain.Category;
import spring.service.ArticleService;
import spring.service.CategoryService;
import spring.web.ajax.ArticlesAjax;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String view(@PathVariable("id") Long id,Model uiModel) {
        Category category = categoryService.get(id);
        uiModel.addAttribute("category", category);
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("categories",categories);
        return "category/view";
    }

    @RequestMapping(value="/{id}/articles_ajax",method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ArticlesAjax viewAjax(@PathVariable("id") Long id, @RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy) {

        Sort sort = null;
        if (order.equalsIgnoreCase("DESC")) {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        PageRequest pageable = new PageRequest(pageCounter,number, sort);
        Page<Article> articlePage = articleService.getByCategoryId(id, pageable);
        ArticlesAjax responsive =new  ArticlesAjax();
        responsive.setArticles(Lists.newArrayList(articlePage.iterator()));

        return responsive;
     }
}