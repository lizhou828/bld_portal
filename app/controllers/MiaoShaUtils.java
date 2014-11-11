package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.MiaoSha;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.Util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-4
 * Time: p.m.3:29
 * 秒杀工具类
 */
public class MiaoShaUtils {

    private static Log log = LogFactory.getLog(MiaoSha.class);

    /**
     * 根据当前日期是星期几,来获取本周内、每一天的所有秒杀商品
     * @param currentDate
     * @param currentWeekday
     * @return Map<Integer,Miaosha>
     *     map.put(1,miaosha)表示存储星期一的秒杀商品
     *     map.put(2,miaosha)表示存储星期二的秒杀商品
     *     map.put(3,miaosha)表示存储星期三的秒杀商品
     *     以此类推
     *     。。。。。。
     *     map.put(7,miaosha)表示存储星期天的秒杀商品
     */
    public static Map<Integer, MiaoSha> findBySunday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();
        MiaoSha miaoSha = null;
        Date date = null;

        date = getBeforeDate(currentDate,6);
        miaoSha =  findByBeforeDate( date  );
        map.put( 1 ,miaoSha);


        date = getBeforeDate(currentDate,5);
        miaoSha =  findByBeforeDate( date  );
        map.put( 2 ,miaoSha);


        date = getBeforeDate(currentDate,4);
        miaoSha =  findByBeforeDate( date  );
        map.put( 3 ,miaoSha);

        date = getBeforeDate(currentDate,3);
        miaoSha =  findByBeforeDate( date  );
        map.put( 4,miaoSha);

        date = getBeforeDate(currentDate,2);
        miaoSha =  findByBeforeDate( date  );
        map.put( 5 ,miaoSha);

        date = getBeforeDate(currentDate,1);
        miaoSha =  findByBeforeDate( date  );
        map.put( 6 ,miaoSha);

