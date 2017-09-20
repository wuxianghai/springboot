package com.springboot.Service;

import com.springboot.Dao.ArticleDao;
import com.springboot.bean.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Hardy.wu on 2017/2/9.
 */
@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    /**
    * @description: 查询文章
    * @author hardy.wu
    * @Date 2017/2/10 10:48
    * @param
    */
    public List<Article> getArticleAll(){
        List<Article> article = articleDao.getAll();
        return article;
    }

    /**
    * @description: 新增文章
    * @author hardy.wu
    * @Date 2017/2/10 10:51
    * @param
    */
    public void insertArticle(Date time){
        articleDao.insertArticle(time);
    }

    public String getall(){
        return articleDao.getall();
    }
}
