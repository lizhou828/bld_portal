package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * User: Liujy
 * Date: 13-7-4
 * Time: 上午10:01
 */
@Entity
@Table(name="TTXS_SHOP_GOODSRAT")
public class GoodsEvaluate extends JPASupport {

    @Id
    @Column(name = "GOODRATID",length = 32)
    public String id = UUID.randomUUID().toString().replace("-","");

    @Column(name = "RATMSG",length = 256)
    public String comment;

    @ManyToOne
    @JoinColumn(name = "MEMBERID")
    public Member member;

    @Column(name = "CREATEDATE")
    public Date createDate;

    @OneToOne
    @JoinColumn(name = "GOODSID")
    public Goods goods;

}
