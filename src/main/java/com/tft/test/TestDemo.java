package com.tft.test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 11:17
 *
 * @ClassName TestDemo
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TestDemo {
    static volatile AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] arg)throws Exception{
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        count.getAndIncrement();
                    }
                }
            }.start();
        }
        Thread.sleep(1000);
        System.out.println("volatile count: " + count);
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arrs = new ArrayList<>();
        Set<String> hasLook = new HashSet<>();
        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]== 0){
                        //所有下标不重复三元组   i,j,k
                        List<Integer> arr = new ArrayList<>();
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[k]);
                        Collections.sort(arr);
                        String arrStr = ""+arr.get(0)+arr.get(1)+arr.get(2);
                        if(hasLook.contains(arrStr)){
                            continue;
                        }else{
                            hasLook.add(""+arr.get(0)+arr.get(1)+arr.get(2));
                            arrs.add(arr);
                        }
                    }
                }
            }
        }
        return arrs;
    }


    public static String convert(String s, int numRows) {
        //计算需要的矩阵大小
        int length = s.length();
        int n = length/(numRows*2-2)*(numRows-1);
        int c = length%(numRows*2-2);
        if(c!=0){
            if(c<=numRows){
                n=n+1;
            }else if(c<2*numRows-1){
                n=1+c-numRows;
            }else{
                n=n+numRows;
            }
        }
        //定义矩阵
        char[][] charArr = new char[numRows][n];
        //读入矩阵
        char[] str = s.toCharArray();
        int x=0,y=0;
        int dir = 0;
        //放字符
        for(int i=0;i<length;i++){
            charArr[x][y]=str[i];
            if(dir==0){
                if(x<numRows-1){
                    x++;
                }else{
                    dir = 1;
                    x--;
                }
            }
            if(dir==1){
                if(x>0){
                    y++;
                    x--;
                }else{
                    dir = 0;
                    x++;
                }
            }
        }
        //读字符
        int index = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<numRows;j++){
            }
        }
        System.out.println(n);
        return s;
    }
}
