package controllers;

import models.Address;
import models.Member;
import models.Order;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-6-18
 * Time: 下午4:04
 */
public class Members extends Application{



    public static void registerUI(){
        render();
    }

    public static void register(String userName,String password,String password1,String email){
        String emailError = "";
        if(userName == null || "".equals(userName.trim())){
            String userError = "用户名为空";
            render("/Members/registerUI.html",userError,userName,password,password1);
        }
        Member member = Member.findByUserName(userName);
        if(member != null){
            String userError = "用户名已存在";
            render("/Members/registerUI.html",userError,userName,password,password1);
        }
        if(password == null || "".equals(password.trim())){
            String passwordError = "密码为空";
            render("/Members/registerUI.html",passwordError,userName,password,password1);
        }
        if(password.trim().length() < 7){
            String passwordError = "密码长度少于7位";
            render("/Members/registerUI.html",passwordError,userName,password,password1);
        }
        if(!password.equals(password1)){
            String password1Error = "确认密码错误";
            render("/Members/registerUI.html",password1Error,userName,password,password1);
        }
        if(email == null || "".equals(email)){
            emailError = "邮箱不能为空";
            render("/Members/registerUI.html",emailError,userName,password,password1,email);
        }
        String emailCheck = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]" +
                "+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(!email.matches(emailCheck)){
            emailError = "邮箱格式不正确";
            render("/Members/registerUI.html",emailError,userName,password,password1,email);
        }
        Member member1 = new Member();
        member1.userName = userName;
        member1.password = DigestUtils.md5Hex(password).toUpperCase();
        member1.email = email;
        member1.create();
        render(member1);
    }

    public static void logonUI(){
        render();
    }

    public static void logon(String userName,String password,String url) throws UnsupportedEncodingException {
        String userError = "";
        String passwordError = "";
        if(userName == null || userName.trim().equals("")){
            userError = "用户名不能为空";
            render("/Members/logonUI.html",userError,userName,password);
        }
        if(password == null || password.trim().equals("")){
            passwordError = "密码不能为空";
            render("/Members/logonUI.html",passwordError,userName,password);
        }
        Member member = Member.findByUserName(userName);
        if(member == null){
            userError = "不存在该用户";
            render("/Members/logonUI.html",userError,userName,password);
        }
        if(!DigestUtils.md5Hex(password).toUpperCase().equals(member.password)){
            passwordError = "密码错误";
            render("/Members/logonUI.html",passwordError,userName,password);
        }
        session.put(USER,member.userName);
        if(url != null && !"".equals(url) && !url.contains("logonUI") && !url.contains("registerUI")){
            redirect(URLDecoder.decode(url,"UTF-8"));
        }

    }

    public static void logout(){
       session.remove(USER);
       redirect("/Members/logonUI");
    }

    public static void welcome(){
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        Order order = Order.findByMemberId(member.memberId);
        render(member,order);
    }

    public static void rePasswordUI(){
        render();
    }

    public static void rePassword(String oldPassword,String newPassword,String newPassword1){
        String passwordError = "";
        String passwor1Error = "";
        String password2Error = "";

        if(oldPassword == null || "".equals(oldPassword.trim())){
           passwordError = "原密码不能为空";
            render("/Members/rePasswordUI.html",passwordError);
        }
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        if(!member.password.toUpperCase().equals(DigestUtils.md5Hex(oldPassword).toUpperCase())){
            passwordError = "原密码不正确";
            render("/Members/rePasswordUI.html",passwordError);
        }
        if(newPassword == null || "".equals(newPassword)){
            passwor1Error = "新密码不能为空";
            render("/Members/rePasswordUI.html",passwor1Error);
        }
        if(newPassword.trim().length() < 7){
            passwor1Error = "密码长度必须大于六位";
            render("/Members/rePasswordUI.html",passwor1Error);
        }
        if (newPassword1 == null || "".equals(newPassword1)){
            password2Error = "确认密码不能为空";
            render("/Members/rePasswordUI.html",password2Error);
        }
        if(!newPassword.equals(newPassword1)){
            password2Error = "二次密码输入错误";
            render("/Members/rePasswordUI.html",password2Error);
        }
        member.password = DigestUtils.md5Hex(newPassword).toUpperCase();
        member.save();
        render();
    }

    public static void address(){
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        List<Address> list = Address.findByMemberId(member.memberId);
        render(list);
    }
}
