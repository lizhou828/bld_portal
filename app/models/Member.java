package models;

import play.db.jpa.JPASupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * User: Liujy
 * Date: 13-6-18
 * Time: 下午3:38
 */
@Entity
@Table(name="TTXS_USER_MEMBER")
public class Member extends JPASupport {

    @Id
    @Column(name = "MEMBERID",length = 32)
    public String memberId = UUID.randomUUID().toString().replace("-","");

    @Column(name = "USERNAME")
    public String userName;

    @Column(name = "MEMBERPASSWORD")
    public String password;

    @Column(name = "EMAIL")
    public String email;

    @Column(name = "sex")
    public Integer sex;//0 保密，1 男，2 女





    public static Member findByUserName(String userName) {
        String hql = "from Member m where m.userName = ?";
        return find(hql,userName).first();
    }
}
