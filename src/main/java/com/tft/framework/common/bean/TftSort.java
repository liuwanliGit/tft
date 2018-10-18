package com.tft.framework.common.bean;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>类描述：对排序进行封装
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/8 0:12
 *
 * @ClassName TftSort
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TftSort{

    private List<TftOrder> orders = new ArrayList<>();

    public List<TftOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<TftOrder> orders) {
        this.orders = orders;
    }

    public Sort toSort(){
        List<Sort.Order> orders = new ArrayList<>();
        for(TftOrder order:this.orders){
            orders.add(order.toOrder());
        }
        return Sort.by(orders);
    }

    public void addOrders(TftOrder order){
        orders.add(order);
    }

    public TftSort ascby(String sortTitle){
        this.orders.add(new TftOrder(sortTitle));
        return this;
    }

    public TftSort descby(String sortTitle){
        this.orders.add(new TftOrder(sortTitle,"desc"));
        return this;
    }

    public TftSort by(String sortTitle,String sortOrder){
        this.orders.add(new TftOrder(sortTitle,sortOrder));
        return this;
    }

}
