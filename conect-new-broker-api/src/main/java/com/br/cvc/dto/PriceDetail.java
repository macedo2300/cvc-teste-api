package com.br.cvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PriceDetail {

    @JsonProperty("pricePerDayAdult")
    private BigDecimal pricePerDayAdult;

    @JsonProperty("pricePerDayChild")
    private BigDecimal pricePerDayChild;

    public PriceDetail(BigDecimal calpricePerDayAdult, BigDecimal calpricePerDayChild) {
        this.pricePerDayAdult = calpricePerDayAdult;
        this.pricePerDayChild = calpricePerDayChild;
    }

    public BigDecimal getPricePerDayAdult() {
        return pricePerDayAdult;
    }

    public void setPricePerDayAdult(BigDecimal pricePerDayAdult) {
        this.pricePerDayAdult = pricePerDayAdult;
    }

    public BigDecimal getPricePerDayChild() {
        return pricePerDayChild;
    }

    public void setPricePerDayChild(BigDecimal pricePerDayChild) {
        this.pricePerDayChild = pricePerDayChild;
    }
}
