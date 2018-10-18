package com.tft.framework.common.bean;

import org.springframework.data.domain.Sort;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/8 0:29
 *
 * @ClassName TftOrder
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TftOrder {

    private String sortTitle;
    private String sortOrder;

    public TftOrder(){

    }
    public TftOrder(String sortTilte){
        this.sortTitle = sortTilte;
        this.sortOrder = "asc";
    }
    public TftOrder(String sortTilte,String sortOrder){
        this.sortTitle = sortTilte;
        this.sortOrder = sortOrder;
    }
    public String getSortTitle() {
        return sortTitle;
    }

    public void setSortTitle(String sortTitle) {
        this.sortTitle = sortTitle;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Sort.Order toOrder(){
        if("desc".equalsIgnoreCase(sortOrder)){
            return Sort.Order.desc(sortTitle);
        }
        return Sort.Order.asc(sortTitle);
    }

}
