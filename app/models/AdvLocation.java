package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-10
 * Time: A.M.9:23
 * 广告位置实体类
 */
@Entity
@Table(name = "TTXS_SYS_LOCATIONINFO")
public class AdvLocation extends JPASupport {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "LOCATIONID")
    public String id;

    @Column(name = "LOCATIONNAME")
    public String codeName;//位置代码

    @Column(name = "LOCATIONINTRO")
    public String name;//广告位置名称

    @Column(name = "CREATEDATE")
    public Date createDate;

    @Column(name = "MODIFYDATE")
    public Date lastModifyDate;

    @Column(name = "LOCATIONSIZE")
    public String locationSize;//广告位置尺寸

    @Column(name = "ADVNUMBER")
    public Integer advNumber;//可投放的广告数量

    public static AdvLocation findByName(String codeName){
        String hql = "from AdvLocation where codeName = ?";
        return find(hql,codeName).first();
    }

}
