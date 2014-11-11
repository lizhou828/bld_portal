package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-6-5
 * Time: 下午3:26
 */
@Entity
@Table(name="TTXS_SHOP_GOODS")
public class Goods extends JPASupport {
    private static Log log = LogFactory.getLog(Goods.class);

    private static Gson gson = new Gson();

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "GOODSID")
    public String id;

    @Column(name = "PRODUCTSN")
    public String goodsNo;//商品编号

    @Column(name = "GOODSNAME")
    public String name;//商品名称

    @Column(name = "PRODUCTPRICE")
    public double goodsPrice;//商品价格

    @Column(name = "MARKETPRICE")
    public double marketPrice;//市场价格

    @Column(name = "CREATEDATE")
    public Date createDate;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    public Product product;//类别


    @Column(name = "STOCKNUM")
    public int stockNum;//库存

    @Column(name = "PRODUCTPOINT")
    public int goodsPoint;//商品积分

    @Column(name = "ISADDED")
    public Boolean isAdd;//是否上架

    @Lob
    @Column(name = "PRODUCTDESCRIPTION")
    public String goodsDes;//商品描述

    @Column(name = "PAGEKEYWORDS")
    public String pageKeyWords;//页面关键字

    @Column(name = "PAGEDESCRIPT")
    public String pageDes;//页面描述

    @Column(name = "PHOTOSPATH")
    public String images;//商品图片

    @ManyToOne
    @JoinColumn(name = "BRANDID")
    public Brand brand;//品牌

    @Column(name = "PRODUCTAREA")
    public String goodsArea;//产地

    @Column(name = "CODE")
    public String code;//条形码

    @Column(name = "SALES")
    public int sales = 0;     //销量

    @Column(name = "click")
    public int click = 0;//浏览次数


    @Column(name = "ISBOUTIQUE")
    public Boolean isBest;

    @Column(name = "ISNEW")
    public Boolean isNew;

    @Column(name = "ISHOT")
    public Boolean isHot;

    @Column(name = "PRODUCTWEIGHT")
    public double weight;

    @Column(name = "WEIGHTUNIT")
    public String weightUnit;


    public final static int HOT_COUNT = 6;
    public final static int NEWGOODS_COUNT = 6;

    public static Page<Goods> findByProductId(String productId, Integer page, Integer pageSize,String orderColumn) {
        String hql = "from Goods g where g.product.id = ? and g.isAdd = true";
        if(orderColumn != null && !"".equals(orderColumn)){
            hql = hql + " order by " + orderColumn + " asc";
        }
        List<Goods> list = find(hql,productId).fetch(page, pageSize);
        hql = "select count(*) from Goods g where g.product.id = ?";
        long totalCount = count(hql,productId);
        return new Page<Goods>(page,pageSize,totalCount,list);
    }

    public static Page<Goods> findByProductList(List<Product> list,Integer page,Integer pageSize,String orderColumn){
        List<String> productIdList = new ArrayList<String>();
        for(Product product1 : list){
            productIdList.add(product1.id);
        }
        StringBuilder hqlString = new StringBuilder("from Goods g where g.isAdd = true and g.product.id in (");
        hqlString = Goods.togetherParameters(hqlString,productIdList);
        if(orderColumn != null && !"".equals(orderColumn)){
            hqlString.append(" order by ").append(orderColumn).append(" asc");
        }
        List<Goods> goodsList = find(hqlString.toString()).fetch(page,pageSize);
        hqlString = new StringBuilder("select count(*) from Goods g where g.isAdd = true and g.product.id in (");
        hqlString = Goods.togetherParameters(hqlString,productIdList);
        long  totalCount = count(hqlString.toString());
        return new Page<Goods>(page,pageSize,totalCount,goodsList);
    }

    private static StringBuilder togetherParameters(StringBuilder hql, List<String> productIdList) {
        for(int x = 0;x<productIdList.size();x++){
            if(x != 0){
                hql.append(",");
            }
            hql.append("'").append(productIdList.get(x)).append("'");
        }
        return hql.append(")");
    }


    public static Page<Goods> findByKey(String key, Integer page, Integer pageSize,String orderColumn) {
        String hql = "from Goods g where g.name like '%" + key + "%' and g.isAdd = true";
        if(orderColumn != null && !"".equals(orderColumn)){
            hql = hql + " order by " + orderColumn + " asc";
        }
        List<Goods> list = find(hql).fetch(page,pageSize);
        hql = "select count(*) from Goods g where g.name like '%" + key + "%' and g.isAdd = true";
        long totalCount = count(hql);
        return new Page<Goods>(page,pageSize,totalCount,list);
    }

    //获取商品数量最多的前 num个 类别
    public static List<Object> findTopCategoryByNum(int num){
        String hql = "select g.product.id from Goods g group by g.product.id order by count(g.product.id) desc";
        return find(hql).fetch().subList(0,num);
    }
    //

    //根据商品类别id 获取前10个商品,yi

    public static List<Goods> findNewByProductId(String productId,String orderColumn){
        String hql = "from Goods where product.id = ? and isNew = true and rownum <= 10";
        if(orderColumn != null && !"".equals(orderColumn)){
            hql = hql + " order by " + orderColumn + " desc";
        }
        List<Goods> goodsList =  find(hql,productId).fetch();
        //获取首图
        if(goodsList != null && goodsList.size() != 0){
            String images = "";
            Gson gson = new Gson();
            for(Goods goods :goodsList){
                images = goods.images ;
                try{
                    List<String> imageList =gson.fromJson(images, new TypeToken<List<String>>() {}.getType());
                    images = imageList.get(0);
                }catch (Exception e){
                    images = "";
                    log.info(e);
                }
                goods.images = images;

            }
        }
        return  goodsList ;
    }



    public static List<Goods> findHot() {
        String hql = "from Goods g where g.isHot = true and g.isAdd = true order by g.createDate desc";
        return find(hql).fetch(1,HOT_COUNT);
    }

    public static List<Goods> findNewGoodsByProductId(String productId) {
        String hql ="from Goods g where g.isNew =true and g.product.id = ? and g.isAdd = true order by createDate";
        return find(hql,productId).fetch(NEWGOODS_COUNT);

    }

    public   List<String> getImagePaths(){
        try{
            return new Gson().fromJson(images,new TypeToken<List<String>>(){}.getType());
        }catch (Exception e){
            return null;
        }
    }
    
    public  static  List<String> jsonToArray(String json){
        if(json == null)json = "";
        List<String> goodsList = gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
        if(goodsList == null)goodsList = new ArrayList<String>();
        return goodsList;
    }

    public static String arrayToJson(List<String> list){
        return  gson.toJson(list);
    }
}
