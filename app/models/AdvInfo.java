package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-6
 * Time: p.m.4:11
 * 广告信息
 *
 */
@Entity
@Table(name = "TTXS_ADV_ADVINFO")
public class AdvInfo extends JPASupport {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ADVINFOID")
    public String id;

    @Column(name = "ADVTITLE")
    public String advTitle;//广告标题

    @JoinColumn(name = "LOCATIONID")
    @ManyToOne
    public AdvLocation advLocation;//广告位置

    @Column(name = "ADVTYPE")
    public  String type; //广告类型   1 代表图片

    @Column(name = "HYPERLINK")
    public  String hypeLink; //广告链接

    @Column(name = "PHOTOID")
    public  String advImages; //广告图片

    @Column(name = "SHOWTYPE")
    public  Integer showType; //展示类型 1 表示图片

    @Column(name = "SHOWBEGINDATE")
    public Date beginDate; //广告展示开始日期

    @Column(name = "SHOWENDDATE")
    public Date endDate; //广告展示结束日期

    @Column(name = "ADVSTAT")
    public  String state; //广告状态 1表示展示  0表示不展示

    @JoinColumn(name = "UPUSERID")
    @ManyToOne
    public  Admin admin; //后台操作人

    @Column(name = "UPUSERIP")
    public  String ip;

    @Column(name = "CREATEDATE")
    public  Date createDate;

    @Column(name = "MODIFYDATE")
    public  Date lastModifyDate;


    /**
     *
     * @param locationName 广告位置名
     * @param num
     * @return
     */
    public static List<AdvInfo> findByLocation( String locationName,Integer num ){
        if(locationName == null || "".equals(locationName) || num == null || num <= 0 ){
            return null ;
        }
        AdvLocation advLocation1 = AdvLocation.findByName(locationName);
        String hql = "from AdvInfo where advLocation = ? ";
        hql += "and type = '1' and showType = 1 and state = '1'" +
                " and  beginDate < sysdate and endDate  > sysdate order by createDate desc";
        List<AdvInfo> advInfoList = find(hql,advLocation1).fetch();
        if(advInfoList != null && advInfoList.size() > num){
            advInfoList = advInfoList.subList(0,num);
        }
        return advInfoList;
    }

    public static AdvInfo findByLocation(String locationName){
        if(locationName == null || "".equals(locationName)  ){
            return null ;
        }
        AdvLocation advLocation1 = AdvLocation.findByName(locationName);
        String hql = "from AdvInfo where advLocation = ? ";
        hql += "and type = '1' and showType = 1 and state = '1'" +
                " and  beginDate < sysdate and endDate  > sysdate order by createDate desc";
        return find(hql,advLocation1).first();
    }

    public   List<String> getImagePaths(){
        try{
            return new Gson().fromJson(advImages,new TypeToken<List<String>>(){}.getType());
        }catch (Exception e){
            return null;
        }
    }

}
