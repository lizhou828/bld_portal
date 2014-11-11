package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * User: administrator
 * Date: 13-6-18
 * Time: 下午4:48
 */
@Entity
@Table(name="TTXS_SHOP_ADDRESS")
public class Address extends JPASupport {
    @Id
    @Column(name = "ID")
    public String id = UUID.randomUUID().toString().replace("-","");

    @Column(name = "ADDRESS")
    public String address;

    public String phoneNumber;//座机

    public String mobileNumber;//手机

    public String zipCode;//邮编

    public String name;//收货人姓名



    @ManyToOne
    @JoinColumn(name = "MEMBERID",columnDefinition = "char")
    public Member member;

    public static List<Address> findByMemberId(String memberId) {
        String hql = "from Address a where a.member.memberId = ?";
        return find(hql,memberId).fetch();
    }
}
