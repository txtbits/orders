package com.txtbits.orders.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Getter
@Setter
public class Order implements Serializable {

    private String region;

    private String country;

    private String itemType;

    private String salesChannel;

    private String orderPriority;

    private Date orderDate;

    @Id
    private Long orderId;

    private Date shipDate;

    private Integer unitsSold;

    private BigDecimal unitPrice;

    private BigDecimal unitCost;

    private BigDecimal totalRevenue;

    private BigDecimal totalCost;

    private BigDecimal totalProfit;

    public Order() { }

    public Order(String region, String country, String itemType, String salesChannel, String orderPriority, Date orderDate, Long orderId, Date shipDate, Integer unitsSold, BigDecimal unitPrice, BigDecimal unitCost, BigDecimal totalRevenue, BigDecimal totalCost, BigDecimal totalProfit) {
        this.region = region;
        this.country = country;
        this.itemType = itemType;
        this.salesChannel = salesChannel;
        this.orderPriority = orderPriority;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.shipDate = shipDate;
        this.unitsSold = unitsSold;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.totalProfit = totalProfit;
    }
}
