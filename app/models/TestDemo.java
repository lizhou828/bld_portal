package models;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-8
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
public class TestDemo {
    public static void main(String [] args){
       DeliveryType deliveryType = new DeliveryType();
       deliveryType.firstPrice = 88;
        deliveryType.firstWeight = 1d;
        deliveryType.firstWeightPrice = 6;
        deliveryType.firstWeightUnit = "kg";
        deliveryType.continueWeight = 1d;
        deliveryType.continueWeightPrice = 1;
        deliveryType.continueWeightUnit = "kg";
        double  result = DeliveryType.calculateFreight(deliveryType,88,1000);
        System.out.println(result);
    }
}
