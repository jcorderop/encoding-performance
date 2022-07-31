package com.jc.encoding.builder.text;

import com.google.gson.Gson;
import com.jc.encoding.builder.TradeConverter;
import com.jc.model.dto.TradeDto;

public class GsonTradeBuilder implements TradeConverter<TradeDto> {

    @Override
    public TradeDto newTrade(final TradeDto tradeDto) {
        return tradeDto;
    }

    public byte[] toBytes(final TradeDto t) {
        return new Gson().toJson(t).getBytes();
    }

    public TradeDto fromBytes(final byte[] b) {
        return new Gson().fromJson(new String(b), TradeDto.class);
    }
}
