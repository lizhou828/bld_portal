package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-17
 * Time: A.M.9:24
 * 广告位置常量类
 */
public class AdvLocationType {
    //广告位置
    public static final String SYDTLB = "index_big_pic_ppt";//首页大图片轮播
    public static final String SYXTLB = "index_little_pic_ppt";//首页小图片轮播
    public static final String SYCTGG = "index_long_adv";//首页长条广告
    public static final String SYZCGG = "index_left_adv";//首页左侧广告
    public static final String INDEX_1FLOOR_LONG_ADV="index_1floor_long_adv"; //首页1楼长条广告
    public static final String INDEX_2FLOOR_LONG_ADV="index_2floor_long_adv"; //首页2楼长条广告
    public static final String INDEX_3FLOOR_LONG_ADV="index_3floor_long_adv"; //首页3楼长条广告
    public static final String INDEX_1FLOOR_LEFT_ADV="index_1floor_left_adv"; //首页1楼左侧广告
    public static final String INDEX_2FLOOR_LEFT_ADV="index_2floor_left_adv"; //首页2楼左侧广告
    public static final String INDEX_3FLOOR_LEFT_ADV="index_3floor_left_adv"; //首页3楼左侧广告


    //首页楼层位置
    public static final String INDEX_FIRST_FLOOR = "index_first_floor";
    public static final String INDEX_SECOND_FLOOR = "index_second_floor";
    public static final String INDEX_THIRD_FLOOR = "index_third_floor";

    public static Map<String,String> map = new HashMap<String, String>();

    static {
        map.put(INDEX_FIRST_FLOOR,"首页1楼");
        map.put(INDEX_SECOND_FLOOR,"首页2楼");
        map.put(INDEX_THIRD_FLOOR,"首页3楼");
    }

}
