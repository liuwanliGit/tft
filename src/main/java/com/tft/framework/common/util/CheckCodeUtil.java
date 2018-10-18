package com.tft.framework.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/25 16:21
 *
 * @ClassName CodeImgUtil
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class CheckCodeUtil {

    private static int width=90,height=20;
    private static Random random = new Random();
    //验证码字符集合
    private static String codeChars =
            "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    //返回一个随机颜色
    private static Color getRandomColor(int minColor,int maxColor){
        //保证minColor最大不超过255
        if(minColor >255) minColor =255;
        //保证maxColor最大不超过minColor
        if(maxColor >255) maxColor =255;
        if(minColor > maxColor) {
            int temp = minColor;
            minColor = maxColor;
            maxColor = temp;
        }
        //获得红色的随机颜色值
        int red = minColor+random.nextInt(maxColor-minColor);
        //获取绿色的随机颜色
        int green = minColor+random.nextInt(maxColor-minColor);
        //获取蓝色的随机颜色
        int blue = minColor+random.nextInt(maxColor-minColor);
        return new Color(red, green, blue);
    }
    //生成一个四位的随机验证码
    public static String createCodeChar(){
        int charsLength = codeChars.length();
        char[] codeChar = new char[4];
        for(int i=0;i<4;i++){
            codeChar[i] = codeChars.charAt(random.nextInt(charsLength));
        }
        return new String(codeChar);
    }
    //画背景
    private static void drawBackground(Graphics g){
        //随机设置填充颜色
        g.setColor(getRandomColor(180,250));
        g.fillRect(0, 0, width, height);
        // 随机产生 120 个干扰点

        for (int i = 0; i < 120; i++)
        {
            int x = (int) (Math.random() * width);

            int y = (int) (Math.random() * height);

            int red = (int) (Math.random() * 255);

            int green = (int) (Math.random() * 255);

            int blue = (int) (Math.random() * 255);

            g.setColor(new Color(red, green, blue));

            g.drawOval(x, y, 1, 0);
        }
    }
    //画验证码图片
    private static void drawRands(Graphics g, String rands){
        System.out.println("yzm:"+rands);
        //设置初始字体
        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
        //随机设置字体颜色
        g.setColor(getRandomColor(120, 180));
        //验证码的随机字体
        String[] fontNames = {"Times New Roman","Book antiqua","Arial"};
        // 在不同的高度上输出验证码的每个字符
        for(int i=0;i<4;i++){
            //随机设置字体
            g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
            //随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10,100));
            //在图形上输出验证码字符，x和y都是随机生成
            g.drawString(String.valueOf(rands.charAt(i)), 16*i+random.nextInt(7),height-random.nextInt(6) );
        }
    }

    public static BufferedImage createCodeImg(){
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //获取验证码
        String code = createCodeChar();
        //构造图像
        drawBackground(g);
        //画验证码
        drawRands(g,code);
        //完成绘画
        g.dispose();
        //把code存到session中
        TftWebContext.putValue("checkCode",code);
        return image;
    }

    public static Boolean checkCode(String code){
        String codeTemp = (String)TftWebContext.getValue("checkCode");
        if(codeTemp!=null&&code!=null&&code.equalsIgnoreCase(codeTemp)){
            return true;
        }
        return false;
    }
}
