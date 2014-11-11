package controllers;

import models.MiaoSha;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-6-19
 * Time: p.m.5:13
 * 商品秒杀
 */


public class MiaoShas extends Application {
    //秒杀首页
    public static void index(){
        render();
    }

    //秒杀时间到期
    public static void timeOver(String id){
        MiaoSha miaoSha = MiaoSha.findById(id);
        miaoSha.lastModifyTime = new Date();
        miaoSha.state = "2";
        miaoSha.save();
        renderText(miaoSha.state);
    }
}
