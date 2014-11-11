package controllers;

import models.Goods;
import models.Page;
import models.Product;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-6-8
 * Time: 上午10:18
 */
public class Goodses extends Application {



    public static void show(String id){
        Goods goods = Goods.findById(id);
        List<Goods> hots = Goods.findHot();
        render(goods,hots);
    }

    public static void list(String productId,String orderColumn){
        Product product = Product.findById(productId);
        List<Product> productList = Product.findByParentCategory(productId);
        Page<Goods> page = null;
        if(productList == null || productList.size() == 0){
            page = Goods.findByProductId(productId, getPage(), getPageSize(), orderColumn);
        }else{
            page = Goods.findByProductList(productList,getPage(),getPageSize(),orderColumn);
        }
        List<Goods> newGoods = Goods.findNewGoodsByProductId(productId);
        render(page, productId, orderColumn, product,newGoods);
    }

    public static void addCart(String goodsId,String province) throws UnsupportedEncodingException {
        Goods goods = Goods.findById(goodsId);
        String cart = session.get(CART_KEY);
        List<String> goodsList = Goods.jsonToArray(cart);
        goodsList.add(goods.id);
        String json = Goods.arrayToJson(goodsList);
        session.put(CART_KEY, json);
        province = URLDecoder.decode(province,"UTF-8");
        session.put(PROVINCE,province);
        renderJSON(goodsList.size());
    }
}
