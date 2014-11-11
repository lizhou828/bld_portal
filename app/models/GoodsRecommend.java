package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-8-2
 * Time: P.M.12:44
 * 商品推荐实体
 */
@Entity
@Table(name="TTXS_ADMIN_RECOMMEND")
public class GoodsRecommend extends JPASupport{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "RECOMMENDID")
    public String id;

    @ManyToOne()
    @JoinColumn(name = "goodsid")
    public Goods goods;

    @Column(name = "GOODESCATEGORYID")
    public String categoryId;

    @Column(name = "RECOMMENDAREA")
    public String recommendArea;

    @Column(name = "RECOMMENDTYPE")
    public String recommendType;//推荐类型 1 表示商品推荐

    @Column(name = "RECOMMENDSTATE")
    public String recommendState; //推荐状态：1 表示推荐， 0 表示不推荐

    @Column(name = "ORDERLIST")
    public Integer orderList;

    @ManyToOne
    @JoinColumn(name = "USERID")
    public Admin admin;

    @ManyToOne
    @JoinColumn(name = "LASTMODIFYUSERID")
    public Admin lastModifyUser;

    @JoinColumn(name = "CREATEDATE")
    public Date createDate;

    @JoinColumn(name = "LASTMODIFYDATE")
    public Date lastModifyDate;


    public static List<GoodsRecommend> findByProductAndArea(String categoryId, String recommendArea) {
        String hql = "from GoodsRecommend g where g.categoryId =? and recommendArea = ? "
                +" and recommendType='1' and recommendState='1' order by orderList ";
        return find(hql,categoryId,recommendArea).fetch();

    }
}
