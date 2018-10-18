package com.tft.algo.mahjong_chengdu.bean;

/**
 * <br>类描述：麻将花色
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/26 9:37
 *
 * @ClassName Mahjong_color
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public enum Mahjong_color {
    TONG("tong","筒"),TIAO("tiao", "条"),WAN("wan", "万");

    private final String key;
    private final String value;

    Mahjong_color(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
