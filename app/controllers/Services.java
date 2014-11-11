package controllers;

import models.Article;
import models.ArticleType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-4
 * Time: P.M.3:44
 * 便民服务
 */
public class Services extends Application{
    public static void index(){
        List<Article> jzfu = Article.findByType(ArticleType.JZFU,20);
        List<Article> xhfu = Article.findByType(ArticleType.XHFU,20);
        List<Article> jxfu = Article.findByType(ArticleType.JXFU,20);
        List<Article> lyfu = Article.findByType(ArticleType.LYFU,20);
        render(jzfu,xhfu,jxfu,lyfu);
    }

    public static void show(String id){
        Article article = Article.findById(id);
        render(article);
    }

    public static void help(String id){
        Article article = Article.findById(id);
        List<Article> articles = Article.findByType(article.type,4);
        render(article,articles);
    }
}
