package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: Liujy
 * Date: 13-6-18
 * Time: 下午3:09
 */
@Entity
@Table(name="TTXS_SHOP_ORDERS")
public class Order extends JPASupport {

    @Id
    @Column(name = "ORDERID")
    public String id = UUID.randomUUID().toString().replace("-","");

    @Column(name = "ORDERSN")
    public String orderNum;//订单编号

    @Column(name = "ORDERSTATUS")
    public Integer status;

    @Column(name = "TOTALAMOUNT")
    public double totalPrice;//订单总金额

    @Column(name = "PRODUCTTOTALQUANTITY")
    public int count;//商品总数量

    @Column(name = "SHIPNAME")
    public String shipName;

    @Column(name = "SHIPADDRESS")
    public String shipAddress;

    @Column(name = "SHIPZIPCODE")
    public String shipZipCode;

    @Column(name = "SHIPPHONE")
    public String shipPhone;

    @Column(name = "SHIPMOBILE")
    public String shipMobile;

    @Column(name = "CREATEDATE")
    public Date createDate = new Date();

    @Column(name = "MODIFYDATE")
    public Date modifyDate;

    @ManyToOne
    @JoinColumn(name = "MEMBERID",columnDefinition = "char")
    public Member member;

    public static Order findByMemberId(String memberId) {
        String hql = "from Order o where o.member.memberId = ? order by o.createDate desc";
        return find(hql,memberId).first();
    }

    public static Page<Order> findByMemberId(String memberId,Integer page,Integer pageSize) {
        String hql = "from Order o where o.member.memberId= ? order by o.createDate desc";
        List<Order> list = find(hql,memberId).fetch(page,pageSize);
        hql = "select count(*) from Order o where o.member.memberId= ?";
        long totalCount = count(hql,memberId);
        return new Page<Order>(page,pageSize,totalCount,list);
    }
}
