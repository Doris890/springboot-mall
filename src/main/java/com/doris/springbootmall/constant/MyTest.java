package com.doris.springbootmall.constant;

public class MyTest {

    /**
     * Enum跟字串之間轉換(使用頻率高)
     * @param args
     */
    public static void main(String[] args) {

        //1. 給予enum，去轉成string
        ProductCategory category = ProductCategory.FOOD;
        String s = category.name();
        System.out.println(s);  //FOOD


        //2. 給予字串，去回查enum裡面的值
        String s2 = "CAR";
        ProductCategory category2 = ProductCategory.valueOf(s2);



    }
}
