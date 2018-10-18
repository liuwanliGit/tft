package com.tft.algo.mahjong_chengdu.bean;

import java.util.List;

/**
 * <br>类描述：麻将实体
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/25 10:11
 *
 * @ClassName Mahjong_entity
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class Mahjong_entity {

    private Integer value;  //值
    private Mahjong_color color;//花色

    public Mahjong_entity(Integer value, Mahjong_color color){
        this.value = value;
        this.color = color;
    }
    public Mahjong_entity(){

    }
    public static String printMahjongList(List<Mahjong_entity> list){
        String str = "";
        if(list==null || list.size()<1){
            return str;
        }
        for(Mahjong_entity entity:list){
            str = str + " " + entity.toString();
        }
        return str;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Mahjong_color getColor() {
        return color;
    }

    public void setColor(Mahjong_color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.value.toString()+this.color.getValue();
    }

    @Override
    public int hashCode() {
        return value.hashCode()+color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Mahjong_entity entity = (Mahjong_entity) obj;
        return entity.value == this.value && entity.color==this.color;
    }

    @Override
    public Mahjong_entity clone() throws CloneNotSupportedException {
        return new Mahjong_entity(this.value,this.color);
    }
}