        //获取星期天的秒杀商品
        miaoSha = findByCurrentDate(currentDate);
        map.put(7,miaoSha);
        return map;
    }

    public static Map<Integer, MiaoSha> findBySaturday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();
        MiaoSha miaoSha = null;
        Date date = null;

        date = getBeforeDate(currentDate,5);
        miaoSha =  findByBeforeDate( date  );
        map.put( 1 ,miaoSha);


        date = getBeforeDate(currentDate,4);
        miaoSha =  findByBeforeDate( date  );
        map.put( 2 ,miaoSha);

        date = getBeforeDate(currentDate,3);
        miaoSha =  findByBeforeDate( date  );
        map.put(3 ,miaoSha);

        date = getBeforeDate(currentDate,2);
        miaoSha =  findByBeforeDate( date  );
        map.put( 4 ,miaoSha);

        date = getBeforeDate(currentDate,1);
        miaoSha =  findByBeforeDate( date  );
        map.put( 5 ,miaoSha);

        //获取星期六的秒杀商品
        miaoSha = findByCurrentDate(currentDate);
        map.put(6,miaoSha);

        //获取星期天的秒杀商品
        date= getAfterDate(currentDate,1);
        miaoSha  =  findByAfterDate(date);
        map.put( 7 , miaoSha);

        return map;
    }


    //当前时间是星期五
    public static Map<Integer, MiaoSha> findByFriday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();
        MiaoSha miaoSha = null;
        Date date = null;
        //获取星期一的秒杀商品
        date = getBeforeDate(currentDate,4);
        miaoSha =  findByBeforeDate( date  );
        map.put( 1 ,miaoSha);

        //获取星期二的秒杀商品
        date = getBeforeDate(currentDate,3);
        miaoSha =  findByBeforeDate( date  );
        map.put( 2 ,miaoSha);

        //获取星期三的秒杀商品
        date = getBeforeDate(currentDate,2);
        miaoSha =  findByBeforeDate( date  );
        map.put( 3 ,miaoSha);

        //获取星期四的秒杀商品
        date = getBeforeDate(currentDate,1);
        miaoSha =  findByBeforeDate( date  );
        map.put( 4 ,miaoSha);

        //获取星期五的秒杀商品
        miaoSha = findByCurrentDate(currentDate);
        map.put(5,miaoSha);

        //获取星期六的秒杀商品
        date= getAfterDate(currentDate,1);
        miaoSha  =  findByAfterDate(date);
        map.put( 6 , miaoSha);

        //获取星期天的秒杀商品
        date= getAfterDate(currentDate,2);
        miaoSha  =  findByAfterDate(date);
        map.put( 7 , miaoSha);

        return map;
    }



    public static Map<Integer, MiaoSha> findByThursday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();

        Date monday = getBeforeDate(currentDate,3);
        MiaoSha mondayMiaoSha =  findByBeforeDate( monday  );
        map.put( 1 , mondayMiaoSha );

        Date tuesday = getBeforeDate(currentDate,2);
        MiaoSha tuesdayMiaoSha =  findByBeforeDate( tuesday  );
        map.put( 2 ,tuesdayMiaoSha );

        Date wednesday = getBeforeDate(currentDate,1);
        MiaoSha wedMiaoSha =  findByBeforeDate( wednesday  );
        map.put( 3 ,wedMiaoSha );

        MiaoSha thuMiaoSha = findByCurrentDate(currentDate);
        map.put(4,thuMiaoSha);

        Date friday = getAfterDate(currentDate,1);
        MiaoSha fridayMiaoSha =  findByAfterDate(friday);
        map.put( 5 , fridayMiaoSha );

        Date saturday = getAfterDate(currentDate,2);
        MiaoSha saturdayMiaoSha =  findByAfterDate(saturday);
        map.put( 6, saturdayMiaoSha );

        Date sunday = getAfterDate(currentDate,3);
        MiaoSha sundayMiaoSha =  findByAfterDate(sunday);
        map.put( 7 , sundayMiaoSha );

        return map;
    }


    public static Map<Integer, MiaoSha> findByWednesday(Date currentDate, String today) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();

        Date monday = getBeforeDate(currentDate,2);
        MiaoSha mondayMiaoSha =  findByBeforeDate( monday  );
        map.put( 1 , mondayMiaoSha );

        Date tuesday = getBeforeDate(currentDate,1);
        MiaoSha tuesdayMiaoSha =  findByBeforeDate( tuesday  );
        map.put( 2 ,tuesdayMiaoSha );

        MiaoSha wedMiaoSha = findByCurrentDate(currentDate);
        map.put(3,wedMiaoSha);

        Date thursday = getAfterDate(currentDate,1);
        MiaoSha thursdayMiaoSha =  findByAfterDate(thursday);
        map.put( 4 , thursdayMiaoSha );

        Date friday = getAfterDate(currentDate,2);
        MiaoSha fridayMiaoSha =  findByAfterDate(friday);
        map.put(5, fridayMiaoSha );

        Date saturday = getAfterDate(currentDate,3);
        MiaoSha saturdayMiaoSha =  findByAfterDate(saturday);
        map.put( 6, saturdayMiaoSha );

        Date sunday = getAfterDate(currentDate,4);
        MiaoSha sundayMiaoSha =  findByAfterDate(sunday);
        map.put( 7 , sundayMiaoSha );

        return map;
    }

    public static Map<Integer, MiaoSha> findByTuesday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();
        MiaoSha miaoSha = null;
        Date date = null;

        date = getBeforeDate(currentDate,1);
        miaoSha =  findByBeforeDate( date  );
        map.put( 1 ,miaoSha);

        //星期二
        miaoSha = findByCurrentDate(currentDate);
        map.put(2,miaoSha);

        date= getAfterDate(currentDate,1);
        miaoSha  =  findByAfterDate(date);
        map.put( 3 , miaoSha);

        date= getAfterDate(currentDate,2);
        miaoSha  =  findByAfterDate(date);
        map.put( 4 , miaoSha);

        date= getAfterDate(currentDate,3);
        miaoSha  =  findByAfterDate(date);
        map.put(5 , miaoSha);

        date= getAfterDate(currentDate,4);
        miaoSha  =  findByAfterDate(date);
        map.put( 6 , miaoSha);

        date= getAfterDate(currentDate,5);
        miaoSha  =  findByAfterDate(date);
        map.put( 7 , miaoSha);

        return map;
    }

    public static Map<Integer, MiaoSha> findByMonday(Date currentDate, String currentWeekday) {
        Map<Integer,MiaoSha> map = new HashMap<Integer,MiaoSha>();
        MiaoSha miaoSha = null;
        Date date = null;

        //星期一
        miaoSha = findByCurrentDate(currentDate);
        map.put(1,miaoSha);

        date= getAfterDate(currentDate,1);
        miaoSha  =  findByAfterDate(date);
        map.put( 2, miaoSha);

        date= getAfterDate(currentDate,2);
        miaoSha  =  findByAfterDate(date);
        map.put( 3 , miaoSha);

        date= getAfterDate(currentDate,3);
        miaoSha  =  findByAfterDate(date);
        map.put(4 , miaoSha);

        date= getAfterDate(currentDate,4);
        miaoSha  =  findByAfterDate(date);
        map.put( 5 , miaoSha);

        date= getAfterDate(currentDate,5);
        miaoSha  =  findByAfterDate(date);
        map.put( 6 , miaoSha);

        date= getAfterDate(currentDate,6);
        miaoSha  =  findByAfterDate(date);
        map.put(7 , miaoSha);

        return map;
    }

    ////找到当天、当前时间正在的秒杀商品
    private static MiaoSha findByCurrentDate(Date currentDate){
        String hql = "from MiaoSha where to_char(beginDate,'yyyy-MM-dd') = ? " +
                "and beginDate < sysdate and  sysdate < endDate and state = '1' ";
        String date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        MiaoSha currentMiaoSha  = MiaoSha.find(hql, date).first();//当天、当前时间正在秒杀的商品
        MiaoSha immediatelyMiaoSha = null;
        MiaoSha afterMiaoSha = null;
        MiaoSha beforeMiaosha = null;
        if(currentMiaoSha != null ){
            return currentMiaoSha;
        }else{
            hql = "from MiaoSha where to_char(beginDate,'yyyy-MM-dd') = ? " +
                    "and beginDate < sysdate and  sysdate < endDate and state = '3' ";
            date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            immediatelyMiaoSha =  MiaoSha.find(hql, date).first();
            if(immediatelyMiaoSha != null){
                immediatelyMiaoSha.state="1";
                MiaoSha.updateState("1",immediatelyMiaoSha.id);
            }
        }
        if(immediatelyMiaoSha != null){
            return immediatelyMiaoSha;
        }else {
            //获取这一天开始时间在当前系统时间之后即将秒杀的商品
            hql = "from MiaoSha where  to_char(beginDate,'yyyy-MM-dd') = ? "+
                    "and beginDate > sysdate  and  state = '3' order by beginDate";
            date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            afterMiaoSha = MiaoSha.find(hql, date).first();
        }
        if(afterMiaoSha != null){
            return afterMiaoSha;
        }else {
            //获取这一天结束时间在系统当前时间以前已秒杀完的商品
            hql = "from MiaoSha where  to_char(beginDate,'yyyy-MM-dd') = ? "+
                    "and endDate < sysdate  and  state = '2' order by endDate desc";
            date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            beforeMiaosha = MiaoSha.find(hql, date).first();
        }
        if(beforeMiaosha != null){
            return beforeMiaosha;
        }
        return null;



    }

    //找到当前日期以前的一天的秒杀商品（若没有正在秒杀的商品，则取最后一件已经被秒杀的商品）
    private static MiaoSha findByBeforeDate(Date date){
        String hql = "from MiaoSha where to_char(endDate,'yyyy-MM-dd') = ? " +
                "and endDate > sysdate  and state = '1' order by endDate desc";
        MiaoSha currentMiaoSha =  MiaoSha.find(hql, new SimpleDateFormat("yyyy-MM-dd").format(date)).first();
        MiaoSha  miaoSha = null;
        if(currentMiaoSha != null ){
            return currentMiaoSha;
        }else {
            hql = "from MiaoSha where to_char(beginDate,'yyyy-MM-dd') = ? " +
                    "and endDate < sysdate and state = '2' order by endDate desc";
            miaoSha = MiaoSha.find(hql, new SimpleDateFormat("yyyy-MM-dd").format(date)).first();
        }
        if(miaoSha != null){
            return miaoSha;
        }
        return null;

    }

    //找到当前日期以后的一天的秒杀商品（获取这一天第一个将被秒杀的商品）
    private static MiaoSha findByAfterDate(Date date){
        String hql = "from MiaoSha where to_char(beginDate,'yyyy-MM-dd') = ? "+
                "and  state ='3' order by beginDate ";
        MiaoSha miaoSha = MiaoSha.find(hql, new SimpleDateFormat("yyyy-MM-dd").format(date)).first();
        Date currentDate = new Date();
        if (miaoSha!= null && currentDate.compareTo(miaoSha.beginDate) != -1){
            miaoSha.state = "1";
            miaoSha.save();
        }
        return miaoSha;
    }



    //1 代表前一天， 2代表前两天 以此类推。。。
    @Util
    public static Date getBeforeDate(Date date,Integer integer){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-integer);
        return calendar.getTime();
    }
    //1 代表后一天， 2代表后两天 以此类推。。。
    @Util
    public static Date getAfterDate(Date date,Integer integer){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,+integer);
        return calendar.getTime();
    }

    //根据当前日期获取是星期几
    @Util
    public static String getWeekStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.DAY_OF_WEEK);
        String str = "";
        if(hour == 2){
            str = "星期一";
        }else if(hour == 3){
            str = "星期二";
        }else if(hour == 4){
            str = "星期三";
        }else if(hour == 5){
            str = "星期四";
        }else if(hour == 6){
            str = "星期五";
        }else if(hour == 7){
            str = "星期六";
        }else if(hour == 1){
            str = "星期日";
        }
        return str;
    }


    public static Map<Integer, MiaoSha> getFirstPic(Map<Integer, MiaoSha> miaoShaMap) {
        String images=null;
        for(int i = 1;i<=7 ;i++){
            if( miaoShaMap.get(i) != null){
                images = miaoShaMap.get(i).goods.images;
                Gson gson = new Gson();
                List<String> goodsList = new ArrayList<String>();
                try{
                    goodsList = gson.fromJson(images, new TypeToken<List<String>>() {}.getType());
                    images = goodsList.get(0); //默认获取第一张图片
                }catch (Exception e){
                    images = "";
                    log.info(e);
                }
                miaoShaMap.get(i).goods.images = images;
            }
        }
        return miaoShaMap;
    }
}
