package models;

import controllers.MiaoShaUtils;
import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;
import play.mvc.Util;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-1
 * Time: A.M.9:33
 * 商品秒杀推荐
 */
@Entity
@Table(name = "TTXS_ADMIN_MIAOSHA")
public class MiaoSha extends JPASupport{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "MIAOSHAID")
    public String id;

    @ManyToOne
    @JoinColumn(name ="GOODSID" )
    public Goods goods;//商品

    @Column(name = "BEGINDATE")
    public Date beginDate;//秒杀开始日期

    @Column(name = "ENDDATE")
    public Date endDate;  //秒杀结束日期

    @Column(name = "STATE")
    public String state;// 0表示未秒杀  1秒杀正在秒杀  2表示已秒杀过  3表示即将秒杀

    @Column(name = "NUM")
    public Integer number;//商品被秒杀次数

    @ManyToOne
    @JoinColumn(name = "USERID")
    public Admin admin;//秒杀商品推荐人

    @Column(name = "CREATEDATE")
    public Date createDate;// 创建日期

    @Column(name = "BEGINWEEKDAY")
    public String beginWeekday;// 秒杀开始日期是星期几

    @Column(name = "ENDWEEKDAY")
    public String endWeekday;// 秒杀开始日期是星期几

    @Column(name = "TIME")
    public Long time; //秒杀时长

    @Column(name = "MIAOPRICE")
    public Double miaoPrice;//秒杀价格

    @Column(name = "LASTMODIFYTIME")
    public Date lastModifyTime;


   public static void updateState(String state,String id){
       state = state.trim();
       id = id.trim();
       if(state != null && !"".equals(state) && id != null && !"".equals(id)){
           String hql = "from MiaoSha where id = ?";
           MiaoSha miaoSha = find(hql,id).first();
           miaoSha.state = state;
           miaoSha.save();
       }

   }
    public static MiaoSha findById(String id){
        MiaoSha miaoSha = null;
        if(id != null && !"".equals(id)){
            id = id.trim();
            String hql = "from MiaoSha where id = ?";
            miaoSha = find(hql,id).first();
        }
        return miaoSha;
    }

    //根据日期获取秒杀商品
    public static MiaoSha findMiaoShaByDate(Date date){
       String hql = "from MiaoSha where beginDate = ? and state = '1' and weekday is not null order by createDate desc";
       return find(hql,date).first();
    }

    //根据当前日期，获取本周内的秒杀商品（网站首页）
    public static Map<Integer,MiaoSha> findByWeek(Date currentDate){
        Map<Integer,MiaoSha> map = null;
        if(currentDate == null ) return map;
        String currentWeekday = getWeekStr(currentDate);
        if(currentWeekday == null || currentWeekday.equals("")) return map;
        if(currentWeekday.equals("星期一")){
            map = MiaoShaUtils.findByMonday(currentDate, currentWeekday);

        }else if(currentWeekday.equals("星期二")){
            map = MiaoShaUtils.findByTuesday(currentDate, currentWeekday);

        }else if(currentWeekday.equals("星期三")){
            map = MiaoShaUtils.findByWednesday(currentDate, currentWeekday);

        }else if(currentWeekday.equals("星期四")){
            map = MiaoShaUtils.findByThursday(currentDate,currentWeekday);

        }else if(currentWeekday.equals("星期五")){
            map = MiaoShaUtils.findByFriday(currentDate, currentWeekday);

        }else if(currentWeekday.equals("星期六")){
            map = MiaoShaUtils.findBySaturday(currentDate, currentWeekday);

        } else if(currentWeekday.equals("星期日")){
            map = MiaoShaUtils.findBySunday(currentDate, currentWeekday);
        }
        return map;
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




}



