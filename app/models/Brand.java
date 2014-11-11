package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * User: Liujy
 * Date: 13-6-17
 * Time: 上午11:59
 */
@Entity
@Table(name="TTXS_SHOP_BRAND")
public class Brand  extends JPASupport{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "BRANDID")
    public String id;

    @Column(name = "BRANDNAME")
    public String name;
}
