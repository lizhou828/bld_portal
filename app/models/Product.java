package models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-6-5
 * Time: 上午9:41
 */
@Entity
@Table(name="TTXS_SHOP_GOODESCATEGORY")
public class Product extends JPASupport{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "CATEGORYID")
    public String id;//主键

    @Column(name = "CATEGORYNAME")
    public String name;    //名称


    @ManyToOne()
    @JoinColumn(name = "PARENTCATEGORYID")
    @NotFound(action= NotFoundAction.IGNORE)//上级类别
    public Product product;

    @Column(name = "ISHOT", columnDefinition = "number(1)")  //是否热门
    public Integer isHot;

    @Column(name = "GOODSNUM")
    public Integer goodsNum;//商品数量

    @Column(name="ORDERLIST")
    public Integer order;

    @Column(name = "ISRECOMMEND")
    public Integer isRecommend;

    @Column(name = "recommendArea")
    public String recommendArea;

    //找到所有热销的类别
    public static List<Product> findByHot(){
        String hql = "select * from Product where isHot = 1  order by goodsNum desc";
        return find(hql).fetch();
    }

    //获取推荐到首页的类别
    public static Product findRecommendProduct(String recommendArea) {
        String hql = "from Product where product is null and isRecommend = 1 and recommendArea = ? ";
        return find(hql,recommendArea).first();
    }

    //首页获取所有 有商品的一级类别
    public static List<Product> findIndexProduct(Long num) {
        String hql = "from Product p where  p.product is null  and rownum <= ? order by order";
        return  find(hql,num).fetch();

    }

    //根据父类别id 获取所有的子分类
    public static List<Product> findByParentCategory(String parentId){
        String hql = "from Product p where p.product.id = ?";
        return find(hql,parentId).fetch();
    }

    /**
     * 根据所有的父类别，找到所有的子类别
     * @param products
     * @return
     */
    public static Map<String, List<Product>> findSubProduct(List<Product> products) {
        if(products == null && products.size() <= 0 ){
            return null;
        }
        List<Product> temp = null ;
        Map<String ,List<Product>> map = new HashMap<String, List<Product>>();
        for(Product p:products){
            temp = findByParentCategory(p.id);
            map.put(p.id,temp);
        }
        return map;
    }
}
