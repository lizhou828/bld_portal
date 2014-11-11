package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-6
 * Time: 上午10:59
 */
public class ArticleType {

    public static final int JZFU = 0;

    public static final int XHFU = 1;

    public static final int JXFU = 2;

    public static final int LYFU = 3;

    public static final int BZZX = 4;

    public static final int PSFS = 5;

    public static final int KHFW = 6;

    public static final int HYZX = 7;

    public static final int ZSZQ = 8;

    public static final int GSJS = 9;

    public static final int SCGG = 10;

    public static final int ZCXY = 11;

    public static Map<Integer,String> map = new HashMap<Integer,String>();

    static {
        map.put(JZFU,"家政服务");
        map.put(XHFU,"鲜花服务");
        map.put(JXFU,"驾校服务");
        map.put(LYFU,"旅游服务");
        map.put(BZZX,"帮助中心");
        map.put(PSFS,"配送方式");
        map.put(KHFW,"客户服务");
        map.put(HYZX,"会员中心");
        map.put(ZSZQ,"招商专区");
        map.put(GSJS,"公司介绍");
        map.put(SCGG,"商城公告");
        map.put(ZCXY,"注册协议");
    }
}
