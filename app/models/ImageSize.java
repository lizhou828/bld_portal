package models;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-7-5
 * Time: 2:41
 * 图片尺寸
 */
public class ImageSize {
    private final static int[][] sizes = {
            {58, 58}, //    0
            {168,168},//1
            {180,180},//2
            {350,350},//3
            {100,100}, //4
            {190,190},//5
            {150,150} ,//6
            {708,305},//7
            {200,182},//8
            {60,60},//9
            {278,471},//10
            {1183,115}//11
    };

    /* 商品详细页，缩略图 */
    public final static String XIANGXI_SUOLUETU = sizes[0][0] + "X" + sizes[0][1];

    /* 商品详细页，大图*/
    public final static String XIANGXI_DATU = sizes[3][0] + "X" + sizes[3][1];

    /* 首页秒杀区， */
    public final static String INDEX_MIAOSHA= sizes[1][0] + "X" + sizes[1][1];

    /* 首页，商品分类图 */
    public final static String INDEX_FENLEI= sizes[2][0] + "X" + sizes[2][1];

    /* 商品详细页，热销商品图*/
    public final static String XIANGXI_HOTS = sizes[4][0] + "X" + sizes[4][1];

    /*商品列表页，商品图片*/
    public final static String LIST_SUOLUETU = sizes[5][0] + "X" +sizes[5][1];

    /*商品列表页，相关产品图片*/
    public final static String LIST_XIANGGUAN = sizes[6][0] + "X" +sizes[6][1];

    /* 首页，广告大图片轮播 */
    public final static String INDEX_PPT_BIGPIC= sizes[7][0] + "X" + sizes[7][1];

    /* 首页，广告小图片轮播 */
    public final static String INDEX_PPT_LITTLEPIC= sizes[8][0] + "X" + sizes[8][1];

    /*商品订单缩略图*/
    public final static String ORDER_SUOLUETU = sizes[9][0] + "X" +sizes[9][1];

    /*首页左侧广告*/
    public final static String INDEX_LEFT_ADV = sizes[10][0] + "X" +sizes[10][1];

    /*首页长条广告*/
    public final static String INDEX_LONG_ADV = sizes[11][0] + "X" +sizes[11][1];

    /**
     * get special size for compressed image
     * <pre>
     *     String xUrl = IMAGE_SIZE.getImage("upload/2013/01/21/123456.jpg", IMAGE_SIZE.X)
     *     the xUrl is "upload/2013/01/21/123456_50X50.jpg"
     * </pre>
     *
     * @param url the origin image url
     * @param size image size, for example : IMAGE_SIZE.X or IMAGE_SIZE.S
     * @return the compressed image url
     */
    public static String getImage(String url, String size) {
        if(url == null || "".equals(url.trim()))return  null;
        int idx = url.lastIndexOf(".");
        return String.format("%s_%s%s", url.substring(0, idx), size, url.substring(idx));
    }

//    public static void main(String []args ){
//        System.out.println(ImageSize.INDEX_FENLEI);
//        System.out.println(getImage("upload/2013/01/21/123456.jpg", ImageSize.INDEX_FENLEI));
//        System.out.println(getImage("upload/2013/01/21/123456.jpg", ImageSize.INDEX_MIAOSHA));
//        System.out.println(getImage("upload/2013/01/21/123456.jpg", ImageSize.XIANGXI_DATU));
//        System.out.println(getImage("upload/2013/01/21/123456.jpg", ImageSize.XIANGXI_SUOLUETU));
//
//    }
}
