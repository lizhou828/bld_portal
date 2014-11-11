package controllers;

import models.Address;
import models.Member;

/**
 * User: Liujy
 * Date: 13-6-26
 * Time: 下午5:04
 */
public class Addresses extends Application {

    public static void addUI(){
        render();
    }

    public static void testGit(){

    }

    public static void add(String address,String name,String phoneNum,String mobileNum,String zipCode){
        String userName = session.get(USER);
        Member member = Member.findByUserName(userName);
        Address address1 = new Address();
        address1.name = name;
        address1.address = address;
        address1.phoneNumber = phoneNum;
        address1.mobileNumber = mobileNum;
        address1.member = member;
        address1.create();
        redirect("/Members/address");
    }

    public static void delete(String id){
        Address address = Address.findById(id);
        address.delete();
        redirect("/Members/address");
    }


}
