package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.ISerializable;
import com.jc.model.avro.Trade;
import com.jc.model.avro.TradeType;
import com.jc.model.dto.TradeDto;

import java.io.IOException;
import java.io.UncheckedIOException;

public class AvroTradeRecordBuilder implements ISerializable<Trade>  {

    @Override
    public Trade newTrade(final TradeDto tradeDto) {
        Trade trade = Trade.newBuilder()
                .setTradeId(tradeDto.getTradeId())
                .setCustomerId(tradeDto.getCustomerId())
                .setExchange(tradeDto.getExchange())
                .setTradeType(TradeType.valueOf(tradeDto.getTradeType().name()))
                .setSymbol(tradeDto.getSymbol())
                .setQty(tradeDto.getQty())
                .build();
        return trade;
    }

    @Override
    public Trade fromBytes(final byte[] bytes) {
        try {
            return Trade.getDecoder().decode(bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public byte[] toBytes(final Trade trade) {
        try {
            return Trade.getEncoder().encode(trade).array();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
