package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * User: administrator
 * Date: 13-7-22
 * Time: 上午10:07
 */
@Entity
@Table(name="TTXS_SHOP_FEETEMPLATE")
public class DeliveryType extends JPASupport{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    public String id;

    public Double firstWeight;// 首重量

    public Double continueWeight;// 续重量

    public String firstWeightUnit;// 首重单位

    public String continueWeightUnit;// 续重单位

    @Column(name = "FIRST_WEIGHT_PRICE")
    public double firstWeightPrice;// 首重价格

    @Column(name = "CONTINUE_WEIGHT_PRICE")
    public double continueWeightPrice;// 续重价格

    @Column(name = "FIRST_PRICE")
    public double firstPrice;//邮费优惠需满足的价格

    @Column(name = "PROVINCE")
    public String province;//省

    public String description;// 介绍

    //重量换算，默认返回以G为单位的重量
    public static double weightConversion(double weight,String weightUnit){
        double result = 0;
        if(WeightUnit.G.equals(weightUnit)){
            result = weight;
        }
        else  if(WeightUnit.KG.equals(weightUnit)){
            result = weight * 1000;
        }else {
            throw new RuntimeException("weightUnit is not true");
        }
        return result;
    }

    public static double calculateWeight(Goods goods,int count){
        double weight = weightConversion(goods.weight,goods.weightUnit) * count;
        return weight;
    }

    public static DeliveryType findByPro(String province) {
        String hql = "from DeliveryType d where d.province = ?";
        return find(hql,province).first();
    }

    public static double calculateFreight(DeliveryType deliveryType, double countPrice,double countWeight) {
        double result = 0;
        double firstWeight = DeliveryType.weightConversion(deliveryType.firstWeight,deliveryType.firstWeightUnit);
        double continueWeight = DeliveryType.weightConversion(deliveryType.continueWeight,deliveryType.continueWeightUnit);
        if(countPrice >= deliveryType.firstPrice){
            if(countWeight>firstWeight){
                result = ((countWeight - firstWeight)/continueWeight) * deliveryType.continueWeightPrice;
            }
        }else{
            result = deliveryType.firstWeightPrice;
            if(countWeight > firstWeight){
                result = result + ((countWeight - firstWeight)/continueWeight) * deliveryType.continueWeightPrice;
            }
        }
        return result;
    }
}
