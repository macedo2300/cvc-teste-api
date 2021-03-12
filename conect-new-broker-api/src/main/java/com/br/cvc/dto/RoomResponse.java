package com.br.cvc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomResponse implements Serializable {


    private static final long serialVersionUID = -5299601993767038567L;

    @JsonProperty("roomID")
    private int roomID;

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("priceDetail")
    private PriceDetail priceDetail;


    public RoomResponse(int roomID, String categoryName,
                        BigDecimal calpricePerDayAdult,
                        BigDecimal calpricePerDayChild, BigDecimal total) {

        this.roomID = roomID;
        this.categoryName = categoryName;
        this.totalPrice = total;
        this.priceDetail = new PriceDetail(calpricePerDayAdult,calpricePerDayChild);
    }
}
