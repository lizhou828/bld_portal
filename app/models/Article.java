package models;

import play.db.jpa.JPASupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-6
 * Time: 上午10:55
 */
@Entity
@Table(name="TTXS_SHOP_ARTICLE")
public class Article extends JPASupport{
    @Id
    @Column(name = "ID")
    public String id = UUID.randomUUID().toString().replace("-","");

    @Column(name = "title")
    public String title;

    @Column(name = "text")
    public String text;

    @Column(name = "type")
    public Integer type;

    @Column(name = "create_time")
    public Date createTime;

    @Column(name = "ORDERLIST")
    public Integer orderList;

    public static List<Article> findByType(Integer type,Integer count){
        return find("from Article where type = ? order by orderList",type).fetch(count);
    }
}
