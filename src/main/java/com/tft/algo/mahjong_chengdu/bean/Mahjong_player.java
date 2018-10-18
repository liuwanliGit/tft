package com.tft.algo.mahjong_chengdu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>类描述：麻将玩家
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/26 9:44
 *
 * @ClassName Mahjong_player
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class Mahjong_player {

    private List<Mahjong_entity> handCard = new ArrayList<>();//手牌

    private List<Mahjong_entity> outCard = new ArrayList<>();//已出牌

    private List<Mahjong_entity> downCard = new ArrayList<>();//已碰手牌

    private Mahjong_entity inCard ;  //摸到的手牌

    private boolean haveHu = false; //是否已经胡牌

    private String name;//玩家名称

    public Mahjong_player(String name) {
        this.name = name;
    }

    public List<Mahjong_entity> getHandCard() {
        return handCard;
    }

    public void setHandCard(List<Mahjong_entity> handCard) {
        this.handCard = handCard;
    }

    public List<Mahjong_entity> getOutCard() {
        return outCard;
    }

    public void setOutCard(List<Mahjong_entity> outCard) {
        this.outCard = outCard;
    }

    public List<Mahjong_entity> getDownCard() {
        return downCard;
    }

    public void setDownCard(List<Mahjong_entity> downCard) {
        this.downCard = downCard;
    }

    public Mahjong_entity getInCard() {
        return inCard;
    }

    public void setInCard(Mahjong_entity inCard) {
        this.inCard = inCard;
    }

    public boolean isHaveHu() {
        return haveHu;
    }

    public void setHaveHu(boolean haveHu) {
        this.haveHu = haveHu;
    }
}
