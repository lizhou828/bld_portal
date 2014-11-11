package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-6-18
 * Time: 下午3:57
 */
public class Orders extends Application {

    public static void cartList(){
        Gson gson = new Gson();
        String cartJson = session.get(CART_KEY);
        if(cartJson == null || cartJson.equals("[]")){
            cartJson = "[]";
        }
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String goodsId : goodsList){
            Integer count = map.get(goodsId);
            if(count == null)map.put(goodsId,1);
            else map.put(goodsId,count + 1);
        }
        Map<Goods,Integer> cartMap = new HashMap<Goods,Integer>();
        double countPrice = 0;
        for (Map.Entry<String,Integer> entry : map.entrySet()){
             String key = entry.getKey();
             Goods goods = Goods.findById(key);
             Integer value = entry.getValue();
             countPrice = countPrice + goods.goodsPrice * value;
             cartMap.put(goods,value);
        }
        render(cartMap,countPrice);
    }

    public static void removeGoods(String goodsId){
        Gson gson = new Gson();
        String cartJson = session.get(CART_KEY);
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        while (goodsList.contains(goodsId)){
            goodsList.remove(goodsId);
        }
        session.put(CART_KEY,gson.toJson(goodsList));
        cartList();
    }

    public static void add(String goodsId,double price){
        Goods goods = Goods.findById(goodsId);
        Gson gson = new Gson();
        String cartJson = session.get(CART_KEY);
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        goodsList.add(goodsId);
        session.put(CART_KEY, gson.toJson(goodsList));
        renderJSON(goods.goodsPrice + price);
    }

    public static void reduct(String goodsId,double price){
        Goods goods = Goods.findById(goodsId);
        Gson gson = new Gson();
        String cartJson = session.get(CART_KEY);
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        goodsList.remove(goodsId);
        session.put(CART_KEY,gson.toJson(goodsList));
        renderJSON(price - goods.goodsPrice);
    }

    public static void orderConfirm(){
        Gson gson = new Gson();
        String cartJson = session.get(CART_KEY);
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String goodsId : goodsList){
            Integer count = map.get(goodsId);
            if(count == null)map.put(goodsId,1);
            else map.put(goodsId,count + 1);
        }
        Map<Goods,Integer> cartMap = new HashMap<Goods,Integer>();
        double countPrice = 0;
        double countWeight = 0;
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            Goods goods = Goods.findById(key);
            Integer count = entry.getValue();
            countPrice = countPrice + goods.goodsPrice * count;
            countWeight = countWeight + DeliveryType.calculateWeight(goods,count);
            cartMap.put(goods,count);
        }
        DeliveryType deliveryType = DeliveryType.findByPro(session.get(PROVINCE));
        double freight = DeliveryType.calculateFreight(deliveryType,countPrice,countWeight);
        Member member = Member.findByUserName(session.get(USER));
        List<Address> list = Address.findByMemberId(member.memberId);
        render(cartMap,countPrice,list,freight);
    }

    public static void orderCommit(String addressId,double freight){
        Gson gson = new Gson();
        Order order = new Order();
        Address address1 = Address.findById(addressId);
        order.orderNum = System.currentTimeMillis() + "";
        order.shipAddress = address1.address;
        order.shipMobile = address1.mobileNumber;
        order.shipPhone = address1.phoneNumber;
        order.shipName = address1.name;
        order.shipZipCode = address1.zipCode;
        order.member = address1.member;

        String cartJson = session.get(CART_KEY);
        List<String> goodsList = gson.fromJson(cartJson, new TypeToken<List<String>>() {}.getType());
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String goodsId : goodsList){
            Integer count = map.get(goodsId);
            if(count == null)map.put(goodsId,1);
            else map.put(goodsId,count + 1);
        }
        double countPrice = 0;
        List<OrderItem> list = new ArrayList<OrderItem>();
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            Goods goods = Goods.findById(key);
            Integer value = entry.getValue();
            countPrice = countPrice + goods.goodsPrice * value;
            OrderItem orderItem = new OrderItem();
            orderItem.goods = goods;
            orderItem.goodsNum = value;
            orderItem.order = order;
            list.add(orderItem);
        }
        order.totalPrice = countPrice + freight;
        order.count = map.size();
        order.status = OrderStatus.NOT_PAY;
        order.create();
        for (OrderItem orderItem : list){
            orderItem.create();
        }
        session.remove(CART_KEY);
        session.remove(PROVINCE);
        render(order);
    }

    public static void myOrder(){
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        Page<Order> page = Order.findByMemberId(member.memberId,getPage(),getPageSize());
        render(page);
    }
}
