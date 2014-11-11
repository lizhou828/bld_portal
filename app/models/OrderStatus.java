package models;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-6-24
 * Time: 下午2:39
 */
public class OrderStatus {
    public final static int NOT_PAY = 0;//未支付
    public final static int PEI_SONG = 1;//正在配送
    public final static int IS_FAHUO = 2;//已发货
    public final static int WAN_CHEN = 3;//已完成
    public final static int QU_XIAO = 4;//取消

    public final static Map<Integer,String> map = new HashMap<Integer, String>();
    static {
        map.put(NOT_PAY,"未支付");
        map.put(PEI_SONG,"正在配送");
        map.put(IS_FAHUO,"已发货");
        map.put(WAN_CHEN,"已完成");
        map.put(QU_XIAO,"取消");
    }
}
