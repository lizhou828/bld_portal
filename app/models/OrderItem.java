package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: Liujy
 * Date: 13-6-24
 * Time: 上午10:16
 */
@Entity
@Table(name="TTXS_SHOP_ORDERITEM")
public class OrderItem extends JPASupport{
    @Id
    @Column(name = "ORDERITEMID")
    public String id = UUID.randomUUID().toString().replace("-","");


    @Column(name = "CREATEDATE")
    public Date createDate  = new Date();

    @Column(name = "GOODSNUM")
    public int goodsNum;

    @ManyToOne
    @JoinColumn(name = "GOODSID")
    public Goods goods;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    public Order order;

    public static List<OrderItem> findByOderId(String orderId) {
        String hql = "from OrderItem o where o.order.id = ?";
        return find(hql,orderId).fetch();
    }
}
