package com.tft.algo.mahjong_chengdu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>类描述：麻将桌，负责生成一副，管理麻将的四个玩家
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/26 9:42
 *
 * @ClassName Mahjong_table
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class Mahjong_table {
    private List<Mahjong_entity> remainCard; //剩余牌

    private Mahjong_player player1; //玩家1
    private Mahjong_player player2; //玩家1
    private Mahjong_player player3; //玩家1
    private Mahjong_player player4; //玩家1

    public Mahjong_table(Mahjong_player player1,Mahjong_player player2,Mahjong_player player3,Mahjong_player player4){
        this.player1=player1;
        this.player2=player2;
        this.player3=player3;
        this.player4=player4;
    }
    //初始化牌桌
    public Mahjong_table initTable(){
        this.disruptCard();//生成无序的牌
        this.dealCard();    //给玩家发牌
        return this;
    }
    /**
    <br>功能描述:  发牌
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/9/27 10:55
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return void
     * @see #
     */
    private void dealCard() {
        int index = 0;
        for(int i=0;i<4;i++){
            player1.getHandCard().addAll(this.remainCard.subList(0,3));
            remainCard.removeAll(this.remainCard.subList(0,3));
            player2.getHandCard().addAll(this.remainCard.subList(0,3));
            remainCard.removeAll(this.remainCard.subList(0,3));
            player3.getHandCard().addAll(this.remainCard.subList(0,3));
            remainCard.removeAll(this.remainCard.subList(0,3));
            player4.getHandCard().addAll(this.remainCard.subList(0,3));
            remainCard.removeAll(this.remainCard.subList(0,3));
        }
        //最后玩家1拿2张，其他玩家拿1张
        player1.getHandCard().addAll(this.remainCard.subList(0,1));
        remainCard.removeAll(this.remainCard.subList(0,1));
        player2.getHandCard().add(this.remainCard.get(0));
        remainCard.remove(this.remainCard.get(0));
        player3.getHandCard().add(this.remainCard.get(0));
        remainCard.remove(this.remainCard.get(0));
        player4.getHandCard().add(this.remainCard.get(0));
        remainCard.remove(this.remainCard.get(0));
    }

    /**
    <br>功能描述:  打乱牌的顺序
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/9/27 10:49
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return void
     * @see #
     */
    private void disruptCard(){
        this.initOrderCard();
        int size = remainCard.size();
        for(int i=0;i<size/2;i++){
            int index = (int)(Math.random()*size);
            Mahjong_entity temp = this.remainCard.get(index);
            remainCard.set(index,remainCard.get(i));
            remainCard.set(i,temp);
        }
    }
    /**
    <br>功能描述:  生成一副有序的牌
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/9/27 10:49
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return void
     * @see #
     */
    private void initOrderCard(){
        remainCard = new ArrayList<>();
        try {
            for(Mahjong_color color:Mahjong_color.values()){
                for(int i=1;i<10;i++){
                    Mahjong_entity tong = new Mahjong_entity(i,color);
                    remainCard.add(tong);
                    remainCard.add(tong.clone());
                    remainCard.add(tong.clone());
                    remainCard.add(tong.clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public Mahjong_player getPlayer1() {
        return player1;
    }

    public Mahjong_player getPlayer2() {
        return player2;
    }

    public Mahjong_player getPlayer3() {
        return player3;
    }

    public Mahjong_player getPlayer4() {
        return player4;
    }

    public List<Mahjong_entity> getRemainCard() {
        return remainCard;
    }

    public void setRemainCard(List<Mahjong_entity> remainCard) {
        this.remainCard = remainCard;
    }


}
