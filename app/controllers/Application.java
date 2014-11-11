package controllers;

import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Application extends Controller {
    protected static String uploadDir = Play.configuration.getProperty("upload.dir", "/data/upload");
    private static Log log = LogFactory.getLog(Application.class);
    protected final static String CART_KEY = "CART_KEY";
    protected final static String PROVINCE = "PROVINCE";
    protected static final String USER = "user";
    public static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    public static Integer getPageSize() {
        return request.params.get("pageSize") == null ? 40 : Integer.valueOf(request.params.get("pageSize"));
    }

    @Before(only = {"Orders.orderConfirm","Orders.myOrder","Members.rePassword",
            "Addresses.add","Addresses.delete","Members.address"})
    public static void isLogin() throws UnsupportedEncodingException {
       String userName = session.get(USER);
       if(userName == null){
           redirect("/Members/logonUI?url=" + URLEncoder.encode(request.url, "utf-8"));
       }
    }

    @Before(unless = {"Members.logonUI","Members.logon","Members.registerUI","Members.register"})
    public static void getUserName(){
        String userName = session.get(USER);
        renderArgs.put("userName",userName);
    }

    @Before
    public static void getCarCount(){
        List<String> list = Goods.jsonToArray(session.get(CART_KEY));
        renderArgs.put("carCount",list.size());
    }

    public static void index (){
        Integer flag = 0;
        //首页小图片轮播广告（6张大图片）
        List<AdvInfo> indexBigPic = AdvInfo.findByLocation(AdvLocationType.SYDTLB,6);
        //首页大图片轮播广告（9张小图片）
        List<AdvInfo>  indexLittlePic = AdvInfo.findByLocation(AdvLocationType.SYXTLB,9);
        //首页秒杀商品展示
        Map<Integer,MiaoSha> miaoShaMap =  MiaoSha.findByWeek(new Date());

        //首页楼层
        Map<String,Object> firstFloorMap = getFloorMap(1);
        Map<String,Object> secondFloorMap = getFloorMap(2);
        Map<String,Object> thirdFloorMap = getFloorMap(3);
        List<Map<String,Object>> floorList = new ArrayList<Map<String, Object>>();
        floorList.add(firstFloorMap);
        floorList.add(secondFloorMap);
        floorList.add(thirdFloorMap);

        //商城公告
        List<Article> articles = Article.findByType(ArticleType.SCGG, 7);
        //首页菜单标记
        flag=1;
        render(flag, floorList, miaoShaMap, articles, indexLittlePic, indexBigPic);
    }

    private static Map<String ,Object> getFloorMap(Integer floorNO){
        Map<String,Object> map = new HashMap<String, Object>();
        Product floorProduct = null;
        List<GoodsRecommend> floorGoodsList = null;
        AdvInfo floorLongAdv = null;
        AdvInfo floorLeftAdv = null;
        if(floorNO == 1){
            floorProduct = Product.findRecommendProduct(AdvLocationType.INDEX_FIRST_FLOOR);
            if(floorProduct == null ){
                floorGoodsList = null;
            }else {
                floorGoodsList = GoodsRecommend.findByProductAndArea(floorProduct.id,AdvLocationType.INDEX_FIRST_FLOOR);
            }
            floorLongAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_1FLOOR_LONG_ADV);
            floorLeftAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_1FLOOR_LEFT_ADV);
        }
        if(floorNO == 2){
            floorProduct = Product.findRecommendProduct(AdvLocationType.INDEX_SECOND_FLOOR);
            if(floorProduct == null ){
                floorGoodsList = null;
            }else {
                floorGoodsList = GoodsRecommend.findByProductAndArea(floorProduct.id,AdvLocationType.INDEX_SECOND_FLOOR);
            }
            floorLongAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_2FLOOR_LONG_ADV);
            floorLeftAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_2FLOOR_LEFT_ADV);
        }
        if(floorNO == 3){
            floorProduct = Product.findRecommendProduct(AdvLocationType.INDEX_THIRD_FLOOR);
            if(floorProduct == null ){
                floorGoodsList = null;
            }else {
                floorGoodsList = GoodsRecommend.findByProductAndArea(floorProduct.id,AdvLocationType.INDEX_THIRD_FLOOR);
            }
            floorLongAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_3FLOOR_LONG_ADV);
            floorLeftAdv = AdvInfo.findByLocation(AdvLocationType.INDEX_3FLOOR_LEFT_ADV);
        }
        map.put("Product",floorProduct);
        map.put("GoodsRecommendList",floorGoodsList);
        map.put("LongAdv",floorLongAdv);
        map.put("LeftAdv",floorLeftAdv);
        return map;
    }

    public static void search(String key,String orderColumn){
        Page<Goods> page = Goods.findByKey(key,getPage(),getPageSize(),orderColumn);
        render("/Goodses/list.html",page,key);

    }
    public static void point(){
        render();
    }

    @Before
    public static void before(){
        //首页类别（动态获取）
        List<Product> products = Product.findIndexProduct(14L);
        Map<String,List<Product>> subProductsMap = Product.findSubProduct(products);

        List<Article> bzzx = Article.findByType(ArticleType.BZZX,4);
        List<Article> psfs = Article.findByType(ArticleType.PSFS,4);
        List<Article> khfw = Article.findByType(ArticleType.KHFW,4);
        List<Article> zszq = Article.findByType(ArticleType.ZSZQ,4);
        List<Article> gsjs = Article.findByType(ArticleType.GSJS,4);
        List<Article> hyzx = Article.findByType(ArticleType.HYZX,4);
        renderArgs.put("bzzx",bzzx);
        renderArgs.put("psfs",psfs);
        renderArgs.put("khfw",khfw);
        renderArgs.put("zszq",zszq);
        renderArgs.put("gsjs",gsjs);
        renderArgs.put("hyzx",hyzx);
        renderArgs.put("products",products);
        renderArgs.put("subProductsMap", subProductsMap);
    }

}