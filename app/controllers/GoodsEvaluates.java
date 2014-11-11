package controllers;

import models.Goods;
import models.GoodsEvaluate;
import models.Member;

import java.util.Date;

/**
 * User: Liujy
 * Date: 13-7-4
 * Time: 上午10:18
 */
public class GoodsEvaluates extends Application {

   public static void evaluateUI(String goodsId,String orderId){
      Goods goods = Goods.findById(goodsId);
      render(goods,orderId);
   }


    public static void evaluate(String comment,String goodsId,String orderId){
        GoodsEvaluate goodsEvaluate = new GoodsEvaluate();
        goodsEvaluate.comment = comment;
        goodsEvaluate.createDate = new Date();
        goodsEvaluate.goods = Goods.findById(goodsId);
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        goodsEvaluate.member = member;
        goodsEvaluate.create();
        OrderItems.list(orderId);
    }
}
