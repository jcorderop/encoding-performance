package com.jc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeDto {
    private long tradeId;
    private long customerId;
    private long qty;
    private TradeType tradeType;
    private String symbol;
    private String exchange;

    public enum TradeType {
        Buy,
        Sell
    }
}
