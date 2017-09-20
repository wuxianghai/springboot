package com.springboot.Dao;

import com.springboot.bean.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Hardy.wu on 2017/2/8.
 */
    @Mapper
public interface ArticleDao {

    @Select("select * from article ")
    List<Article> getAll();

    @Insert("insert into article (title, type, lable, addTime, click_number) value('文章','spring','boot',#{time},'15')")
    void insertArticle(Date time);

    @Select("SELECT T.character_name FROM RPT_D_CHARACTER T where t.CHARACTER_WID=1001")
    String getall();
    
}
