package controllers;

import models.OrderItem;

import java.util.List;

/**
 * User: administrator
 * Date: 13-7-5
 * Time: 上午10:29
 */
public class OrderItems extends Application {

    public static void list(String orderId){
        List<OrderItem> list = OrderItem.findByOderId(orderId);
        render(list,orderId);
    }
}
