package controllers;

import models.Product;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-6-5
 * Time: 上午10:52
 */
public class Products extends Application {

    public static void list(){
        List<Product> list = Product.findAll();
        System.out.print(list);
    }
}
