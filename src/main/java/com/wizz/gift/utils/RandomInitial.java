package com.wizz.gift.utils;

import java.util.Random;

/**
 * @author liqiqi_tql
 * @date 2020/11/23 -22:23
 */
public class RandomInitial {
    public static int[] getArray(int a,int b){
        Random r = new Random();
        //使用数组接收随机数字
        int[] arr = new int[a];
        for (int i = 0; i <arr.length ; i++) {
            //随机生成1-b的数字
            arr[i] = r.nextInt(b)+1;
            for (int j = 0; j <i ; j++) {
                //对数组内容进行遍历，如果随机数和数组中的元素
                //有相同的话，那么外循环i--,对此索引位置上的元素重新生成
                if (arr[i] == arr[j]){
                    i--;
                    break;
                }
            }
        }
        //返回数组
        return arr;
    }

}
