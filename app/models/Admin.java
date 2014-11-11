package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-1
 * Time: A.M.9:57
 * 后台管理员
 */
@Entity
@Table(name = "TTXS_USER_ADMIN")
public class Admin extends JPASupport{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ADMINID")
    public String id;

    @Column(name = "USERNAME")
    public String userName;//

    @Column(name = "ADMINPASSWORD")
    public String password;//

    @Column(name = "EMAIL")
    public String email;//

    @Column(name = "ADMINNAME")
    public String adminName;//

    @Column(name = "ISACCOUNTENABLED")
    public String enable;// 账户是否可用    1表示可用

    @Column(name = "ISACCOUNTLOCKED")
    public String locked;// 账户是否锁定    1表示未锁定

    @Column(name = "ISACCOUNTEXPIRED")
    public String expired;// 账户是否到期    1表示未到期

    @Column(name = "ISCREDENTIALSEXPIRED")
    public String credentialExpired;// 凭证（证书）是否到期    1表示未到期

    @Column(name = "LOGINDATE")
    public Date loginDate;

    @Column(name = "CREATEDATE")
    public Date createDate;

    @Column(name = "LOGINIP")
    public String loginIP;

    @Column(name = "MODIFYDATE")
    public Date modifyDate;










}
