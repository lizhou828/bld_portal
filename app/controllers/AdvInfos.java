package controllers;

import models.AdvInfo;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-18
 * Time: a.m.11:30
 * 广告信息
 */
public class AdvInfos extends Application {

    public static void indexBigPic(String codeName ,Integer num){
        List<AdvInfo> advInfoList =AdvInfo.findByLocation(codeName,num);
        renderJSON(advInfoList);
    }
    public static void findAdvByLocation(String locationName){
        AdvInfo adv =AdvInfo.findByLocation(locationName);
        renderJSON(adv);
    }


}
