package com.springboot.Controller;

import com.google.gson.Gson;
import com.springboot.Service.ArticleService;
import com.springboot.bean.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Hardy.wu on 2017/2/8.
 */
@Controller
@SpringBootApplication
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    /**
     * @description: 进入博客页面
     * @author hardy.wu
     * @Date 2017/2/10 15:13
     * @param
     */
    @RequestMapping("/newIndex")
    public String newIndex(){

        return "index";
    }

    /**
    * @description: 进入博客页面
    * @author hardy.wu
    * @Date 2017/2/10 15:13
    * @param
    */
    @RequestMapping("/index1")
    public String index(Model model){
        List<Article> article_list = articleService.getArticleAll();
        model.addAttribute("name","我就是无敌");
        model.addAttribute("article_list",article_list);
        return "index1";
    }

    /**
    * @description: 获取文章信息
    * @author hardy.wu
    * @Date 2017/2/10 15:13
    * @param
    */
    @RequestMapping("/getArticle")
    public void getArticle(HttpServletRequest request, HttpServletResponse response){
        List<Article> article = articleService.getArticleAll();
        System.out.println(article);
        Gson gson = new Gson();
        String json = gson.toJson(article);
        try {
            response.getWriter().print(json);
        }catch (Exception e){}

    }

    /**
    * @description: 新增文章
    * @author hardy.wu
    * @Date 2017/2/10 10:52
    * @param
    */
    @RequestMapping("/insertArtircle")
    public void insertArticle(){
        Date time = new Date();
        articleService.insertArticle(time);
    }

    @RequestMapping("/getall")
    public String getall(){
        return articleService.getall();
    }
}

